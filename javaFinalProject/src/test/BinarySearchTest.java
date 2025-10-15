import customClass.Car;
import org.junit.jupiter.api.Test;
import search.BinarySearch;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTest {

    @Test
    void testSearchFound() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car.Builder().setModel("BMW").setMaxSpeed(200).setWeight(1500).build());
        cars.add(new Car.Builder().setModel("Audi").setMaxSpeed(220).setWeight(1600).build());
        cars.add(new Car.Builder().setModel("Tesla").setMaxSpeed(250).setWeight(1800).build());

        // Сортируем список для бинарного поиска
        cars.sort(Car::compareTo);

        Car key = new Car.Builder().setModel("Audi").setMaxSpeed(220).setWeight(1600).build();
        int index = BinarySearch.search(cars, key, Car::compareTo);

        assertTrue(index != -1, "Элемент должен быть найден");
        assertEquals(key.getModel(), cars.get(index).getModel());
    }

    @Test
    void testSearchNotFound() {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car.Builder().setModel("BMW").setMaxSpeed(200).setWeight(1500).build());
        cars.add(new Car.Builder().setModel("Audi").setMaxSpeed(220).setWeight(1600).build());
        cars.add(new Car.Builder().setModel("Tesla").setMaxSpeed(250).setWeight(1800).build());

        // Сортируем список для бинарного поиска
        cars.sort(Car::compareTo);

        Car key = new Car.Builder().setModel("Mercedes").setMaxSpeed(210).setWeight(1700).build();
        int index = BinarySearch.search(cars, key, Car::compareTo);

        assertEquals(-1, index, "Элемент не должен быть найден");
    }
}