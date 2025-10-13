FROM maven:3.9-eclipse-temurin-21 AS base

WORKDIR /app

COPY . .

RUN chmod +x run.sh

ENTRYPOINT ["./run.sh"]
