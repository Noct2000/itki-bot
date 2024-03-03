FROM node:18.11.0 AS node
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

FROM nginx:latest
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=node /app/dist/frontend /usr/share/nginx/html
EXPOSE 80
