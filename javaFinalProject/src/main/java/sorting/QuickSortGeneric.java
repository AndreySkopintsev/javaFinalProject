package sorting;


import java.util.Comparator;

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

    private int compare(T a, T b) {
        if (comparator != null) {
            //если компаратор существует, то использовать его
            return comparator.compare(a, b);
        } else {
            //использовать безкомпараторную логику
            return ((Comparable<T>) a).compareTo(b);
        }
    }
}