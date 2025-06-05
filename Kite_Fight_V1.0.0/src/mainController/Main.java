package mainController;

import gameManager.GameController;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\nPress any number to start a new game, or 0 to exit:");
			int choice = sc.nextInt();
			sc.nextLine(); 

			switch (choice) {
			case 0 -> {
				System.out.println("Exiting game. Goodbye!");
				sc.close();
				System.exit(0);
			}
			default -> {
				GameController controller = new GameController();
				controller.startGame();
			}
			}
		}
	}
}
