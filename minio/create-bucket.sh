#!/bin/sh

# Start Minio server in the background
minio server /data --console-address ":9001" &

# Wait for Minio server to be up
while ! curl -s http://localhost:9000/minio/health/live; do
  echo "Waiting for Minio server..."
  sleep 3
done

# Configure Minio client with the Minio server
mc alias set myminio http://localhost:9000 $MINIO_ACCESS_KEY $MINIO_SECRET_KEY

# Create the bucket
mc mb myminio/my-data

# Keep the container running
tail -f /dev/null
