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
