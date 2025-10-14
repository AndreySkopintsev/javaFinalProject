package readingStrategy;

import customClass.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserOneInputStrategy implements ReadingStrategy {

    @Override
    public List<Car> getData() {
        Scanner sc = new Scanner(System.in);

        final int size = 1;

        List<Car> collection = new ArrayList<>(size);

        String model = null;
        double maxSpeed = 0;
        int weight = 0;
        boolean isModelCorrect = false;
        boolean isMaxSpeedCorrect = false;
        boolean isWeightCorrect = false;

        while (!isModelCorrect) {
            System.out.println("Введите модель ");
            if (sc.hasNextLine()) {
                model = sc.nextLine();
                if (!model.isBlank()) {
                    isModelCorrect = true;
                } else {
                    System.out.println("Модель не должна быть пустой");
                }
            } else {
                System.out.println("Ошибка ввода");
                sc.nextLine();
            }
        }

        while (!isMaxSpeedCorrect) {
            System.out.println("Введите максимальную скорость");
            if (sc.hasNextDouble()) {
                maxSpeed = sc.nextDouble();
                if (maxSpeed > 0) {
                    isMaxSpeedCorrect = true;
                } else {
                    System.out.println("Значение должно быть больше нуля");
                }
            } else {
                System.out.println("Значение должно быть числом");
                sc.nextLine();
            }
        }

        while (!isWeightCorrect) {
            System.out.println("Введите вес");
            if (sc.hasNextInt()) {
                weight = sc.nextInt();
                if (weight > 0) {
                    isWeightCorrect = true;
                } else {
                    System.out.println("Значение должно быть больше нуля");
                }
            } else {
                System.out.println("Значение должно быть числом");
                sc.nextLine();
            }
        }
        sc.nextLine();

        Car car = new Car.Builder()
                .setModel(model)
                .setMaxSpeed(maxSpeed)
                .setWeight(weight)
                .build();
        collection.add(car);

        return collection;
    }
}
