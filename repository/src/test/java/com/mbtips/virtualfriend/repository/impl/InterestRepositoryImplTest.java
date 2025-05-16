package com.mbtips.virtualfriend.repository.impl;

import com.mbtips.TestConfiguration;
import com.mbtips.virtualfriend.interfaces.InterestRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfiguration.class)
@ActiveProfiles("test")
class InterestRepositoryImplTest {

    @Autowired
    InterestRepository interestRepository;

    @BeforeAll
    static void setup() {
        System.setProperty("spring.profiles.active", "local");
        System.setProperty("jasypt.encryptor.password", "API-MBTips");
    }

    @DisplayName("test")
    @Test
    void test() {
        System.out.println("테스트");
    }
}