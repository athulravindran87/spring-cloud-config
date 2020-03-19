package com.athul.spring.configserver;

import com.athul.spring.configserver.controller.RefreshControllerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ConfigServerApplicationTests.class,
        RefreshControllerTest.class
})
public class AllTests {
}
