
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . /app
RUN javac NetworkMonitor.java
CMD ["java", "NetworkMonitor"]
