package numgen;

import java.util.Random;
import java.util.Scanner;

public class Numgen {
    public static void main(String[] args) throws InterruptedException {
        // Создаем объект для генерации случайных чисел
        Random rand = new Random();
        // Создаем объект для считывания данных с клавиатуры
        Scanner scanner = new Scanner(System.in);
        
        int userGuess = 0;
        
        while(userGuess == 0){
            System.out.println("Загадай номер от 1 до 10:");
            // Проверяем, что введено число
            if (scanner.hasNextInt()) {
                userGuess = scanner.nextInt();
                // Проверяем, что число находится в диапазоне от 1 до 10
                if (userGuess < 1 || userGuess > 10) {
                    System.out.println("Некорректное число. Пожалуйста, введите число от 1 до 10.");
                    userGuess = 0;
                }
            } else {
                System.out.println("Некорректный ввод. Пожалуйста, введите число.");
                scanner.next();
            }
        }
        
        System.out.println("Генерируем результат...");
        // Выводим на экран номера от 1 до 10 с задержкой 250 мс
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
            Thread.sleep(250);
        }
        // Генерируем случайное число от 1 до 10
        int randomNumber = rand.nextInt(10) + 1;
        System.out.println("\nВыпало число: " + randomNumber);
        // Проверяем, совпадает ли загаданное пользователем число со сгенерированным
        if (userGuess == randomNumber) {
        	System.out.println("Вы угадали номер! Вы победили!");
        	} else {
        	System.out.println("К сожалению выпало другое число. Возможно повезет в следующий раз!");
        	}
        	}
        	}

//just test string like this file edited
