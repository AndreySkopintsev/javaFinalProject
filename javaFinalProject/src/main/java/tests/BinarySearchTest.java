package tests;

import customClass.Car;
import search.BinarySearch;

import java.util.ArrayList;
import java.util.List;


public class BinarySearchTest {

    public static void run(){
        List<Car> cars = new ArrayList<>();
        cars.add(new Car.Builder().setModel("BMW").setMaxSpeed(200).setWeight(1500).build());
        cars.add(new Car.Builder().setModel("Audi").setMaxSpeed(220).setWeight(1600).build());
        cars.add(new Car.Builder().setModel("Tesla").setMaxSpeed(250).setWeight(1800).build());

        // Сортируем список для бинарного поиска
        cars.sort(Car::compareTo);

        Car key = new Car.Builder().setModel("Audi").setMaxSpeed(220).setWeight(1600).build();
        int index = BinarySearch.search(cars, key, Car::compareTo);

        if(cars.get(index).getModel().equals(key.getModel())){
            System.out.println("found the right one, you did it");
        }else{
            System.out.println("test failed");
        }

        List<Car> cars2 = new ArrayList<>();
        cars2.add(new Car.Builder().setModel("BMW").setMaxSpeed(200).setWeight(1500).build());
        cars2.add(new Car.Builder().setModel("Audi").setMaxSpeed(220).setWeight(1600).build());
        cars2.add(new Car.Builder().setModel("Tesla").setMaxSpeed(250).setWeight(1800).build());

        // Сортируем список для бинарного поиска
        cars2.sort(Car::compareTo);

        Car key2 = new Car.Builder().setModel("Mercedes").setMaxSpeed(210).setWeight(1700).build();
        int index2 = BinarySearch.search(cars2, key2, Car::compareTo);

        if(index2==-1){
            System.out.println("found the right one, you did it");
        }else{
            System.out.println("test failed");
        }
    }

    public static void main(String[] args) {
        run();
    }


}