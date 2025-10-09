package sorting;

public class QuickSortGeneric <T extends Comparable<? super T>> {
    public void quicksort(T[] array, int startIndex, int endIndex)
    {
        // verify that the start and end index have not overlapped
        if (startIndex < endIndex)
        {
            int pivotIndex = partition(array, startIndex, endIndex);
            quicksort(array, startIndex, pivotIndex);
            quicksort(array, pivotIndex + 1, endIndex);
        }
    }

    private int partition(T[] array, int startIndex, int endIndex)
    {
        int pivotIndex = (startIndex + endIndex) / 2;
        T pivotValue = array[pivotIndex];
        startIndex--;
        endIndex++;

        while (true)
        {

            do startIndex++; while (array[startIndex].compareTo(pivotValue) < 0) ;


            do endIndex--; while (array[endIndex].compareTo(pivotValue) > 0) ;

            if (startIndex >= endIndex) return endIndex;

            T temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
        }
    }
}
