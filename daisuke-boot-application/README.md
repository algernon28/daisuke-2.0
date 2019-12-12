# Daisuke 2.0
## Boot Application
This is the entry point of the application. It can be run from here.
The command line is typically like this: 
```java
java -jar ./daisuke20.jar --spring-config.location=classpath:/daisuke-config.yml
```
Alternatively, if the configuration file is external, it can be specified like this:

```java 
java -jar ./daisuke20.jar --spring.config.location=file://<path>/application.yml 
```


