package lesson3;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlatMapDemo {
    public static void main(String[] args) {
//        List<SerializablePerson> people = IntStream.rangeClosed(1,100)
//                .mapToObj(it -> new Department("Department #" + it))
//                .map(it -> new SerializablePerson("Person #" + UUID.randomUUID(), it))
//                .toList();

        List<String> langs = List.of("java", "c#", "c++", "python", "kotlin", "assembler");
        List<Integer> list = langs.stream()
                .flatMap(it -> it.chars().boxed())
                .toList();

        List<Character> characters = langs.stream()
                .flatMapToInt(CharSequence::chars) // Получаем IntStream из символов строки
                .mapToObj(ch -> (char) ch) // Преобразуем int в char
                .collect(Collectors.toList()); // Собираем символы в список

        System.out.println(characters);

    }
}
