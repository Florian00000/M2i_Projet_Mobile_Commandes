FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:jdk-17 AS builder

COPY --chown=quarkus:quarkus mvnw /code/mvnw
COPY --chown=quarkus:quarkus .mvn /code/.mvn
COPY --chown=quarkus:quarkus pom.xml /code/
USER quarkus
WORKDIR /code
COPY src /code/src
RUN ./mvnw -B org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

RUN ./mvnw package -Dnative -Dmaven.test.skip=true

FROM quay.io/quarkus/quarkus-micro-image:2.0

WORKDIR /work/

COPY --from=builder /code/target/*-runner /work/application

RUN chmod 775 /work /work/application \
    && chown -R 1001 /work \
    && chmod -R "g+rwX" /work \
    && chown -R 1001:root /work

EXPOSE 8080
USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]