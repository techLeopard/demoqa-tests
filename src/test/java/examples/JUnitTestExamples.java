package examples;

import org.junit.jupiter.api.*;

public class JUnitTestExamples {

    @BeforeAll
    static void beforeAll(){
        System.out.println("Here is beforeAll()");
    }

    @BeforeEach
    void beforeEach(){
        System.out.println("Here is beforeEach()");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("Here is afterAll()");
    }

    @AfterEach
    void afterEach(){
        System.out.println("Here is afterEach()");
    }

    @Test
    void firstTest(){
        System.out.println("Here is the first test!");
    }

    @Test
    void secondTest() {
        System.out.println("Here is the second test!");
    }
}
