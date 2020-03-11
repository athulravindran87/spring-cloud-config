# Spring Cloud Config Server - Auto Refresh using Apache Kafka
A Spring Cloud Config Server - A demonstration of Discovery first and Config first.

## Stack:                   	         
1) Eureka Discovery Service.	         	             
2) Cloud Config Server                          
3) Spring Boot 2
4) Docker
5) Apache Kafka


| Service Name           | port | Comments                       |  
| -----------------------| -----| -------------------------------|
| discovery-server       | 8761 | Eureka discovery server        |
| config-server          | 8762 | Cloud Config Server            |
| client-config-first    | 8763 | client service instance 1      |
| client-discovery-first | 8764 | client service instance 2      |

## What's in here ??
This project is a working example of Spring Cloud Config Server with 2 client services and auto refresh using Spring Cloud Bus and Apache Kafka deployed on Kubernetes.

1) client-config-first - This service connects to Config server on bootstrap, obtains the location of Eureka server to register itself along with app specific properties from yml.
2) client-discovery-first - This service connects to Eureka server first and discovers config server's location to obtain its properties.

## How does it work

Spring Cloud Config server offers two ways of its clients to connect to Config server. Config first and Discovery first..

### Config first 

Config servers client applications connect to Config server first on bootstrap and obtains rest of the properties need for it to function.
You can also add more properties in the bootstrap but that defeats the whole purpose of having a centralized config server.

```
spring:
  application:
    name: client-config-first
  profiles:
    active: local
  cloud:
    config:
      uri: ${CONFIG_URI:http://localhost:8888}
      fail-fast: true
      retry:
        max-attempts: 20
        max-interval: 15000
        initial-interval: 10000
```     

Lets breakdown the above configuration.
1. cloud.config.uri         - this is fixed and known url of your config server.
2. cloud.discovery.enabled  - by default false. So I haven't included it here.
3. cloud.fail-fast          - true, if you want your service to fail and retry if config server is not available yet to connect.
4. cloud.retry.max-attempt  - Maximum attempts this service would retry to connect to config.
5. cloud.retry.max-interval - interval time between every retry.
6. cloud.retry.initial-interval - interval time for the first retry.

### Discovery first

Config server's client applications connect to Discovery server first on bootstrap and registers itself. Then it discovers config servers location from Eureka
and connects to config server to obtain its properties.

```
spring:
  application:
    name: client-discovery-first
  profiles:
    active: local
  cloud:
    config:
      fail-fast: true
      retry:
        max-attempts: 20
        max-interval: 15000
        initial-interval: 10000
      discovery:
        enabled: true
        service-id: config-server
server:
  port: 8764

eureka:
  client:
    enabled: true
    register-with-eureka: true
    fetch-registry: true
    serviceUrl:
      defaultZone: ${EUREKA_URI:http://localhost:8761/eureka}
```     
Lets breakdown the above configuration.
1. cloud.discovery.enabled          - true, this is the most important configuration.
2. cloud.discovery.service-id       - spring.application.name of your config server. This is the name config server is registered with Eureka.
3. eureka.client.enabled            - true
4. eureka.client.serviceUrl.defaultZone - Fixed url of Eureka server.

Please follow the link on medium.com for Auto refresh using Kafka and Spring Cloud Bus
