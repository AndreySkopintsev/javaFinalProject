package CustomClass;

//В этом классе должно быть минимум три поля и по каждому должна выполняться сортировка.
//Так как четких требований нет, предлагаю сделать поля типов String, double и int
//Так же для CustomClass в папке test создать "тестовый" класс в котором проверять сортировку полей + валидацию
public class Car implements Comparable<Car> {
    private String model;
    private double maxSpeed;
    private int weight;

    private Car(Builder builder) {
        this.model = builder.model;
        this.maxSpeed = builder.maxSpeed;
        this.weight = builder.weight;
    }

    public String getModel() { return model; }
    public double getMaxSpeed() { return maxSpeed; }
    public int getWeight() { return weight; }

    public static class Builder {
        private String model;
        private double maxSpeed;
        private int weight;

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setMaxSpeed(double maxSpeed) {
            this.maxSpeed = maxSpeed;
            return this;
        }

        public Builder setWeight(int weight) {
            this.weight = weight;
            return this;
        }

        public Car build() {
            if(model.isBlank()) {
                throw new IllegalArgumentException("Введено неверное значение модели.");
            }

            if(maxSpeed <= 0) {
                throw new IllegalArgumentException("Скорость не может быть меньше или равна 0.");
            }

            if(weight <= 0) {
                throw new IllegalArgumentException("Вес не может быть меньше или равен 0");
            }
            return new Car(this);

        }
    }

    @Override
    public int compareTo(Car other) {
        // Сначала сравниваем по имени
        int result = this.model.compareTo(other.model);
        if (result != 0) return result;

        // Если имена одинаковые — по цене
        result = Double.compare(this.maxSpeed, other.maxSpeed);
        if (result != 0) return result;

        // Если и цена одинакова — по количеству
        return Integer.compare(this.weight, other.weight);
    }


    @Override
    public String toString() {
        return String.format("%s: maxSpeed=%.2f, weight=%d", model, maxSpeed, weight);
    }



    //TODO реализовать паттерн Builder + добавить валидацию данных
    //    //TODO валидация данных, как я понимаю, это как минимум проверка на то что переданные данные сортируемы (не boolean, Strings не содержат символов)
}
