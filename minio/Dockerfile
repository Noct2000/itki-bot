# Stage 1: Use an Alpine image to install the Minio Client
FROM alpine:3.12 as mc-builder

# Install wget and download Minio Client
RUN apk add --no-cache wget && \
    wget https://dl.min.io/client/mc/release/linux-amd64/mc && \
    chmod +x mc

# Stage 2: Use the official Minio image as the base image
FROM minio/minio

# Copy the Minio Client from the previous stage
COPY --from=mc-builder /mc /usr/bin/

# Copy the startup script into the container
COPY create-bucket.sh /usr/bin/

# Make the script executable
RUN chmod +x /usr/bin/create-bucket.sh

# Entry point to run the Minio server and create the bucket
ENTRYPOINT ["/usr/bin/create-bucket.sh"]
