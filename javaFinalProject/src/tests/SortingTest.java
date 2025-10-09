package tests;

import sorting.BubbleSortGeneric;
import sorting.QuickSortGeneric;

public class SortingTest {
    void main() {
//Реализация quickSort'a через дженерик
        // пример сортировки с Strings
        String[] arrayOfStrings = {"Andree", "Leana", "Faviola", "Loyce", "Quincy", "Milo", "Jamila", "Toccara", "Nelda", "Blair", "Ernestine", "Chara", "Kareen", "Monty", "Rene", "Cami", "Winifred", "Tara", "Demetrice", "Azucena"};
        QuickSortGeneric<String> stringSorter   = new QuickSortGeneric<>();
        stringSorter.quicksort(arrayOfStrings, 0, arrayOfStrings.length - 1);
        System.out.println(java.util.Arrays.toString(arrayOfStrings));

        // пример сортировки с double
        Double[]                     arrayOfDoubles = {0.35, 0.02, 0.36, 0.82, 0.27, 0.49, 0.41, 0.17, 0.30, 0.89, 0.37, 0.66, 0.82, 0.17, 0.20, 0.96, 0.18, 0.25, 0.37, 0.52};
        QuickSortGeneric<Double> doubleSorter   = new QuickSortGeneric<>();
        doubleSorter.quicksort(arrayOfDoubles, 0, arrayOfDoubles.length - 1);
        System.out.println(java.util.Arrays.toString(arrayOfDoubles));

        //Реализация bubbleSort'a через дженерик
        // пример сортировки с Strings
        String[]   arrayOfStrings2 = {"Andree", "Leana", "Faviola", "Loyce", "Quincy", "Milo", "Jamila", "Toccara", "Nelda", "Blair", "Ernestine", "Chara", "Kareen", "Monty", "Rene", "Cami", "Winifred", "Tara", "Demetrice", "Azucena"};
        BubbleSortGeneric<String> stringSorter2   = new BubbleSortGeneric<>();
        stringSorter2.bubbleSort(arrayOfStrings);
        System.out.println(java.util.Arrays.toString(arrayOfStrings));

        // пример сортировки с double
        Double[]                  arrayOfDoubles2 = {0.35, 0.02, 0.36, 0.82, 0.27, 0.49, 0.41, 0.17, 0.30, 0.89, 0.37, 0.66, 0.82, 0.17, 0.20, 0.96, 0.18, 0.25, 0.37, 0.52};
        BubbleSortGeneric<Double> doubleSorter2   = new BubbleSortGeneric<>();
        doubleSorter2.bubbleSort(arrayOfDoubles);
        System.out.println(java.util.Arrays.toString(arrayOfDoubles));
    }
}
