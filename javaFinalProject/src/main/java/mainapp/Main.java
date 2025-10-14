package mainapp;
import customClass.Car;
import readingStrategy.*;
import search.BinarySearch;
import sorting.ThreadPoolSort;
import userInterface.UserInterface;

import java.util.*;

class Main {
    private final Scanner inputScanner;
    private final UserInterface ui;
    private List<Car> cars;

    private FileReader dataInput;
    private final ReadingStrategy userInput;
    private final ReadingStrategy fileInput;
    private final ReadingStrategy randomInput;
    private final ReadingStrategy oneInput;


    private Comparator<Car> naturalComparator;


    // Конструктор
    public Main() {
        this.inputScanner = new Scanner(System.in);
        this.ui = new UserInterface();
        this.cars = new ArrayList<>();
        this.naturalComparator = Car::compareTo;

        this.dataInput = new FileReader();
        userInput = new UserInputStrategy();
        fileInput = new ReadingFromFileStrategy();
        randomInput = new RandomDataStrategy();
        oneInput = new UserOneInputStrategy();
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
                            dataInput.setReadingStrategy(userInput);
                            break;
                        case "2"://Данные из файла
                            dataInput.setReadingStrategy(fileInput);
                            break;
                        case "3"://Случайные данные
                            dataInput.setReadingStrategy(randomInput);
                            break;
                        case "4"://Назад в меню
                            break;
                        default:
                            System.out.println("Введенная команда не поддерживается.");
                    }
                    cars = dataInput.getCollection();//ввод данных

                    if (cars != null && !cars.isEmpty()) {
                        ThreadPoolSort<Car> sort = new ThreadPoolSort<>();
                        sort.parallelSort(cars);
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

                    dataInput.setReadingStrategy(oneInput);
                    List<Car> findCar = dataInput.getCollection();
                    Car key = findCar.get(0);
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


