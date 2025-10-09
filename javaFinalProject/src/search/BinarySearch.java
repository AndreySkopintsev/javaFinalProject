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

    public static <T> int search(List<T> list, T key, Comparator<T> comparator) {
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
}
