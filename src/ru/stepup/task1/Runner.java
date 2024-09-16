package ru.stepup.task1;

import ru.stepup.task1.anno.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Runner {
    public static void start(String className) throws Exception {
        Class<?> clazz = Class.forName(className);
        Object instance = clazz.getDeclaredConstructor().newInstance();

        Method[] methods = clazz.getDeclaredMethods();

        Method before = null;
        Method after = null;
        Map<Integer, ArrayList<Method>> priorityTests = new HashMap<>();
        Map<Method, Object[]> csvTests = new HashMap<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (!Modifier.isStatic(method.getModifiers())) {
                    throw new RuntimeException("BeforeSuite method should be static");
                }
                if (before != null) {
                    throw new RuntimeException("BeforeSuite is already defined");
                }
                before = method;
            } else if (method.isAnnotationPresent(AfterSuite.class)) {
                if (!Modifier.isStatic(method.getModifiers())) {
                    throw new RuntimeException("AfterSuite method should be static");
                }
                if (after != null) {
                    throw new RuntimeException("AfterSuite is already defined");
                }
                after = method;
            } else if (method.isAnnotationPresent(Test.class)) {
                int priority = method.getAnnotation(Test.class).priority();
                priorityTests.computeIfAbsent(priority, k -> new ArrayList<>()).add(method);
            } else if (method.isAnnotationPresent(CsvSource.class)) {
                if (method.getParameterCount() == 0) {
                    throw new RuntimeException("CsvSource method should have parameters");
                }
                String[] values = method.getAnnotation(CsvSource.class).value().split(",");
                if (values.length != method.getParameterCount()) {
                    throw new RuntimeException("CsvSource method should have the same number of parameters as values");
                }
                Object[] params = new Object[values.length];
                for (int i = 0; i < values.length; i++) {
                    if (method.getParameterTypes()[i] == int.class) {
                        params[i] = Integer.parseInt(values[i].trim());
                    } else if (method.getParameterTypes()[i] == boolean.class) {
                        params[i] = Boolean.parseBoolean(values[i].trim());
                    } else {
                        params[i] = values[i].trim();
                    }
                }
                csvTests.put(method, params);
            }
        }

        if (before != null) {
            before.invoke(null);
        }

        for (Map.Entry<Integer, ArrayList<Method>> entry : priorityTests.entrySet()) {
            for (Method method : entry.getValue()) {
                method.invoke(instance);
            }
        }

        for (Map.Entry<Method, Object[]> entry : csvTests.entrySet()) {
            entry.getKey().invoke(instance, entry.getValue());
        }

        if (after != null) {
            after.invoke(null);
        }
    }

    public static void main(String[] args) {
        try {
            Runner.start("ru.stepup.task1.TestClass");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
