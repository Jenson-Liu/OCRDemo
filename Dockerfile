# Docker image for springboot file run
# VERSION 0.0.1
# Author: eangulee
# 基础镜像使用java
FROM openjdk:11
MAINTAINER Jenson Liu
# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /app/demo1
# 将jar包添加到容器中并更名为dockerapp.jar
ADD ./target/demo1-0.0.1-SNAPSHOT.jar demo1.jar
ADD ./cers/ccf-715.cer ccf-715.cer
ADD ./cers/ccf-720.cer ccf-720.cer
EXPOSE 8080
ENV ALLOW_MOCKED_AUTH_HEADER true
ENV destinations [{name:"ErpQueryEndpoint",url:"https://ccf-715.wdf.sap.corp",username:"_SAPI501695",password:"4EBZ##r8u1Y#JP-hbbgx" ,properties:[{key:"TrustAll",value:"true"},{key:"sap-client", value: "715"}]}]
# 运行jar包
#keytool -import -alias ccf-715 -keystore /usr/local/openjdk-11/lib/security/cacerts -file /ccf-715.cer -storepass changeit
#ENTRYPOINT ["keytool", "-import -alias ccf-715 -keystore /usr/local/openjdk-11/lib/security/cacerts -file ./ccf-715.cer -storepass changeit"]
#ENTRYPOINT ["keytool", "-import -alias ccf-720 -keystore /usr/local/openjdk-11/lib/security/cacerts -file ./ccf-720.cer -storepass changeit"]
RUN sh -c '/bin/echo -e "y" | keytool -import -alias ccf-715 -keystore /usr/local/openjdk-11/lib/security/cacerts -file /ccf-715.cer -storepass changeit'
#    && keytool -import -alias ccf-720 -keystore /usr/local/openjdk-11/lib/security/cacerts -file /ccf-720.cer -storepass changeit
RUN bash -c 'touch /demo1.jar'
#ENTRYPOINT ["keytool"," -import -alias ccf-715 -keystore /usr/local/openjdk-11/lib/securitycacerts -file","/ccf-715.cer","-storepass changeit"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/demo1.jar"]
#ENTRYPOINT ["keytool"," -import -alias ccf-715 -keystore /usr/local/openjdk-11/lib/securitycacerts -file","/ccf-715.cer","-storepass changeit"]
#ENTRYPOINT ["keytool"," -import -alias ccf-720 -keystore /usr/local/openjdk-11/lib/securitycacerts -file","/ccf-720.cer","-storepass changeit"]
