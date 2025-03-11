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

for SERVICE in "${BOOTABLE_MODULES[@]}"; do
    echo "🚀 Deploying $SERVICE..."

    # Adjusted file paths to include correct `.yaml` extension
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
        continue
    fi

    # Check if the service already has an OpenShift Route
    oc get route airlines-$SERVICE -n $NAMESPACE &>/dev/null
    if [ $? -ne 0 ]; then
        echo "🌍 Exposing OpenShift Route for $SERVICE..."
        oc expose service airlines-$SERVICE -n $NAMESPACE
    fi
done


echo "✅ All microservices have been deployed successfully!"
