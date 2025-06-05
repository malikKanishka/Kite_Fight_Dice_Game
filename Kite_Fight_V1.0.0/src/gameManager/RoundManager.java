package gameManager;

import model.*;
import utils.ConsoleUtils;

import java.util.Comparator;
import java.util.List;

public class RoundManager {
	public static void playRound(List<Player> players, int roundNumber) {
		for (Player p : players) {
			int boost = Dice.roll() + Dice.roll() + Dice.roll();
			p.addBoost(boost);
			System.out.println(p.getName() + " rolled and gained a boost of " + boost);
		}

		Player attacker = players.stream().max(Comparator.comparingInt(Player::getTotalString)).orElse(null);
		Player victim = players.stream().min(Comparator.comparingInt(Player::getTotalString)).orElse(null);

		if (attacker != null && victim != null && attacker != victim) {
			int cut = 2;
			victim.reduceString(cut);
			System.out.println(attacker.getName() + " cuts " + cut + " from " + victim.getName());

			attacker.addKite();
			victim.removeKite();
			ConsoleUtils.printRoundResults(attacker, victim, cut);

			if (victim.getKiteCount() < 1) {
				victim.eliminate();
				System.out.println(victim.getName() + " has been eliminated!");
			}
		}

		players.forEach(Player::resetBoost);
		ConsoleUtils.printPlayerStatus(players);
	}
}
