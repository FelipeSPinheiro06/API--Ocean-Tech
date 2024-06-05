FROM eclipse-temurin:17-jdk

WORKDIR /app

RUN mvnw install

COPY --from=build /target/oceantech-0.0.1-SNAPSHOT.jar oceantech.jar

CMD ["java", "-jar", "oceantech.jar"]