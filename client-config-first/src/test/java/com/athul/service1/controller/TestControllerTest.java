package com.athul.service1.controller;

import com.athul.service1.config.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TestControllerTest {

    @Mock
    private TestConfig config;

    @InjectMocks
    private TestController testObj;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        when(config.getName()).thenReturn("abc");
        when(config.getPhone()).thenReturn("123");
        mockMvc = MockMvcBuilders.standaloneSetup(this.testObj).build();
    }

    @Test
    public void getConfigData() {
        assertThat(this.testObj.getConfigData(), equalTo("Name: abc Phone: 123"));
    }

    @Test
    public void getConfigDataMvc() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Name: abc Phone: 123")));
    }
}