package numbers;

import java.util.Random;
import java.util.Scanner;

public class Numbers {
	public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Guess a number between 1 and 10:");
        int userGuess = scanner.nextInt();
        System.out.println("Generating random number...");
        for (int i = 1; i <= 10; i++) {
            System.out.print(i + " ");
            Thread.sleep(200);
        }
        int randomNumber = rand.nextInt(10) + 1;
        System.out.println("\nThe number is: " + randomNumber);
        if (userGuess == randomNumber) {
            System.out.println("You guessed the number! You win!");
        } else {
            System.out.println("Sorry, the number was not guessed. Better luck next time!");
        }
    }

}
