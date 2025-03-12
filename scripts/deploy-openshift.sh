#!/bin/bash

# Set namespace/project
NAMESPACE="airlines-backend-microservices"

# Define infrastructure services (Kafka, Keycloak, ELK)
INFRA_SERVICES=(
    "keycloak"
    "elk"
    "kafka"
)

# Define microservices to deploy (these depend on infrastructure)
BOOTABLE_MODULES=(
    "tourist"
    "ticket"
    "flight"
    "mail-sender"
    "ticket-pdf-generator"
)

# OpenShift registry URL (CRC default)
REGISTRY_URL="default-route-openshift-image-registry.apps-crc.testing"

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

# Function to deploy a single service
deploy_service() {
    SERVICE=$1
    echo "🚀 Deploying microservice: $SERVICE..."

    for FILE in configmap.yaml secret.yaml deployment.yaml service.yaml route.yaml; do
        FILE_PATH="$SERVICE/$FILE"
        if [ -f "$FILE_PATH" ]; then
            echo "📜 Applying $FILE for $SERVICE..."
            oc apply -f "$FILE_PATH"
        else
            echo "⚠️ Warning: $FILE not found for $SERVICE, skipping..."
        fi
    done

    # Ensure the service exists before exposing the route
    oc get service airlines-$SERVICE -n $NAMESPACE &>/dev/null
    if [ $? -ne 0 ]; then
        echo "❌ Service for $SERVICE does not exist, skipping route exposure."
        return
    fi

    # Expose an OpenShift Route if it doesn't exist
    oc get route airlines-$SERVICE -n $NAMESPACE &>/dev/null
    if [ $? -ne 0 ]; then
        echo "🌍 Exposing OpenShift Route for $SERVICE..."
        oc expose service airlines-$SERVICE -n $NAMESPACE
    fi
}

# Function to deploy infrastructure services
deploy_infra_services() {
    for SERVICE in "${INFRA_SERVICES[@]}"; do
        echo "🚀 Deploying infrastructure service: $SERVICE..."

        SERVICE_PATH="openshift/$SERVICE"

        if [ -d "$SERVICE_PATH" ]; then
            for FILE in deployment.yaml service.yaml route.yaml; do
                FILE_PATH="$SERVICE_PATH/$FILE"
                if [ -f "$FILE_PATH" ]; then
                    echo "📜 Applying $FILE for $SERVICE..."
                    oc apply -f "$FILE_PATH"
                else
                    echo "⚠️ Warning: $FILE not found for $SERVICE, skipping..."
                fi
            done

            # Ensure the service exists before exposing the route
            oc get service $SERVICE -n $NAMESPACE &>/dev/null
            if [ $? -ne 0 ]; then
                echo "❌ Service for $SERVICE does not exist, skipping route exposure."
                continue
            fi

            # Expose an OpenShift Route if it doesn't exist
            oc get route $SERVICE -n $NAMESPACE &>/dev/null
            if [ $? -ne 0 ]; then
                echo "🌍 Exposing OpenShift Route for $SERVICE..."
                oc expose service $SERVICE -n $NAMESPACE
            fi
        else
            echo "⚠️ Warning: Directory $SERVICE_PATH not found, skipping deployment."
        fi
    done
}

# Wait function for infrastructure services
wait_for_pod() {
    local SERVICE=$1
    local MAX_RETRIES=30  # Wait up to 5 minutes
    local RETRY=0

    while [[ $(oc get pods -n $NAMESPACE -l app=$SERVICE -o jsonpath='{.items[0].status.phase}' 2>/dev/null) != "Running" ]]; do
        if [ $RETRY -ge $MAX_RETRIES ]; then
            echo "❌ Timeout: $SERVICE did not start successfully."
            exit 1
        fi
        echo "⏳ Waiting for $SERVICE to start... ($RETRY/$MAX_RETRIES)"
        sleep 10
        ((RETRY++))
    done
    echo "✅ $SERVICE is now running!"
}

# If a specific microservice is provided as an argument, only deploy that service
if [ -n "$1" ]; then
    SERVICE_NAME="$1"

    # Check if it's an infrastructure service
    if [[ " ${INFRA_SERVICES[@]} " =~ " ${SERVICE_NAME} " ]]; then
        echo "🔄 Reloading infrastructure service: $SERVICE_NAME..."
        deploy_infra_services "$SERVICE_NAME"
        exit 0
    fi

    # Check if it's a bootable microservice
    if [[ " ${BOOTABLE_MODULES[@]} " =~ " ${SERVICE_NAME} " ]]; then
        echo "🔄 Reloading microservice: $SERVICE_NAME..."
        deploy_service "$SERVICE_NAME"
        exit 0
    fi

    echo "❌ Error: $SERVICE_NAME is not a recognized service!"
    exit 1
fi

# Deploy infrastructure services first
deploy_infra_services

# Wait for infrastructure services before deploying microservices
echo "⏳ Waiting for infrastructure services to become ready..."
wait_for_pod "keycloak"
wait_for_pod "elasticsearch"  # Part of ELK
wait_for_pod "kafka"

# Deploy all microservices
for SERVICE in "${BOOTABLE_MODULES[@]}"; do
    deploy_service "$SERVICE"
done

echo "✅ All infrastructure services and microservices have been deployed successfully!"
