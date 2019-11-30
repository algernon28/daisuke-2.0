# Daisuke 2.0
## What is it?
### Brief history
This project was born to bring together several one-shot QA Integration solutions implemented for an Enterprise Customer.   
Those small applications were tailored on specific needs, integrating **Code Static Analysis** with **Continuous Integration**.   
More specifically, the platform were [SonarQube](https://www.sonarqube.org)&trade; and [Jenkins](https://jenkins.io)&trade;.    
The solution spanned between producing CSV reports on the issues of a specific project, global synthesis reports on specific classes of issues (e.g. OWASPTOP10), bulk-changes of issue status (e.g. changing all issues on a CSV list to "won't fix"), updating third-party database tables with custom reports, and so on and so forth.   
The applications were small, but quickly growing in number as the requirements came, even if the differences were often just matter of one or two parameters in a query, therefore a new approach was in order.

### To the point
Daisuke is implemented as a set of microservices, with an API intended to mimic the behavior of the issue management in the back-end, therefore it will be invoked by standard HTTP GET queries.
The first release, which is currently in production is Daisuke 1.0, which is still somewhat monolithic and tailored on SonarQube, this one is intended to be more modular, and easily customizable for other kind of Issue Management systems.

## High-Level architecture
Daisuke 2.0 leverages [Spring Boot](https://spring.io/projects/spring-boot) for its architecture, and it is made of several [Maven](https://maven.apache.org/) modules:  

*  daisuke-2.0: the root module, it contains the references to the submodules, and defines the libraries in common across the modules. It also manages the versions to be used.

*   daisuke-boot-domain: module with the data model interfaces and DTO
*   daisuke-boot-jpa: module with the [Spring JPA](https://spring.io/projects/spring-data-jpa) integration
*   daisuke-boot-sonarqube: module with the integration to SonarQube. It uses the [standard library](https://mvnrepository.com/artifact/org.sonarsource.sonarqube/sonar-plugin-api). It actually uses version 8.0, which is compatible with Java 9+ (Daisuke is built against Java 11)
*   daisuke-boot-application: module with the application entrance and the REST controllers. It depends on the modules above.  

More details on the README in the modules' root.

