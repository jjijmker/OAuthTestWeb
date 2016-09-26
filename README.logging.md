Add following system VM options to Tomcat launch configuration:

 -Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager -Djava.util.logging.config.file="${workspace_loc}/logging.properties

Use logging.properties from 'src/main/resources'. Copy them to Eclipse workspace root.