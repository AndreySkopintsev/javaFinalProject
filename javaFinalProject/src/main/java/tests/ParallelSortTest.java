package tests;

import customClass.Car;
import sorting.ThreadPoolSort;

import java.util.Arrays;
import java.util.List;

//public class ParallelSortTest {
//    public static void run(){
//        //Кейс: все модели одинаковые, скорость одинаковая, разный вес
//        List<Car> cars = new List<Car>();
//                {
//                new Car.Builder().setModel("bmw").setMaxSpeed(100).setWeight(1500).build(),
//                new Car.Builder().setModel("bmw").setMaxSpeed(100).setWeight(1300).build(),
//                new Car.Builder().setModel("bmw").setMaxSpeed(100).setWeight(1800).build(),
//                new Car.Builder().setModel("bmw").setMaxSpeed(100).setWeight(500).build(),
//        };
//
//        ThreadPoolSort<Car> sortCars = new ThreadPoolSort<>();
//        sortCars.parallelSort(cars);
//        System.out.println("cars sorted by weight:" + Arrays.toString(cars));
//
//        //Кейс: все модели разные
//        Car[] cars2 = {
//                new Car.Builder().setModel("bmw").setMaxSpeed(100).setWeight(1500).build(),
//                new Car.Builder().setModel("bmz").setMaxSpeed(100).setWeight(1300).build(),
//                new Car.Builder().setModel("bmy").setMaxSpeed(100).setWeight(1800).build(),
//                new Car.Builder().setModel("bma").setMaxSpeed(100).setWeight(500).build(),
//        };
//
//        ThreadPoolSort<Car> sortCars2 = new ThreadPoolSort<>();
//        sortCars2.parallelSort(cars2);
//        System.out.println("cars sorted by name:" + Arrays.toString(cars2));
//
//        //Кейс: все модели одинаковые, скорость одинаковая, разный вес
//        Car[] cars3 = {
//                new Car.Builder().setModel("bmw").setMaxSpeed(90).setWeight(1500).build(),
//                new Car.Builder().setModel("bmw").setMaxSpeed(70).setWeight(1300).build(),
//                new Car.Builder().setModel("bmw").setMaxSpeed(340).setWeight(1800).build(),
//                new Car.Builder().setModel("bmw").setMaxSpeed(110).setWeight(500).build(),
//        };
//
//
//        ThreadPoolSort<Car> sortCars3 = new ThreadPoolSort<>();
//        sortCars3.parallelSort(cars3);
//        System.out.println("cars sorted by speed:" + Arrays.toString(cars3));
//
//        //Кейс: разные модели, есть две одинаковых с разной скоростью
//        Car[] cars4 = {
//                new Car.Builder().setModel("bmw").setMaxSpeed(100).setWeight(1500).build(),
//                new Car.Builder().setModel("bmz").setMaxSpeed(100).setWeight(1900).build(),
//                new Car.Builder().setModel("bmz").setMaxSpeed(100).setWeight(1800).build(),
//                new Car.Builder().setModel("bma").setMaxSpeed(100).setWeight(500).build(),
//        };
//
//        ThreadPoolSort<Car> sortCars4 = new ThreadPoolSort<>();
//        sortCars4.parallelSort(cars4);
//        System.out.println("cars sorted by name and weight:" + Arrays.toString(cars4));
//    }
//    public static void main(String[] args) {
//        run();
//    }
//}
