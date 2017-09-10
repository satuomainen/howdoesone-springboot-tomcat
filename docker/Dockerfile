FROM tomcat:8.0.46-jre8-alpine

# The RUN command runs commands inside the container while setting up

# Remove webapps that come with Tomcat
RUN rm -r /usr/local/tomcat/webapps/examples
RUN rm -r /usr/local/tomcat/webapps/host-manager
RUN rm -r /usr/local/tomcat/webapps/ROOT

# Leave /docs in place to help development
#RUN rm -r /usr/local/tomcat/webapps/docs

# Leave /manager there as well until development is done. See 
# also the bit 
#RUN rm -r /usr/local/tomcat/webapps/manager

# Install any extra SW required by the webapp (apk would be the Alpine 
# version of apt-get)
#RUN apt-get update && apt-get install -y \
#  wkhtmltopdf

# The COPY command insert files from this directory (and below)
# to the image. See also ADD command in Dockers docs.

# Connector ports and stuff maybe needs to be configured. Then
# copy the server.xml from the container in this directory and
# edit it. To get a copy of the server.xml as a starting point
# from a running container, say:
# `docker cp <container-id>:/usr/local/tomcat/conf/server.xml ./server.xml`
#COPY server.xml /usr/local/tomcat/conf/

# To enable access to /manager app, create a user admin/admin.
# Might be a good idea to remove this before deploying outside
# own computer.
COPY ./appconfig/tomcat-users.xml /usr/local/tomcat/conf/

# Get the build process to output the WAR file to this directory,
# then copy it into the container and have Tomcat deploy it.
COPY ./ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Set environment variables
# ARG "imports" the variable from docker build command's --build-arg into here
# ENV sets the variable name so that it can be used in this Dockerfile
ARG IPINFO_SERVICE_TOKEN
ENV IPINFO_SERVICE_TOKEN=${IPINFO_SERVICE_TOKEN}
ARG WUNDERGROUND_API_KEY
ENV WUNDERGROUND_API_KEY=${WUNDERGROUND_API_KEY}

# The VOLUME command creates mount point for a host dir in the
# container.
#VOLUME basicdata/
