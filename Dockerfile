##creates a base image  where the other images are going to buiilt on
##FROM specifies the base image from which you'll build your new image
#FROM amd64/maven:3.8.6-openjdk-11
## sets the working ddirectory for subsequent instructions
#WORKDIR usr/app
##copies files and directories from the local file system to the image
#COPY  .  .
##Run the jar file with Entrypoint
##specifies the default the executable container
#ENTRYPOINT ["mvn","spring-boot:run"]