package readingStrategy;

import customClass.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RandomDataStrategy implements ReadingStrategy{
    @Override
    public List<Car> getData() {
        Scanner sc = new Scanner(System.in);
        int size = 0;
        boolean isSizeCorrect = false;

        while (!isSizeCorrect) {
            System.out.println("Сколько объектов хотите добавить?");
            try {
                size = sc.nextInt();
                if (size > 0) {
                    isSizeCorrect = true;
                } else {
                    System.out.println("Значение должно быть больше нуля");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите число");
            }
        }
        List<Car> collection = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            String model = CarModel.getRandomModel().toString();
            double maxSpeed = new Random().nextDouble(150, 200);
            int weight = new Random().nextInt(1000, 2000);
            Car car = new Car.Builder()
                    .setModel(model)
                    .setMaxSpeed(maxSpeed)
                    .setWeight(weight)
                    .build();
            collection.add(car);
        }

        return collection;
    }
}
