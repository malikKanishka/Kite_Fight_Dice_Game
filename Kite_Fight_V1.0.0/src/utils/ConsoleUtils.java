package utils;

import java.util.List;
import java.util.Scanner;

import model.Player;

public class ConsoleUtils {
	private static final Scanner scanner = new Scanner(System.in);

	public static String promptPlayerName(int playerIndex) {
		System.out.print("Enter name for Player " + playerIndex + ": ");
		String name = scanner.nextLine().trim();
		while (name.isEmpty()) {
			System.out.println("Name cannot be empty. Please try again.");
			System.out.print("Enter name for Player " + playerIndex + ": ");
			name = scanner.nextLine().trim();
		}
		return name;
	}

	public static void printBanner() {
		System.out.println("======================");
		System.out.println("   KITE FIGHT GAME    ");
		System.out.println("======================");
		System.out.println("Welcome to Kite Fight! Cut your opponents' strings and collect their kites!");
	}

	public static void printRoundResults(Player attacker, Player victim, int cutAmount) {
		System.out.println("\n--> " + attacker.getName() + " cuts " + victim.getName() + "'s string!");
		System.out.println("--> " + attacker.getName() + " gained a kite! Now has " + attacker.getKiteCount() + " kites.");
		System.out.println("--> " + victim.getName() + " lost a kite! Now has " + victim.getKiteCount() + " kites.");
	}

	public static void printPlayerStatus(List<Player> players) {
		System.out.println("\n--- CURRENT PLAYER STATUS ---");
		for (Player p : players) {
			System.out.println(p.getName() + ": " + p.getKiteCount() + " kites, " + 
					p.getTotalString() + " string length" + 
					(p.isEliminated() ? " (ELIMINATED)" : ""));
		}
		System.out.println("----------------------------");
	}
}
