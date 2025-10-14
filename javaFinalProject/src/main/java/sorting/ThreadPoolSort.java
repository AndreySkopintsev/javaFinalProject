package sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolSort <T extends Comparable<? super T>> {
    private final QuickSortGeneric<T> quickSorter   = new QuickSortGeneric<>();
    private Comparator<? super T> comparator;

    //дефолтный конструктор без компаратора
    public ThreadPoolSort(){
        this.comparator = null;
    };

    //конструктор с компаратором
    public ThreadPoolSort(Comparator<? super T> comparator) {
        this.comparator = comparator;
    }


    public void parallelSort(List<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("переданный список null");
        }

        if (list.size() <= 1) {
            return;
        }

        if (comparator != null) {
            quickSorter.setComparator(comparator);
        }

        //число тредов константой поставил 2
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        List<List<T>> listOfHalves = splitListInHalf(list);

        try {
            List<Future<?>> futureExecs = new ArrayList<>();
            for (List<T> half : listOfHalves) {
                Future<?> future = executorService.submit(() -> {
                    quickSorter.quicksort(half);
                });
                futureExecs.add(future);
            }

            for (Future<?> future : futureExecs) {
                future.get();
            }

        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("сортировка была прервана", e);
        } finally {
            executorService.shutdown();
        }

        mergeSortedHalves(list, listOfHalves);
    }

    private List<List<T>> splitListInHalf(List<T> list) {
        List<List<T>> listOfHalves = new ArrayList<>();
        int midIndex = list.size() / 2;

        List<T> firstHalf = new ArrayList<>(list.subList(0, midIndex));
        List<T> secondHalf = new ArrayList<>(list.subList(midIndex, list.size()));

        listOfHalves.add(firstHalf);
        listOfHalves.add(secondHalf);

        return listOfHalves;
    }

    private void mergeSortedHalves(List<T> originalList, List<List<T>> sortedHalves) {
        List<T> tempList = new ArrayList<>();
        List<T> leftHalf = sortedHalves.get(0);
        List<T> rightHalf = sortedHalves.get(1);

        int i = 0, j = 0;

        while (i < leftHalf.size() && j < rightHalf.size()) {
            int comparison;
            if (comparator != null) {
                comparison = comparator.compare(leftHalf.get(i), rightHalf.get(j));
            } else {
                comparison = leftHalf.get(i).compareTo(rightHalf.get(j));
            }

            if (comparison <= 0) {
                tempList.add(leftHalf.get(i));
                i++;
            } else {
                tempList.add(rightHalf.get(j));
                j++;
            }
        }

        while (i < leftHalf.size()) {
            tempList.add(leftHalf.get(i));
            i++;
        }

        while (j < rightHalf.size()) {
            tempList.add(rightHalf.get(j));
            j++;
        }

        //заменяем содержимое оригинального списка
        originalList.clear();
        originalList.addAll(tempList);
    }
}