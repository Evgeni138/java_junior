package hw1;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * 0.1. Посмотреть разные статьи на Хабр.ру про Stream API
 * 0.2. Посмотреть видеоролики на YouTube.com Тагира Валеева про Stream API
 *
 * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
 * 1.1 Найти максимальное
 * 2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
 * 2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
 */
public class Task1 {
    public static void main(String[] args) {
        List<Integer> listNumbers = ThreadLocalRandom.current()
                .ints(100, 0, 1000000)
                .boxed()
                .collect(Collectors.toList());

        listNumbers.forEach(System.out::println);
        System.out.println();
        System.out.println("Max number: " + listNumbers.stream().max(Integer::compareTo).get());
        System.out.println("The is sum of numbers over then 500000 after its conversions: " + listNumbers.stream()
                .filter(it -> it > 500000).map(it -> it * 5 -150)
                .reduce(0, (total, it) -> total + it));
        System.out.println("The is total of numbers its square less 100000: " + listNumbers.stream()
                .filter(it -> it * it < 100000).count());


    }
}
