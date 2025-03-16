# Use Tomcat as the base image
FROM tomcat:9.0

# Set working directory in the container
WORKDIR /usr/local/tomcat/webapps/

# Copy your app files into the Tomcat webapps directory
COPY web/ .

# Expose port 8080 for Railway
EXPOSE 8080

# Start Tomcat server
CMD ["catalina.sh", "run"]
