import customClass.Car;
import readingStrategy.*;
import search.BinarySearch;
import sorting.ThreadPoolSort;
import userInterface.UserInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Main {
    private final Scanner inputScanner;
    private final UserInterface ui;
    private List<Car> cars;

    private ReadingStrategy inputStrategy;
    private Comparator<Car> naturalComparator;


    // Конструктор
    public Main() {
        this.inputScanner = new Scanner(System.in);
        this.ui = new UserInterface();
        this.cars = new ArrayList<>();
        this.naturalComparator = Car::compareTo;
    }

    public void run() {

        boolean runProgram = true;

        while (runProgram) {
            ui.printCommands();//вывод пользовательского меню
            String commandNumber = inputScanner.nextLine();//получаем номер команды от пользователя

            switch (commandNumber) {
                case "1"://Ввод данных
                    ui.chooseInputType();
                    String inputType = inputScanner.nextLine();

                    switch (inputType) {
                        case "1"://Данные из консоли
                            inputStrategy = new UserInputStrategy();
                            cars = inputStrategy.getData();
                            break;
                        case "2"://Данные из файла
                            inputStrategy = new ReadingFromFileStrategy();
                            cars = inputStrategy.getData();
                            break;
                        case "3"://Случайные данные
                            inputStrategy = new RandomDataStrategy();
                            cars = inputStrategy.getData();
                            break;
                        case "4"://Назад в меню
                            break;
                        default:
                            System.out.println("Введенная команда не поддерживается.");
                    }

                    if (cars != null && !cars.isEmpty()) {
                        ThreadPoolSort sort = new ThreadPoolSort<>();
                        sort.parallelSort(cars.toArray(new Car[0]));
                        System.out.println("Данные успешно отсортированы.");
                    } else {
                        System.out.println("Нет данных для сортировки.");
                    }

                    break;

                case "2"://Получить отсортированные данные
                    if (cars == null || cars.isEmpty()) {
                        System.out.println("Нет данных для отображения.");
                    } else {
                        System.out.println("Отсортированные данные:");
                        for (int i = 0; i < cars.size(); i++) {
                            System.out.println((i + 1) + ". " + cars.get(i));
                        }
                    }
                    break;

                case "3"://Поиск элемента
                    if (cars == null || cars.isEmpty()) {
                        System.out.println("Нет данных для поиска. Сначала введите данные.");
                        break;
                    }
                    //TODO пользователь вводит для поиска данные
                    Car key = new Car.Builder().setModel("BMW").setMaxSpeed(250.0).setWeight(1600).build();
                    //TODO если решим делать поиск по разным полям отдельно
                    //Comparator<Car> byModel = Comparator.comparing(Car::getModel);

                    int index = BinarySearch.search(cars, key, naturalComparator);
                    if(index != -1)
                        System.out.println("Номер элемента в массиве: " + (index+1));
                    else
                        System.out.println("Элемент не найден.");

                    break;

                case "4":
                    runProgram = false;
                    break;

                default:
                    System.out.println("Введенная команда не поддерживается.");
            }

        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

}

//TODO пока разбиение на задачи для группы видится как:
// София 1) main функция: тут реализовать интерфейс для пользователя, убрать все в методы, тестить все остальные ветки
// + комменты
// + валидация
// Алексей 2) CustomClass + builder (папка customClass)
// Павел 3) Методы чтения входных данных + strategy pattern (папка readingInfo)
// Виталий 4) Бинарный поиск (папка binarySearch)
// Андрей 5) Сортировка  (папка sorting)
// Так же каждый делает по файлу для теста своих методов в папке tests.

//Вопросы
//1. Просмотр отсортированных данных по отдельной команде
//2. При сортировке вывод на английском языке
//3. Поиск в каком виде должен быть? по модели и тд, пользователь вводит модель для поиска или как, сам ли он выбирает вообще
//4. В свиче я выбираю по цифрам, могу переделать по enum, если это имеет смысл


