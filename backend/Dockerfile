FROM openjdk:19-jdk-slim-buster

WORKDIR /app

COPY  lib/fastcgi-lib.jar /app/lib/fastcgi-lib.jar
COPY out/artifacts/web1_jar/web1.jar /app/web1.jar
EXPOSE 1500
CMD ["java", "-DFCGI_PORT=1500", "-jar", "web1.jar"]

