package com.jiangnan.www.springboot03admin;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

@DisplayName("junit5功能测试类")
public class Junit5Test {


    // @BeforeAll 注解的方法
    @BeforeAll
    static void beforeAll() {
        System.out.println("所有测试就要开始了... (@BeforeAll)");
    }

    // @BeforeEach 注解的方法
    @BeforeEach
    void beforeEach() {
        System.out.println("测试就要开始了... (@BeforeEach)");
    }

    // 测试方法1
    @Test
    void test1() {
        System.out.println("执行测试方法1");
    }

    // 测试方法2
    @Test
    void test2() {
        System.out.println("执行测试方法2");
    }

    // @AfterEach 注解的方法
    @AfterEach
    void afterEach() {
        System.out.println("测试结束了... (@AfterEach)");
    }

    // @AfterAll 注解的方法
    @AfterAll
    static void afterAll() {
        System.out.println("所有测试以及结束了... (@AfterAll)");
    }

    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
    void palindromes(String candidate) {
        System.out.println("candidate = " + candidate);
    }


    @Disabled
    @DisplayName("测试方法2")
    @Test
    void deisabledTest() {
        System.out.println(2);
    }


    @RepeatedTest(5)
    void repeatedTest() {
        System.out.println(5);
    }

    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
    @Test
    void testTimeout() throws InterruptedException {
        Thread.sleep(600);
    }

}
