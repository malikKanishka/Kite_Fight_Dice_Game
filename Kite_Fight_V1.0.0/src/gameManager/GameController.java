package gameManager;

import java.util.*;

import constants.GameConfig;
import model.BasicPlayer;
import model.Player;
import utils.ConsoleUtils;

public class GameController {
	private List<Player> players = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);

	public void startGame() {
		ConsoleUtils.printBanner();
		int numPlayers = getValidPlayerCount();
		initializePlayers(numPlayers);

		for (int round = 1; round <= GameConfig.TOTAL_ROUNDS; round++) {
			System.out.println("\n===== ROUND " + round + " =====");
			RoundManager.playRound(players, round);
			players.removeIf(Player::isEliminated);

			if (players.size() == 1) {
				System.out.println("\nOnly one player remains!");
				break;
			}
		}

		showFinalResults();
	}

	private int getValidPlayerCount() {
		int count;
		do {
			System.out.print("Enter number of players (2-4): ");
			count = scanner.nextInt();
		} while (count < GameConfig.MIN_PLAYERS || count > GameConfig.MAX_PLAYERS);
		return count;
	}

	private void initializePlayers(int num) {
		scanner.nextLine(); 
		for (int i = 1; i <= num; i++) {
			String name = ConsoleUtils.promptPlayerName(i);
			players.add(new BasicPlayer(name));
		}
	}

	private void showFinalResults() {
		players.sort((p1, p2) -> Integer.compare(p2.getKiteCount(), p1.getKiteCount()));
		Player winner = players.get(0);
		System.out.println("\n🏁 Winner: " + winner.getName() + " with " + winner.getKiteCount() + " kites!");
	}
}
