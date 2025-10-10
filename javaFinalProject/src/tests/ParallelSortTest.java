package tests;

import sorting.ThreadPoolSort;

public class ParallelSortTest {
    void main(){
        String[] arrayOfStrings = {"Andree", "Leana", "Faviola", "Loyce", "Quincy", "Milo", "Jamila", "Toccara"};
        ThreadPoolSort<String> stringQuickSorter = new ThreadPoolSort<>();
        stringQuickSorter.parallelSort(arrayOfStrings);

        for(String elem:arrayOfStrings){
            System.out.println("elements are as follows: " + elem);
        }
    }
}
