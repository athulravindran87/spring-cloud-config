# Spring Cloud Config Server
A Spring Cloud Config Server - A demonstration of Discovery first and Config first.

[![Build Status](http://34.68.205.106/jenkins/buildStatus/icon?job=hazelcast-cluster-master-build&subject=Master%20Build)](http://34.68.205.106/jenkins/job/hazelcast-cluster-master-build/)       [![Build Status](http://34.68.205.106/jenkins/buildStatus/icon?job=hazelcast-cluster-mutation-test&subject=Mutation%20Test)](http://34.68.205.106/jenkins/job/hazelcast-cluster-mutation-test/)    [![Codacy Badge](https://api.codacy.com/project/badge/Grade/e9e89cc98f5d4b0f9fd80d18c9935981)](https://www.codacy.com?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=athulravindran87/hazelcast-cluster&amp;utm_campaign=Badge_Grade)     [![Quality Gate Status](http://34.67.51.46/api/project_badges/measure?project=com.athul%3Ahazelcast-cluster&metric=alert_status)](http://34.67.51.46/dashboard?id=com.athul%3Ahazelcast-cluster)       [![Bugs](http://34.67.51.46/api/project_badges/measure?project=com.athul%3Ahazelcast-cluster&metric=bugs)](http://34.67.51.46/dashboard?id=com.athul%3Ahazelcast-cluster)    [![Coverage](http://34.67.51.46/api/project_badges/measure?project=com.athul%3Ahazelcast-cluster&metric=coverage)](http://34.67.51.46/dashboard?id=com.athul%3Ahazelcast-cluster)    [![Technical Debt](http://34.67.51.46/api/project_badges/measure?project=com.athul%3Ahazelcast-cluster&metric=sqale_index)](http://34.67.51.46/dashboard?id=com.athul%3Ahazelcast-cluster)   [![Maintainability Rating](http://34.67.51.46/api/project_badges/measure?project=com.athul%3Ahazelcast-cluster&metric=sqale_rating)](http://34.67.51.46/dashboard?id=com.athul%3Ahazelcast-cluster)

## Technical Stack:                   	         
1) Eureka Discovery Service.	         	             
2) Cloud Config Server                          
4) Spring Boot 2
5) Docker
6) Google Cloud - Google Kubernetes Engine

![hazelcast-server (2)](https://user-images.githubusercontent.com/5833938/60470478-90022880-9c2e-11e9-9c0f-cd30afbcd607.jpg)

| Service Name        | port | Comments                       |  
| ------------------- | -----| -------------------------------|
| discover-server     | 8761 | Eureka discovery server        |
| config-server       | 8888 | Cloud Config Server            |
| client-service-1    | 8763 | client service instance 1      |
| client-service-2    | 8764 | client service instance 2      |

## What's in here ??
This project is an working example of hazelcast clustering using Eureka Discovery Service. There are 3 main components as depicted in the picture above. 1) Discovery Server for service registery and service discovery. 2) Hazelcast servers (2) and 3) Hazelcast client. Hazelcast servers are capable of a member joining mechanism who discover each other using hazelcast group name and creates a join. They are also responsible for creation of distributed objects such as map and queues. Hazelcast client joins the hazlecast cluster using Eureka. 

## How to deploy and test ??
1. Run as standalone Spring boot app

Start the services in order...Discovery, Hazelcast server and hazelcast client. Although you can start in any order, but following the mentioned order will gauruantee a clean start. Use the following VM arguments for each
```
- Hazelcast Server : -Dhazelcast.port=5701
- Hazelcast Client : -Deureka.client.props=eureka-client-local -Xms1024m -Xmx2048m
```
   Please note, if you want to start more than one hazelcast-server instances to test member join, then you must also provide    server.port and hazelcast.port for additional instances. 
   `Example: -Dserver.port=8763 -Dhazelcast.port=5702`. 
   Same applies to hazelcast-client as well but client requires `server.port` property only.
   
 2. Running on Docker locally
   
    Execute docker-compose.yaml on your local docker engine. Compose will spin 5 containers. 1 Eureka server,2 hazelcast    
    servers and 2 hazelcast clients. Port numbers are mentioned in the compose file.
    
 3. Running on Google Kubernetes Engine (GKE)
    
    Go thru `commands.txt` file, it is a cheat sheet of `kube` commands required specific to this project.
    
    ##### Pre-requisites
    
    Install the Google Cloud SDK, which includes the `gcloud` command-line tool. Using the gcloud command line tool, install 
    the Kubernetes command-line tool. `kubectl` is used to communicate with   Kubernetes, which is the cluster orchestration 
    system of GKE clusters:

    `gcloud components install kubectl`

    
Use ```/actuator/health``` on hazelcast server to view custom implemented health endpoint to view hazelcast-servers member join.      
    
 ### Test
 
 #### The best way to test is "put" on one hazelcast-client instance and "get" on another hazelcast-client instance. 
 
 There are 2 sets of API endpoints to test
- Map
   - /map/put?key=xx&value=yyy
   - /map/get?key=xx
- Queue
   - /queue/put?value=xx
   - /queue/get
   
 ### Happy Coding !!!! :+1::shipit:  

