package readingStrategy;

import java.util.Random;

public enum CarModel {
    TESLA_MODEL_Y ("Tesla Model Y"),
    TOYOTA_COROLLA("Toyota Corolla"),
    TOYOTARAV4("Toyota RAV4"),
    FORDFSERIES("Ford F-Series"),
    HONDACRV("Honda CR-V"),
    CHEVROLETSILVERADO("Chevrolet Silverado"),
    HYUNDAITUCSON("Hyundai Tucson"),
    TOYOTACAMRY("Toyota Camry"),
    BYDSONG("BYD Song"),
    VOLKSWAGENTIGUAN("Volkswagen Tiguan");

    private final String name;

    CarModel(String s) {
        name = s;
    }

    private static final Random RANDOM = new Random();
    private static final CarModel[] models = values();

    public static CarModel getRandomModel() {
        return models[RANDOM.nextInt(models.length)];
    }

    @Override
    public String toString() {
        return this.name;
    }
}
