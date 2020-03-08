package com.athul.spring.configserver;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ConfigServerApplication.class},
        properties = {"eureka.client.enabled=false", "spring.cloud.bus.enabled=false"},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@ActiveProfiles("native")
public class ApplicationIT {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate;

    @BeforeClass
    public static void setUp() throws Exception {
        Thread.sleep(10000);
    }

    @Test
    public void testConfigServerIsUpAndCheckPayrollProperties() throws Exception {

        restTemplate = new RestTemplate();

        ResponseEntity<String> result = restTemplate.getForEntity(getHost() + "/client-config-first/local", String.class);
        assertThat(result.getBody(), notNullValue());
        assertThat(result.getBody(), containsString("server.port"));
        assertThat(result.getBody(), containsString("8763"));
    }

    private String getHost() {
        return "http://localhost:" + port;
    }
}
