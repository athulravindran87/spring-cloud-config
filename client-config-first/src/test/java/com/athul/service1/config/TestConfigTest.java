package com.athul.service1.config;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class TestConfigTest {

    private TestConfig testObj;

    @Before
    public void setUp() {
        this.testObj = new TestConfig();
    }

    @Test
    public void testEquals() {

        TestConfig test1Config = new TestConfig();
        assertEquals(this.testObj, test1Config);
    }

    @Test
    public void testHashCode() {
        assertThat(this.testObj.hashCode(), equalTo(6061));
    }

    @Test
    public void testToString() {
        assertNotNull(this.testObj.toString());
    }

    @Test
    public void testData() {

        this.testObj.setName("somename");
        this.testObj.setPhone("12345");

        assertThat(this.testObj.getName(), equalTo("somename"));
        assertThat(this.testObj.getPhone(), equalTo("12345"));
    }
}