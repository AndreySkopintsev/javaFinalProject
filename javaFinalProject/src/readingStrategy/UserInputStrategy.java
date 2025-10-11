package readingStrategy;

import customClass.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputStrategy implements ReadingStrategy {

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
            System.out.println("Объект № " + (i + 1));
            System.out.println("Введите модель ");
            String model = sc.next();
            System.out.println("Введите максимальную скорость");
            double maxSpeed = sc.nextDouble();
            System.out.println("Введите вес");
            int weight = sc.nextInt();
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
