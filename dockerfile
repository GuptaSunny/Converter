FROM frolvlad/alpine-oraclejdk8:slim
ADD target/converter-1.0.jar converter.jar
RUN sh -c 'touch /converter.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar /converter.jar" ]
