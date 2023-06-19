FROM node:18
RUN npm install -g --unsafe-perm node-red

# Copy flows and settings
COPY config/* /root/.node-red/

# Install required dependencies
WORKDIR /root/.node-red
RUN npm i

CMD ["node-red"]
