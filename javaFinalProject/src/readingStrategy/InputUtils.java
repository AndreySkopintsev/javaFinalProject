package readingStrategy;

import java.util.Scanner;

public class InputUtils {

    public static int getNumberFromUser() {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        boolean isNumberCorrect = false;

        while (!isNumberCorrect) {
            System.out.println("Сколько объектов хотите добавить?");
            if (sc.hasNextInt()) {
                number = sc.nextInt();
                if (number > 0) {
                    isNumberCorrect = true;
                } else {
                    System.out.println("Значение должно быть больше нуля");
                }
            } else {
                System.out.println("Значение должно быть числом");
                sc.next();
            }
        }
        sc.nextLine();
        return number;
    }
}
