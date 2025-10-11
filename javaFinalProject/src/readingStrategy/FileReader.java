package readingStrategy;

import customClass.Car;

import java.util.List;

public class FileReader {

    private ReadingStrategy readingStrategy;
    private List<Car> collection;

    public FileReader() {}

    public FileReader setReadingStrategy(ReadingStrategy readingStrategy) {
        this.readingStrategy = readingStrategy;
        return this;
    }

    public List<Car> getCollection() {
        this.collection = this.readingStrategy.getData();
        return this.collection;
    }
}
