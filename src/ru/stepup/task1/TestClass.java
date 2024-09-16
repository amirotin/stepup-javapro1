package ru.stepup.task1;

import ru.stepup.task1.anno.AfterSuite;
import ru.stepup.task1.anno.BeforeSuite;
import ru.stepup.task1.anno.CsvSource;
import ru.stepup.task1.anno.Test;

public class TestClass {
    @Test(priority = 8)
    public void test1() {
        System.out.println("Test 1");
    }

    @Test
    public void test2() {
        System.out.println("Test 2");
    }

    @Test(priority = 3)
    public void test3() {
        System.out.println("Test 3");
    }

    @Test(priority = 8)
    public void test4() {
        System.out.println("Test 4");
    }

    @BeforeSuite
    public static void before() {
        System.out.println("Before");
    }

    @AfterSuite
    public static void after() {
        System.out.println("After");
    }

    @CsvSource("1, Abc, 3, false")
    public void testcsv(int a, String b, int c, boolean d) {
        System.out.println("Testcsv: " + a + " " + b + " " + c + " " + d);
    }
}
