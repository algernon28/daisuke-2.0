# Daisuke 2.0
## SonarQube Module
This module is intended as integration with SonarQube Server. All the classes here are tailored on the Client library provided by SonarQube.
There are three types of classes here:

*  __SonarQubeClient:__ a wrap around the SonarQube client
*  __Mapper:__ this maps the specific entities from SonarQube onto the domain DTO
*  __Search/Show:__ this models the query to be sent to the service. Service is used for generic searches, while Show is used to find a specific entity by key. This reflects the same structure used by SonarQube APIs
*  __Service:__ This contains the business logic of the searches. It relays the queries to the Client after the required operations and remapping.
