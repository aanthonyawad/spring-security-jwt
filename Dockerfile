FROM tomcat
#add required jars


#add required files


#add war in the end
ADD build/libs/spring-security-jwt-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/security.war


#add entry point

CMD ["catalina.sh", "run"]