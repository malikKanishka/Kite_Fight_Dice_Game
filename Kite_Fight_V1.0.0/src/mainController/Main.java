package mainController;

import gameManager.GameController;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to Kite Fight!");
		System.out.println("A game of strategy, luck, and kite battles!");

		while (true) {
			try {
				System.out.println("\n--- MAIN MENU ---");
				System.out.println("1. Start a new game");
				System.out.println("0. Exit");
				System.out.print("Enter your choice: ");
				
				int choice = sc.nextInt();
				sc.nextLine(); // Consume newline

				switch (choice) {
				case 0 -> {
					System.out.println("Thank you for playing Kite Fight! Goodbye!");
					sc.close();
					System.exit(0);
				}
				case 1 -> {
					GameController controller = new GameController();
					controller.startGame();
				}
				default -> {
					System.out.println("Invalid choice. Please try again.");
				}
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number.");
				sc.nextLine(); // Clear invalid input
			} catch (Exception e) {
				System.out.println("An error occurred: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}
}
