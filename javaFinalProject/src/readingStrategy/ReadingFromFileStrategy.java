package readingStrategy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import customClass.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReadingFromFileStrategy implements ReadingStrategy{
    @Override
    public List<Car> getData() {
        boolean isDataOk = false;
        List collection = null;

        while (!isDataOk) {
            List<String> contentFromFile = readFile();
            int size = contentFromFile.size();
            collection = new ArrayList<>(size);

            for (String s : contentFromFile) {
                Map<String, Object> fields;
                String model = null;
                double maxSpeed = 0;
                int weight = 0;
                try {
                    fields = parseString(s);
                    model = fields.get("model").toString();
                    maxSpeed = (double) fields.get("maxSpeed");
                    weight = (int) fields.get("weight");
                    isDataOk = true;
                } catch (Exception e) {
                    isDataOk = false;
                }

                try {
                    Car car = new Car.Builder()
                            .setModel(model)
                            .setMaxSpeed(maxSpeed)
                            .setWeight(weight)
                            .build();
                    collection.add(car);
                } catch (RuntimeException e) {
                    System.out.println("Ошибка при чтении файла");
                }
            }
        }

        return collection;
    }

    private static Path getAbsolutePath(String path) {
        return Paths.get(path).toAbsolutePath().normalize();
    }

    private List<String> readFile() {
        Scanner sc = new Scanner(System.in);
        boolean isFileOk = false;
        List<String> content = null;

        while (!isFileOk) {
            System.out.println("Введите имя файла:");
            String filePath = sc.next();
            Path path = getAbsolutePath(filePath);
            isFileOk = true;
            try {
                content = Files.readAllLines(path);
            } catch (IOException e) {
                System.out.println("Ошибка чтения файла");
                isFileOk = false;
            }

            if (isFileOk && content.isEmpty()) {
                System.out.println("Файл пустой");
                isFileOk = false;
            }
        }

        return content;
    }

    public static HashMap<String, Object> parseString(String content) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content, new TypeReference<>() { });
    }
}
