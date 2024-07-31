FROM openjdk:17
WORKDIR /app
COPY /target/task_manager-0.0.1-SNAPSHOT.jar /app/task_manager.jar
ENTRYPOINT ["java", "-jar", "task_manager.jar"]