FROM openjdk:17-jdk-slim
WORKDIR /app
RUN apt-get update && apt-get install -y findutils
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build
CMD ["java", "-jar", "build/libs/injectionJava-0.0.1-SNAPSHOT.jar"]
