FROM frolvlad/alpine-oraclejdk8:slim
ADD converter-1.0.jar converter.jar
RUN sh -c 'touch /converter.jar'
EXPOSE 8080
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /converter.jar" ]
