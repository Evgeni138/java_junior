package lesson1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(List.of("java", "c#", "c++", "python", "kotlin", "perl", "pascal"));
        System.out.println(strings);

        // Применяется анонимный класс
//        strings.sort(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                if (o1.length() < o2.length()) {
//                    return -1;
//                } else if (o1.length() > o2.length()) {
//                    return 1;
//                }
//                return 0;
//            }
//        });

        // Лямбда выражение
        strings.sort((o1, o2) -> o1.length() - o2.length());

        System.out.println(strings);

        MyInterface myInterface = () -> ThreadLocalRandom.current().nextInt(10);
        int result = myInterface.foo();
        System.out.println(result);

        MyInterface2 myInterface2 = arg -> System.out.println(arg);

        myInterface2.foo("abcde");


    }

    static class StringLengthComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1.length() < o2.length()) {
                return -1;
            } else if (o1.length() > o2.length()) {
                return 1;
            }
            return 0;
        }
    }

    interface MyInterface {
        int foo();
    }

    interface MyInterface2 {
        void foo(String arg);
    }
}
