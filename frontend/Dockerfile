FROM nginx:stable-alpine

COPY ../config/nginx.conf /etc/nginx/conf.d/default.conf
COPY frontend/ /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
