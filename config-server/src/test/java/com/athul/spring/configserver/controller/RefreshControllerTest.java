package com.athul.spring.configserver.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.eclipse.collections.impl.factory.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class RefreshControllerTest {

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private EurekaClient eurekaClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private Application mockApp1;

    @Mock
    private Application mockApp2;

    @Mock
    private Application mockApp3;

    @Mock
    private InstanceInfo instanceInfo1;

    @Mock
    private InstanceInfo instanceInfo2;

    @Mock
    private InstanceInfo instanceInfo3;

    @Mock
    private InstanceInfo instanceInfo4;

    @InjectMocks
    private RefreshController testObj;

    @Mock
    private ResponseEntity mockEntity;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {

        this.setUpMockApps();

        this.mockMvc = MockMvcBuilders.standaloneSetup(this.testObj).build();

        when(this.mockEntity.getStatusCode()).thenReturn(HttpStatus.OK);

        when(restTemplate.postForEntity(anyString(), any(), eq(String.class)))
                .thenReturn(mockEntity);

        when(this.eurekaClient.getApplications().getRegisteredApplications())
                .thenReturn(Lists.mutable.of(mockApp1, mockApp2, mockApp3));

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void refreshWithMvc() throws Exception {

        this.mockMvc.perform(get("/refresh")).andExpect(status().isOk());
        verify(this.restTemplate, times(4)).postForEntity(anyString(), any(), eq(String.class));
    }

    public void setUpMockApps() {

        when(instanceInfo1.getHostName()).thenReturn("host1");
        when(instanceInfo1.getPort()).thenReturn(8080);
        when(instanceInfo1.getHomePageUrl()).thenReturn("http://host1:8080/env");

        when(instanceInfo2.getHostName()).thenReturn("host2");
        when(instanceInfo2.getPort()).thenReturn(8080);
        when(instanceInfo2.getHomePageUrl()).thenReturn("http://host2:8080/env");

        when(instanceInfo3.getHostName()).thenReturn("host3");
        when(instanceInfo3.getPort()).thenReturn(8080);
        when(instanceInfo3.getHomePageUrl()).thenReturn("http://host3:8080/env");

        when(instanceInfo4.getHostName()).thenReturn("host4");
        when(instanceInfo4.getPort()).thenReturn(8080);
        when(instanceInfo4.getHomePageUrl()).thenReturn("http://host4:8080/env");

        when(this.mockApp1.getName()).thenReturn("client-1");
        when(this.mockApp1.getInstances()).thenReturn(Lists.mutable.of(instanceInfo1, instanceInfo2));

        when(this.mockApp2.getName()).thenReturn("client-2");
        when(this.mockApp2.getInstances()).thenReturn(Lists.mutable.of(instanceInfo3, instanceInfo4));

        when(this.mockApp3.getName()).thenReturn("config-server");

    }
}