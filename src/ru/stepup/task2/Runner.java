package ru.stepup.task2;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Runner {
    public static void main(String[] args) {
        List<Integer> list = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);
        System.out.println(removeDuplicates(list));
        System.out.println(findThirdMax(list));
        System.out.println(findThirdUniqueMax(list));

        List<Employee> employees = List.of(
                new Employee("Иван", 25, "Инженер"),
                new Employee("Петр", 30, "Менеджер"),
                new Employee("Сидор", 35, "Директор"),
                new Employee("Макс", 25, "Бухгалтер"),
                new Employee("Федя", 30, "Инженер"),
                new Employee("Вася", 25, "Инженер"),
                new Employee("Коля", 45, "Инженер")
        );
        System.out.println(findThreeOldestEngineer(employees));
        System.out.println(findAverageAge(employees));

        List<String> strings = List.of("a", "aa", "aaa", "aaaa", "aaaaa");
        System.out.println(findMaxLengthString(strings));

        String text = "Java Python Java C++ Python Java C++ Java";
        System.out.println(countWords(text));

        List<String> words = List.of("aa", "ba", "ca", "ccc", "aaa", "bbb", "cc", "a", "b", "c");
        System.out.println(sortWords(words));

        String[] stringsArray = {
                "Java Python Java C++ Assembler",
                "Java C++ Python Java CoffeeScript",
                "Python Java C++ Python Perl",
                "C++ Python Java Python Java",
                "Java UnrealScript Java C++ Python"
        };
        System.out.println(findMaxLengthWord(stringsArray));

    }

    public static <T> List<T> removeDuplicates(List<T> list) {
        return list.stream().distinct().toList();
    }

    public static Integer findThirdMax(List<Integer> list) {
        return list.stream()
                //.sorted((a, b) -> b - a)
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Меньше 3-х элементов"));
    }

    public static Integer findThirdUniqueMax(List<Integer> list) {
        return list.stream()
                .distinct()
                //.sorted((a, b) -> b - a)
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Меньше 3-х элементов"));
    }

    public static List<Employee> findThreeOldestEngineer(List<Employee> employees) {
        return employees.stream()
                .filter(employee -> employee.getPosition().equals("Инженер"))
                //.sorted((a, b) -> b.getAge() - a.getAge())
                .sorted((a, b) -> Integer.compare(b.getAge(), a.getAge()))
                .limit(3)
                .toList();
    }

    public static double findAverageAge(List<Employee> employees) {
        return employees.stream()
                .mapToInt(Employee::getAge)
                .average()
                .orElseThrow(() -> new IllegalArgumentException("Список пуст"));
    }

    public static String findMaxLengthString(List<String> list) {
        return list.stream()
                //.max((a, b) -> a.length() - b.length())
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(() -> new IllegalArgumentException("Список пуст"));
    }

    public static Map<String, Integer> countWords(String text) {
        return Arrays.stream(text.split(" "))
                .collect(Collectors.toMap(Function.identity(), word -> 1, Integer::sum));
    }

    public static List<String> sortWords(List<String> list) {
        return list.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Function.identity()))
                .toList();
    }

    public static String findMaxLengthWord(String[] strings) {
        return Arrays.stream(strings)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(() -> new IllegalArgumentException("Массив пуст"));
    }
}
