#Building 

FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTest package

#Runtime stage
FROM eclipse-temurin:17-jre
RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

WORKDIR /opt/app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
#ENV PORT 8080

ENTRYPOINT ["java","-jar", "app.jar"] 

