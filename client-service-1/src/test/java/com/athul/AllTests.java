package com.athul;

import com.athul.service1.ClientService1ApplicationTests;
import com.athul.service1.config.TestConfigTest;
import com.athul.service1.controller.TestControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ClientService1ApplicationTests.class, TestConfigTest.class, TestControllerTest.class})
public class AllTests {
}
