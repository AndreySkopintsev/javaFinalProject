package readingStrategy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import customClass.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ReadingFromFileStrategy implements ReadingStrategy{
    @Override
    public List<Car> getData() {
        List<String> contentFromFile = readFile();
        int size = contentFromFile.size();

        List<Car> collection = new ArrayList<>(size);
        for (String s : contentFromFile) {
            Map<String, Object> fields;
            try {
                fields = parseString(s);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String model = fields.get("model").toString();
            double maxSpeed = (double) fields.get("maxSpeed");
            int weight = (int) fields.get("weight");
            Car car = new Car.Builder()
                    .setModel(model)
                    .setMaxSpeed(maxSpeed)
                    .setWeight(weight)
                    .build();
            collection.add(car);
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
