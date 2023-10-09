package com.simbirsoft.generators;
/**
 * Класс реализует генерацию тестовых данных
 */

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;

public class TestDataGenerator {
    /**
     * Метод getRandomIntegerList генерирует список случайных чисел тип Integer
     * @param limit количество случайных чисел тип int
     * @return список случайных чисел тип Integer
     */
    public static List<Integer> getRandomIntegerList(int limit) {
        return new Random().ints(limit, 0, 100)
                .boxed()
                .collect(Collectors.toList());
    }

    /**
     * Метод getRandomInt генерирует случайное число тип int
     * @param bound верхняя граница возвращаемого числа тип int
     * @return случайное чисто тип int
     */
    public static int getRandomInt(int bound) {
        return new Random().nextInt(bound);
    }

    /**
     * Метод getDateNowAsString генерирует текущую дату по заданному шаблону тип String
     * @param pattern шаблон даты тип String
     * @return текущая дата по заданному шаблону тип String
     */
    public static String getDateNowAsString(String pattern) {
        return LocalDate.now().format((DateTimeFormatter.ofPattern(pattern, Locale.ENGLISH)));
    }
}
