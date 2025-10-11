package userInterface;

public class UserInterface {
    public void printCommands(){
        System.out.println("--------------------------------------");
        System.out.println("Меню команд:");
        System.out.println("1. Ввести данные,");
        System.out.println("2. Просмотреть отсортированные данные,");
        System.out.println("3. Найти элемент,");
        System.out.println("4. Завершить работу программы.");
        System.out.println("--------------------------------------");
        System.out.println("Введите номер команды из списка:");
    }

    public void chooseInputType(){
        System.out.println("------------------------------------------");
        System.out.println("Меню способов ввода данных:");
        System.out.println("1. Получить данные из файла,");
        System.out.println("2. Ввести данные через консоль,");
        System.out.println("3. Сгенерировать данные случайным образом,");
        System.out.println("4. Вернуться в главное меню.");
        System.out.println("------------------------------------------");
        System.out.println("Введите номер команды из списка:");
    }
}
