package lection2.hw;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestProcessor {
    public static void runTest(Class<?> testClass) {
        final Constructor<?> declaredConstructor;
        try {
            declaredConstructor = testClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Для класса \"" + testClass.getName()
                    + "\" не найден конструктор без аргументов");
        }

        final Object testObj;

        try {
            testObj = declaredConstructor.newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось создать объект класса \"" + testClass.getName() + "\"");
        }

        List<Method> methods = new ArrayList<>();

        for (Method method: testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                checkMethods(method);
                methods.add(method);
            }
        }

        List<Method> beforeEachMethods = new ArrayList<>();
        List<Method> afterEachMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();

        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeEach.class)) {
                beforeEachMethods.add(method);
            } else if (method.isAnnotationPresent(AfterEach.class)) {
                afterEachMethods.add(method);
            } else if (method.isAnnotationPresent(Test.class)) {
                checkMethods(method);
                testMethods.add(method);
            }
        }

        beforeEachMethods.sort(Comparator.comparingInt(it -> it.getAnnotation(Test.class).order()));
        afterEachMethods.sort(Comparator.comparingInt(it -> it.getAnnotation(Test.class).order()));
        testMethods.sort(Comparator.comparingInt(it -> it.getAnnotation(Test.class).order()));

        for (Method testMethod : testMethods) {
            beforeEachMethods.forEach(it -> runTest(it, testObj));
            runTest(testMethod, testObj);
            afterEachMethods.forEach(it -> runTest(it, testObj));
        }

        }

        private static void checkMethods(Method method) {
            if (!method.getReturnType().isAssignableFrom(void.class) || method.getParameterCount() != 0) {
                throw new IllegalArgumentException("Метод \"" + method.getName()
                        + "\" должен быть void и не иметь аргументов");
            }
        }

        private static void runTest(Method testMethod, Object testObj) {
            try {
                testMethod.invoke(testObj);
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException("Не удалось запустить тестовый метод \"" + testMethod.getName() + "\"");
            }
        }
}
