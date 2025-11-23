package com.czdgyy.czyjy.admin;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = { "spring.profiles.active=test"})
public class BaseTest {

    @BeforeEach
    public void init() {

    }


}
