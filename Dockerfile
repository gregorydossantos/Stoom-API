FROM openjdk:11
WORKDIR /home/greg/development/projects/java/apirest
COPY /target/apirest.jar /home/greg/development/projects/java/apirest.jar
EXPOSE 8080
CMD java -jar apirest.jar

