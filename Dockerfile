FROM tomcat:11.0.6-jdk21

ARG WAR_FILE
ARG CONTEXT

# Copia o WAR
COPY ${WAR_FILE} /usr/local/tomcat/webapps/${CONTEXT}.war

# Cria um usuário com permissões mínimas
RUN useradd -r -u 1001 -g root -d /home/tomcat tomcatuser && \
    mkdir -p /home/tomcat && \
    chown -R tomcatuser:root /usr/local/tomcat /home/tomcat

# Troca para o novo usuário
USER tomcatuser

# Define o diretório de trabalho
WORKDIR /usr/local/tomcat
