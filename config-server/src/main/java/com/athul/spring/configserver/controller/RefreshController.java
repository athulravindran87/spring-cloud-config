package com.athul.spring.configserver.controller;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import lombok.Getter;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.utility.Iterate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/")
public class RefreshController {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    @Getter
    private Map<String, String> resultMap;


    @GetMapping("/refresh")
    public Map<String, String> refresh() throws Exception {
        resultMap = new HashMap<>();

        if (Objects.isNull(eurekaClient) || Iterate.isEmpty(eurekaClient.getApplications().getRegisteredApplications())) {
            resultMap.put("Failure", "No Eureka Clients available to refresh");
            return resultMap;
        }

        Lists.adapt(eurekaClient.getApplications().getRegisteredApplications())
                .reject(application -> application.getName().equalsIgnoreCase("config-server"))  //I dont want to refresh myself
                .flatCollect(Application::getInstances)
                .forEach(instanceInfo -> this.invokeClient(instanceInfo));

        return resultMap;

    }

    private void invokeClient(InstanceInfo instanceInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/actuator/refresh", entity, String.class);
        resultMap.put(instanceInfo.getHomePageUrl(), response.getStatusCode().getReasonPhrase());
    }

}
