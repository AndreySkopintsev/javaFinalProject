package tests;

import sorting.ThreadPoolSort;

import java.util.Arrays;
import java.util.Comparator;

public class ParallelSortTest {
    void main(){
        //String[] arrayOfStrings = {"Andree", "Leana", "Faviola", "Loyce", "Quincy", "Milo", "Jamila", "Toccara"};
        String[] arrayOfStrings = {"a", "d", "g", "e", "z", "b", "c", "x"};
        ThreadPoolSort<String> stringQuickSorter = new ThreadPoolSort<>();
        stringQuickSorter.parallelSort(arrayOfStrings);

        for(String elem:arrayOfStrings){
            System.out.println("elements are as follows: " + elem);
        }

        Person[] people = {
                new Person("John", 25, 50000),
                new Person("Alice", 30, 60000),
                new Person("Bob", 22, 45000)
        };

        Comparator<Person> ageComparator = Comparator.comparingInt(p -> p.age);
        ThreadPoolSort<Person> sorterByAge = new ThreadPoolSort<>(ageComparator);
        sorterByAge.parallelSort(people);
        System.out.println("люди отсортированные по возрасту: " + Arrays.toString(people));
    }
}

class Person implements Comparable<Person> {
    String name;
    int age;
    double salary;

    public Person(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + "(" + age + ", $" + salary + ")";
    }

    @Override
    public int compareTo(Person o) {
        return 0;
    }
}