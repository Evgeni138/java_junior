package lesson1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class LambdaMethodsExample {

    public static void main(String[] args) {
//        void run(); -> Runnable
//        T get(); -> Supplier
//        void accept(T value); -> Consumer
//        R apply(T value); -> Function

        Runnable runnable = () -> System.out.println(ThreadLocalRandom.current().nextInt(100));
        for (int i = 0; i < 10; i++) {
            runnable.run();
        }

        Function<String, Integer> stringLengthExtractor = arg -> arg.length();
        System.out.println(stringLengthExtractor.apply("java"));

        Function<String, String> f = it -> it.toUpperCase();

        System.out.println(f.apply("Inna"));

        System.out.println();

        Function<String, Integer> argLength = LambdaMethodsExample::stringLengthExtractor2;
        System.out.println(argLength.apply("oooochenDlinnoeSlovo"));

        System.out.println();
        List<String> strings = new ArrayList<>(List.of("java", "c#", "c++", "python", "kotlin", "perl", "pascal"));
        strings.sort(LambdaMethodsExample::myComparator);
        System.out.println(strings);

        System.out.println();

        Supplier<Integer> javaLengthGetter = "java"::length;
        System.out.println(javaLengthGetter.get());

        // String -> boolean  - Predicate
        Predicate<String> isBestLanguage = "java"::equals;
        System.out.println(isBestLanguage.test("java"));
        System.out.println(isBestLanguage.test("python"));

        System.out.println();

        Supplier<Person> personGenerator = Person::new;

        Person person = personGenerator.get();
        System.out.println(person);

        person = personGenerator.get();
        System.out.println(person);

        System.out.println();

        List<Integer> generatedList = Stream.generate(() -> ThreadLocalRandom.current().nextInt(1000))
                .limit(100).toList();
        generatedList.forEach(System.out::println);
    }

    static Integer stringLengthExtractor2(String arg) {
        return arg.length();
    };

    static int myComparator(String a, String b) {
        return a.length() - b.length();
    }

    public static class Person {
        private static long counter = 1L;
        private String name;

        private Supplier<String> wordGenerator;

        public Person() {
            name = "Person " + counter++;
            this.wordGenerator = this::generateNextWord;
        }

        public void saySomething() {
            wordGenerator.get();
        }

        private String generateNextWord() {
            return "EFFECTIVE RANDOM WORD";
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
