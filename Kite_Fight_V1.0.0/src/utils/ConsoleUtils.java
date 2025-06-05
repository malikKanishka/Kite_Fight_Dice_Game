package utils;

import java.util.List;
import java.util.Scanner;

import model.Player;

public class ConsoleUtils {
	private static final Scanner scanner = new Scanner(System.in);

	public static String promptPlayerName(int playerIndex) {
		System.out.print("Enter name for Player " + playerIndex + ": ");
		return scanner.nextLine();
	}

	public static void printBanner() {
		System.out.println("======================\n   Kite Fight Game   \n======================");
	}

	public static void printRoundResults(Player attacker, Player victim, int cutAmount) {
		System.out.println(attacker.getName() + " gained a kite! Now has " + attacker.getKiteCount() + ".");
		System.out.println(victim.getName() + " lost a kite! Now has " + victim.getKiteCount() + ".");
	}

	public static void printPlayerStatus(List<Player> players) {
		System.out.println("\nPlayer Status:");
		for (Player p : players) {
			System.out.println(
					"- " + p.getName() + ": Kites = " + p.getKiteCount() + ", Total String = " + p.getTotalString());
		}
	}
}
