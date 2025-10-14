package readingStrategy;

import customClass.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDataStrategy implements ReadingStrategy{
    @Override
    public List<Car> getData() {
        int size = InputUtils.getNumberFromUser();

        List<Car> collection = new ArrayList<>(size);

        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            String model = CarModel.getRandomModel().toString();
            double maxSpeed = rand.nextDouble(150, 200);
            int weight = rand.nextInt(1000, 2000);
            Car car = new Car.Builder()
                    .setModel(model)
                    .setMaxSpeed(InputUtils.roundDoubleNumber(maxSpeed, 2))
                    .setWeight(weight)
                    .build();
            collection.add(car);
        }

        return collection;
    }
}
