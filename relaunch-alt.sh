#!/bin/bash

set -e
set -o pipefail

# Function to display a message
function log_message() {
    echo -e "\033[1;32m$1\033[0m"
}

# Function to handle errors
function handle_error() {
    echo -e "\033[1;31mError occurred: $1\033[0m"
    exit 1
}

# Function to handle cancellation (Ctrl+C)
function handle_cancel() {
    echo -e "\033[1;33mScript canceled by user. Cleaning up...\033[0m"
    docker-compose -f docker-compose.dev.yml down
    exit 130  # 130 is the standard exit code for script termination by SIGINT
}

# Trap SIGINT (Ctrl+C)
trap handle_cancel SIGINT

# Stop and remove containers
log_message "Stopping and removing Docker containers..."
docker-compose -f docker-compose.dev.yml down || handle_error "Failed to stop Docker containers."

# Clean the build
log_message "Running ./gradlew clean..."
if ! ./gradlew clean; then
    handle_error "Gradle clean failed."
fi

# Assemble the project
log_message "Running ./gradlew assemble..."
if ! ./gradlew assemble; then
    handle_error "Gradle assemble failed."
fi

# Build and start Docker containers
log_message "Starting Docker containers with build..."
if ! docker-compose -f docker-compose.dev.yml up --build; then
    handle_error "Failed to start Docker containers."
fi

log_message "All tasks completed successfully!"
