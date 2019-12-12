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

It also contains the API Rest Controllers, which are domain specific.
Currently the provided support is for SonarQube APIs, and replicates the same interfaces (only very few parameters are skipped):

*  __Component Controller:__ provides the ``/components/search`` and ``/components/show`` endpoints
*  __Rule Controller:__ provides the ``/rules/search`` and ``/rules/show`` endpoints.
*  __Issue Controller:__ provides the ``/issues/search/`` endpoint.

More details about the parameters can be found on the official documentation here: [https://next.sonarqube.com/sonarqube/web_api](https://next.sonarqube.com/sonarqube/web_api)
