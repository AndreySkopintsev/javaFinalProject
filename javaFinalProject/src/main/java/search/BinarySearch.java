package search;

import java.util.Comparator;
import java.util.List;

/**
 * Проект: javaFinalProject
 * Класс BinarySearch
 * Автор: Vitaliy Mikhajlov
 */

public class BinarySearch {
    // Приватный конструктор предотвращает создание экземпляров утилитарного класса
    private BinarySearch() {
    }

    public static <T> int search(List<T> list, T key, Comparator<? super T> comparator) {
        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // вычисляем средний индекс
            int cmp = comparator.compare(list.get(mid), key); // сравниваем среднйи элемент с ключом

            if (cmp == 0) {
                return mid; // ключ найден в mid
            }
            if (cmp < 0) {
                left = mid + 1; // ищем в правой половине
            } else {
                right = mid - 1; // ищем в левой половине
            }
        }
        return -1; // ключ не найден в списке
    }

    public static <T> boolean contains(List<T> list, T key, Comparator<? super T> comparator) {
        checkSorted(list, comparator); // проверяем сортировку перед поиском
        return search(list, key, comparator) != -1;
    }

    // Проверка, что список отсортирован по компаратору
    private static <T> void checkSorted(List<T> list, Comparator<? super T> comparator) {
        for (int i = 1; i < list.size(); i++) {
            if (comparator.compare(list.get(i - 1), list.get(i)) > 0) {
                throw new IllegalArgumentException("Список не отсортирован. Сначала отсортируйте список.");
            }
        }
    }
}
