FROM openjdk:14.0.1-jdk-slim AS builder
WORKDIR /app
COPY . /app
RUN ./gradlew build

FROM openjdk:14.0.1-jdk-slim
WORKDIR /app
COPY --from=builder /app/build/libs/shibainu-bot-3.6.0-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","shibainu-bot-3.6.0-SNAPSHOT.jar"]
