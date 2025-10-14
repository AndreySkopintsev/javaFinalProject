package search;

import java.util.Comparator;
import java.util.List;

/**
 * Проект: javaFinalProject
 * Класс BinarySearch
 * Автор: Vitaliy Mikhajlov
 */

public class BinarySearch {
    // Приватный конструктор предотвращает создание экземпляров утилитарного класса
    private BinarySearch() {
    }

    public static <T> int search(List<T> list, T key, Comparator<T> comparator) {
        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // вычисляем средний индекс
            int cmp = comparator.compare(list.get(mid), key); // сравниваем среднйи элемент с ключом

            if (cmp == 0) {
                return mid; // ключ найден в mid
            }
            if (cmp < 0) {
                left = mid + 1; // ищем в правой половине
            } else {
                right = mid - 1; // ищем в левой половине
            }
        }
        return -1; // ключ не найден в списке
    }

    public static String searchCarFlexible(List<Car> list) {
        if (list == null || list.isEmpty()) {
            return "Список пустой, поиск невозможен.";
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Введите данные автомобиля для поиска (можно оставить пустым):");

        System.out.print("Модель: ");
        String model = sc.nextLine().trim();
        if (model.isEmpty()) model = null;

        Double maxSpeed = null;
        System.out.print("Максимальная скорость: ");
        String speedInput = sc.nextLine().trim();
        if (!speedInput.isEmpty()) {
            try {
                maxSpeed = Double.parseDouble(speedInput);
            } catch (NumberFormatException e) {
                System.out.println("Скорость введена неверно. Поле будет игнорироваться.");
            }
        }

        Integer weight = null;
        System.out.print("Вес: ");
        String weightInput = sc.nextLine().trim();
        if (!weightInput.isEmpty()) {
            try {
                weight = Integer.parseInt(weightInput);
            } catch (NumberFormatException e) {
                System.out.println("Вес введен неверно. Поле будет игнорироваться.");
            }
        }

        // Ищем первый элемент, который подходит под все указанные поля
        for (int i = 0; i < list.size(); i++) {
            Car car = list.get(i);
            boolean match = true;

            if (model != null && !car.getModel().equalsIgnoreCase(model)) match = false;
            if (maxSpeed != null && Double.compare(car.getMaxSpeed(), maxSpeed) != 0) match = false;
            if (weight != null && car.getWeight() != weight) match = false;

            if (match) {
                return "Элемент найден на позиции: " + (i + 1);
            }
        }

        return "Элемент не найден";
    }
}
