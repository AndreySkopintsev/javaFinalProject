package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolSort <T extends Comparable<? super T>> {
    private final QuickSortGeneric<T> quickSorter   = new QuickSortGeneric<>();

    public ThreadPoolSort(){};

    public void parallelSort(T[] array) {
        if (array == null) {
            throw new IllegalArgumentException("array sent is null");
        }

        if (array.length <= 1) {
            return;
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
        int idx = 0;
        for(T[] half:sortedHalves){
            for(T elem:half){
                tempArray[idx] = elem;
                idx+=1;
            }
        }
        System.arraycopy(tempArray, 0, originalArray, 0, tempArray.length);
    }
}
