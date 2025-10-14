package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSortGeneric<T> {
    private Comparator<? super T> comparator;

    public void setComparator(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public void quicksort(T[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(array, left, right);
            quicksort(array, left, pivotIndex - 1);
            quicksort(array, pivotIndex + 1, right);
        }
    }

    private int partition(T[] array, int left, int right) {
        T pivot = array[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (compare(array[j], pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[right];
        array[right] = temp;
        return i + 1;
    }


    public void quicksort(List<T> list) {
        if (list == null || list.size() <= 1) {
            return;
        }
        quicksort(list, 0, list.size() - 1);
    }

    // Основной рекурсивный метод сортировки
    private void quicksort(List<T> list, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(list, left, right);
            quicksort(list, left, pivotIndex - 1);
            quicksort(list, pivotIndex + 1, right);
        }
    }

    // Метод разделения
    private int partition(List<T> list, int left, int right) {
        T pivot = list.get(right);
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (compare(list.get(j), pivot) <= 0) {
                i++;
                // Обмен элементов
                T temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // Помещаем pivot на правильную позицию
        T temp = list.get(i + 1);
        list.set(i + 1, list.get(right));
        list.set(right, temp);

        return i + 1;
    }

    // Вспомогательный метод для сравнения
    private int compare(T a, T b) {
        if (comparator != null) {
            // если компаратор существует, то использовать его
            return comparator.compare(a, b);
        } else {
            // использовать безкомпараторную логику
            return ((Comparable<T>) a).compareTo(b);
        }
    }


    public List<T> sorted(List<T> list) {
        if (list == null) {
            return null;
        }
        List<T> sortedList = new ArrayList<>(list);
        quicksort(sortedList);
        return sortedList;
    }
}