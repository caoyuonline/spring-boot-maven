FROM docker.io/relateiq/oracle-java8
VOLUME /tmp
ADD mq-producer-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 14004
