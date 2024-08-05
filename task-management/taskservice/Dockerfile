FROM maven:3.8.3-openjdk-17 as build
WORKDIR /app
COPY . /app/
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk
COPY --from=build /app/target/taskservice-0.0.1.jar /app/taskservice.jar
CMD java -jar /app/taskservice.jar