package lection2.hw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;

//Расширить пример с запуском тестов следующими фичами:
//1. Добавить аннотации BeforeEach, AfterEach,
//которые ставятся над методами void без аругментов и запускаются ДО и ПОСЛЕ всех тестов соответственно.
//2. В аннотацию Test добавить параметр order() со значением 0 по умолчанию.
//Необходимо при запуске тестов фильтровать тесты по этому параметру (от меньшего к большему).
//Т.е. если есть методы @Test(order = -2) void first, @Test void second, Test(order = 5) void third,
//то порядок вызовов first -> second -> third
//3.* Добавить аннотацию @Skip, которую можно ставить над тест-методами. Если она стоит - то тест не запускается.
//4.* При наличии идей, реализовать их и написать об этом в комментарии при сдаче.
public class Homework {

    public static void main(String[] args) {
        TestProcessor.runTest(MyTest.class);
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    static class MyTest {
        @BeforeEach
        @Test
        void beforeAfterMethod() {
            System.out.println("BeforeEach method");
        }

        @AfterEach
        @Test
        void afterEach() {
            System.out.println("After method");
        }

        @Test(order = -2)
        void firstTest() {
            System.out.println("firstTest is running!");
        }

        @Test
        void secondTest() {
            System.out.println("secondTest is running!");
        }

        @Test(order = 5)
        void thirdTest() {
            System.out.println("thirdTest is running!");
        }
    }
}
