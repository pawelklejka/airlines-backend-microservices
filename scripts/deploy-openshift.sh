#!/bin/bash

# Set namespace/project
NAMESPACE="airlines-backend-microservices"

# Define microservices to deploy
BOOTABLE_MODULES=(
    "tourist"
    "ticket"
    "flight"
    "mail-sender"
    "ticket-pdf-generator"
    "gateway"
    "config-service"
)

# OpenShift registry URL (CRC default)
REGISTRY_URL="default-route-openshift-image-registry.apps-crc.testing"

# Ensure we are logged into OpenShift
echo "Logging into OpenShift..."
oc whoami &>/dev/null
if [ $? -ne 0 ]; then
    echo "Not logged into OpenShift! Run 'oc login' first."
    exit 1
fi

# Ensure the OpenShift project exists
echo "Creating/OpenShift namespace: $NAMESPACE"
oc new-project $NAMESPACE --skip-config-write &>/dev/null || oc project $NAMESPACE

# Loop through all services and deploy them
for SERVICE in "${BOOTABLE_MODULES[@]}"; do
    echo "Deploying $SERVICE..."

    IMAGE_TAG="$REGISTRY_URL/$NAMESPACE/airlines-$SERVICE:latest"
    DEPLOYMENT_YML="services/$SERVICE/deployment.yml"

    # Create ImageStream if not exists
    oc get is airlines-$SERVICE -n $NAMESPACE &>/dev/null
    if [ $? -ne 0 ]; then
        echo "Creating ImageStream for $SERVICE..."
        oc create imagestream airlines-$SERVICE -n $NAMESPACE
    fi

    # Apply deployment.yml from the module if it exists
    if [ -f "$DEPLOYMENT_YML" ]; then
        echo "Applying deployment.yml for $SERVICE..."
        oc apply -f "$DEPLOYMENT_YML"
    else
        echo "Warning: deployment.yml not found for $SERVICE, skipping..."
    fi

    # Expose Service if not exists
    oc get service airlines-$SERVICE -n $NAMESPACE &>/dev/null
    if [ $? -ne 0 ]; then
        echo "Exposing service for $SERVICE..."
        oc expose deployment airlines-$SERVICE --port=8080 -n $NAMESPACE
    fi

    # Expose OpenShift Route
    oc get route airlines-$SERVICE -n $NAMESPACE &>/dev/null
    if [ $? -ne 0 ]; then
        echo "Creating OpenShift route for $SERVICE..."
        oc expose service airlines-$SERVICE -n $NAMESPACE
    fi
done

echo "All microservices have been deployed successfully!"
