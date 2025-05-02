FROM tomcat:11.0.6-jdk21

ARG WAR_FILE
ARG CONTEXT

COPY ${WAR_FILE} /usr/local/tomcat/webapps/${CONTEXT}.war

WORKDIR /usr/local/tomcat

CMD ["catalina.sh", "run"]
