FROM daocloud.io/java:8

VOLUME /tmp
RUN pwd && ls -l ./
ADD target/easy-url-1.0.0.jar  /app.jar

ENV TZ=Asia/Shanghai JAVA_OPTS="-Xms256m -Xmx1024m"
RUN ln -snf /usr/share/zoneinfo/$TZ  /etc/localtime && echo $TZ > /etc/timezone


ENTRYPOINT java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar