package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolSort <T extends Comparable<? super T>> {
    private final QuickSortGeneric<T> quickSorter   = new QuickSortGeneric<>();
    private Comparator<? super T> comparator;

    //Дефолтный конструктор без компаратора
    public ThreadPoolSort(){
        this.comparator = null;
    };

    //Конструктор с компаратором
    public ThreadPoolSort(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }

    public void parallelSort(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array sent is null");
        }

        if (array.length <= 1) {
            return;
        }

        if (comparator != null) {
            quickSorter.setComparator(comparator);
        }

        // Число тредов просто константой поставил 2
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        ArrayList<T[]> arrayOfHalves = splitArrayInHalf(array);
        try {

            //futureExecs - массив типа Future, результат асинхронной работы тредов
            List<Future<?>> futureExecs = new ArrayList<>();
            for (T[] half : arrayOfHalves) {
                Future<?> future = executorService.submit(() -> {
                    quickSorter.quicksort(half, 0, half.length-1);
                });
                futureExecs.add(future);
            }

            //Тут ждем пока все треды закончат выполнение, get() заставляет приложение ждать пока не выполнятся все future задачи
            for (Future<?> future : futureExecs) {
                future.get();
            }

        }catch (InterruptedException | ExecutionException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException("Sorting was interrupted", e);
        } finally {
            executorService.shutdown();
        }

        mergeSortedHalves(array,arrayOfHalves);

    }

    //splitArrayInHalf делит массив пополам, по половине на каждый поток
    public ArrayList<T[]> splitArrayInHalf(T[] array){
        ArrayList<T[]> arrayOfHalves = new ArrayList<>();
        int midIndex = array.length / 2;

        // Первая половина
        T[] firstHalf = Arrays.copyOfRange(array, 0, midIndex);
        arrayOfHalves.add(firstHalf);

        // Вторая половина
        T[] secondHalf = Arrays.copyOfRange(array, midIndex, array.length);
        arrayOfHalves.add(secondHalf);

        return arrayOfHalves;
    }

    public void mergeSortedHalves(T[] originalArray, ArrayList<T[]> sortedHalves){
        @SuppressWarnings("unchecked")

        T[] tempArray = (T[]) new Comparable[originalArray.length];
        int i = 0, j=0, k=0;

        int leftLength = sortedHalves.get(0).length;
        int rightLength = sortedHalves.get(1).length;
        System.out.println(Arrays.toString(sortedHalves.get(0)));
        System.out.println(Arrays.toString(sortedHalves.get(1)));
        while (i < leftLength && j < rightLength) {
            int comparison;

            if (comparator != null) {
                //если использовали конструктор с компаратором
                comparison = comparator.compare(sortedHalves.get(0)[i], sortedHalves.get(1)[j]);
            } else {
                //соответственно если использовали конструктор без компаратора
                comparison = ((Comparable<T>) sortedHalves.get(0)[i]).compareTo(sortedHalves.get(1)[j]);
            }

            if (comparison <= 0) {
                tempArray[k] = sortedHalves.get(0)[i];
                i++;
            } else {
                tempArray[k] = sortedHalves.get(1)[j];
                j++;
            }
            k++;
        }


        while (i < leftLength) {
            tempArray[k] = sortedHalves.get(0)[i];
            i++;
            k++;
        }


        while (j < rightLength) {
            tempArray[k] = sortedHalves.get(1)[j];
            j++;
            k++;
        }

        System.arraycopy(tempArray, 0, originalArray, 0, tempArray.length);
    }
}
