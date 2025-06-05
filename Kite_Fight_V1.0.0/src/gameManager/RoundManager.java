package gameManager;

import model.*;
import utils.ConsoleUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class RoundManager {
	private static final Scanner scanner = new Scanner(System.in);
	
	public static void playRound(List<Player> players, int roundNumber) {
		System.out.println("\nRolling dice for each player to determine string boost...");
		System.out.println("(Press Enter to continue)");
		scanner.nextLine();
		
		for (Player p : players) {
			int roll1 = Dice.roll();
			int roll2 = Dice.roll();
			int roll3 = Dice.roll();
			int boost = roll1 + roll2 + roll3;
			p.addBoost(boost);
			System.out.println(p.getName() + " rolled " + roll1 + " + " + roll2 + " + " + roll3 + " = " + boost + " boost");
		}
		
		System.out.println("\nDetermining string lengths and finding matchups...");
		
		Player attacker = players.stream().max(Comparator.comparingInt(Player::getTotalString)).orElse(null);
		Player victim = players.stream().min(Comparator.comparingInt(Player::getTotalString)).orElse(null);

		if (attacker != null && victim != null && attacker != victim) {
			System.out.println("\n" + attacker.getName() + " has the longest string (" + attacker.getTotalString() + ")");
			System.out.println(victim.getName() + " has the shortest string (" + victim.getTotalString() + ")");
			
			int cut = 2;
			victim.reduceString(cut);
			
			System.out.println("\nPress Enter to see the results of the kite battle...");
			scanner.nextLine();

			attacker.addKite();
			victim.removeKite();
			ConsoleUtils.printRoundResults(attacker, victim, cut);

			if (victim.getKiteCount() < 1) {
				victim.eliminate();
				System.out.println("\n❌ " + victim.getName() + " has been eliminated from the game!");
			}
		} else {
			System.out.println("\nNo clear winner this round - all players have the same string length!");
		}

		players.forEach(Player::resetBoost);
		ConsoleUtils.printPlayerStatus(players);
		
		System.out.println("\nPress Enter to continue to the next phase...");
		scanner.nextLine();
	}
}
