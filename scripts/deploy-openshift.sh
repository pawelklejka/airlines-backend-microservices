#!/bin/bash

# Set namespace/project
NAMESPACE="airlines-backend-microservices"

# Define infrastructure services **in the correct order**
INFRA_SERVICES=(
    "postgres"    # PostgreSQL must start first
    "keycloak"    # Keycloak depends on PostgreSQL
    "kafka"       # Kafka starts after Keycloak
    "elasticsearch"
    "kibana"
    "logstash"
    "jaeger"
)

# Define microservices to deploy (these depend on infrastructure)
BOOTABLE_MODULES=(
    "tourist"
    "ticket"
    "flight"
    "mail-sender"
    "ticket-pdf-generator"
)

# **Ensure we use the correct infrastructure path**
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
INFRA_PATH="$SCRIPT_DIR/../openshift/base/infrastructure"  # Adjusted path for infrastructure

# Ensure we are logged into OpenShift
echo "🔐 Logging into OpenShift..."
oc whoami &>/dev/null
if [ $? -ne 0 ]; then
    echo "❌ Not logged into OpenShift! Run 'oc login' first."
    exit 1
fi

# Ensure the OpenShift project exists
echo "📦 Creating/OpenShift namespace: $NAMESPACE"
oc new-project $NAMESPACE --skip-config-write &>/dev/null || oc project $NAMESPACE

# Function to deploy a single microservice
deploy_service() {
    SERVICE=$1
    echo "🚀 Deploying microservice: airlines-$SERVICE..."

    SERVICE_PATH="$SCRIPT_DIR/../$SERVICE"  # Adjusted path for microservices

    for FILE in configmap.yaml secret.yaml deployment.yaml service.yaml route.yaml; do
        FILE_PATH="$SERVICE_PATH/$FILE"
        if [ -f "$FILE_PATH" ]; then
            echo "📜 Applying $FILE for airlines-$SERVICE..."
            oc apply -f "$FILE_PATH"
        else
            echo "⚠️ Warning: $FILE not found for airlines-$SERVICE, skipping..."
        fi
    done

    # Ensure the service exists before exposing the route
    oc get service airlines-$SERVICE-service -n $NAMESPACE &>/dev/null
    if [ $? -ne 0 ]; then
        echo "❌ Service for airlines-$SERVICE does not exist, skipping route exposure."
        return
    fi

    # Expose an OpenShift Route if it doesn't exist
    oc get route airlines-$SERVICE -n $NAMESPACE &>/dev/null
    if [ $? -ne 0 ]; then
        echo "🌍 Exposing OpenShift Route for airlines-$SERVICE..."
        oc expose service airlines-$SERVICE-service -n $NAMESPACE
    fi
}

# Function to deploy infrastructure services
deploy_infra_services() {
    for SERVICE in "${INFRA_SERVICES[@]}"; do
        echo "🚀 Deploying infrastructure service: airlines-$SERVICE..."

        SERVICE_PATH="$INFRA_PATH/$SERVICE"

        if [ -d "$SERVICE_PATH" ]; then
            for FILE in deployment.yaml service.yaml route.yaml; do
                FILE_PATH="$SERVICE_PATH/$FILE"
                if [ -f "$FILE_PATH" ]; then
                    echo "📜 Applying $FILE for airlines-$SERVICE..."
                    oc apply -f "$FILE_PATH"
                else
                    echo "⚠️ Warning: $FILE not found for airlines-$SERVICE, skipping..."
                fi
            done

            # Wait for service to be available
            oc get service airlines-$SERVICE-service -n $NAMESPACE &>/dev/null
            if [ $? -ne 0 ]; then
                echo "⏳ Waiting for service airlines-$SERVICE-service to be created..."
                sleep 5
                oc get service airlines-$SERVICE-service -n $NAMESPACE &>/dev/null
                if [ $? -ne 0 ]; then
                    echo "❌ Service for airlines-$SERVICE does not exist after retrying, skipping route exposure."
                    continue
                fi
            fi

            # Expose an OpenShift Route if it doesn't exist
            oc get route airlines-$SERVICE -n $NAMESPACE &>/dev/null
            if [ $? -ne 0 ]; then
                echo "🌍 Exposing OpenShift Route for airlines-$SERVICE..."
                oc expose service airlines-$SERVICE-service -n $NAMESPACE
            fi
        else
            echo "⚠️ Warning: Directory $SERVICE_PATH not found, skipping deployment."
        fi
    done
}

# Function to wait for a service to be ready
wait_for_pod() {
    local SERVICE=$1
    local MAX_RETRIES=60  # Wait up to 10 minutes
    local RETRY=0

    # Check if the service exists before waiting for a pod
    oc get service airlines-$SERVICE-service -n $NAMESPACE &>/dev/null
    if [ $? -ne 0 ]; then
        echo "❌ Service airlines-$SERVICE-service does not exist! Skipping..."
        return
    fi

    while [[ $(oc get pods -n $NAMESPACE -l app=airlines-$SERVICE -o jsonpath='{.items[0].status.phase}' 2>/dev/null) != "Running" ]]; do
        if [ $RETRY -ge $MAX_RETRIES ]; then
            echo "❌ Timeout: airlines-$SERVICE did not start successfully."
            oc logs -f deployment/airlines-$SERVICE -n $NAMESPACE  # Show logs
            exit 1
        fi
        echo "⏳ Waiting for airlines-$SERVICE to start... ($RETRY/$MAX_RETRIES)"
        sleep 10
        ((RETRY++))
    done
    echo "✅ airlines-$SERVICE is now running!"
}

# If a specific service is provided as an argument, only deploy that service
if [ -n "$1" ]; then
    SERVICE_NAME="${1#airlines-}"  # Remove "airlines-" prefix if passed by the user

    # Check if it's an infrastructure service
    if [[ " ${INFRA_SERVICES[@]} " =~ " ${SERVICE_NAME} " ]]; then
        echo "🔄 Reloading infrastructure service: airlines-$SERVICE_NAME..."
        deploy_infra_services "$SERVICE_NAME"
        exit 0
    fi

    # Check if it's a bootable microservice
    if [[ " ${BOOTABLE_MODULES[@]} " =~ " ${SERVICE_NAME} " ]]; then
        echo "🔄 Reloading microservice: airlines-$SERVICE_NAME..."
        deploy_service "$SERVICE_NAME"
        exit 0
    fi

    echo "❌ Error: airlines-$SERVICE_NAME is not a recognized service!"
    exit 1
fi

# Deploy infrastructure services **in correct order**
deploy_infra_services

# Ensure PostgreSQL is ready before deploying Keycloak
echo "⏳ Waiting for airlines-postgres to be ready..."
wait_for_pod "postgres"

# Ensure Keycloak is ready before deploying Kafka and ELK
echo "⏳ Waiting for airlines-keycloak to be ready..."
wait_for_pod "keycloak"

# Ensure Kafka is ready before microservices start
echo "⏳ Waiting for airlines-kafka to be ready..."
wait_for_pod "kafka"

# Deploy all microservices
for SERVICE in "${BOOTABLE_MODULES[@]}"; do
    deploy_service "$SERVICE"
done

echo "✅ All infrastructure services and microservices have been deployed successfully!"
