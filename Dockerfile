FROM openjdk:11

RUN mkdir /code

COPY . /code

WORKDIR /code

RUN mkdir -p /apps/bin

RUN ./gradlew clean build && cp -a /code/build/libs/coronainsights-0.0.1.jar /apps/bin/

EXPOSE 8080

CMD ["java", "-jar", "/apps/bin/coronainsights-0.0.1.jar"]