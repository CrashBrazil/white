FROM eclipse-temurin:25

COPY target/white-0.0.1-SNAPSHOT.jar white.jar

ENTRYPOINT ["java", "-jar","white.jar"]