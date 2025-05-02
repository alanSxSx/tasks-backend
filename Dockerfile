FROM tomcat:11.0.6-jdk21

ARG WAR_FILE
ARG CONTEXT

COPY ${WAR_FILE} /usr/local/tomcat/webapps/${CONTEXT}.war

RUN useradd -r -u 1001 -g root -d /home/tomcat tomcatuser && \
    mkdir -p /home/tomcat && \
    chown -R tomcatuser:root /usr/local/tomcat /home/tomcat

USER tomcatuser

WORKDIR /usr/local/tomcat

CMD ["catalina.sh", "run"]
