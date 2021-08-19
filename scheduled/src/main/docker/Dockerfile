#设置镜像基础，jdk8
FROM java:8

VOLUME /tmp
ADD *.jar app.jar
RUN bash -c 'touch /app.jar'
#设置镜像对外暴露端口
EXPOSE 8082
#执行启动命令
ENTRYPOINT ["java", "-jar","/app.jar"]