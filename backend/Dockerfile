FROM openjdk:19-jdk-slim-buster

WORKDIR /app
COPY  lib/fastcgi-lib.jar /app/lib/fastcgi-lib.jar
COPY out/artifacts/web1_jar/web1.jar /app/web1.jar
ENTRYPOINT ["java", "-jar", "web1.jar"]

#FROM openjdk:19-jdk-slim-buster
#WORKDIR /app
#COPY out/artifacts/DrevniyGrek_jar/DrevniyGrek.jar /app/Grek.jar
#ENTRYPOINT ["java", "-jar", "Grek.jar"]

