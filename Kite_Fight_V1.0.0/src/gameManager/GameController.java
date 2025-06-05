package gameManager;

import java.util.*;

import constants.GameConfig;
import model.BasicPlayer;
import model.Player;
import utils.ConsoleUtils;

public class GameController {
	private List<Player> players = new ArrayList<>();
	private Scanner scanner = new Scanner(System.in);
	
	public GameController() {
		// Default constructor
	}

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
			System.out.println("How many players will be joining the game?");
			System.out.print("Enter a number between " + GameConfig.MIN_PLAYERS + " and " + GameConfig.MAX_PLAYERS + ": ");
			try {
				count = scanner.nextInt();
				if (count < GameConfig.MIN_PLAYERS || count > GameConfig.MAX_PLAYERS) {
					System.out.println("Invalid number of players. Please try again.");
				}
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid number.");
				scanner.next(); // Clear invalid input
				count = 0;
			}
		} while (count < GameConfig.MIN_PLAYERS || count > GameConfig.MAX_PLAYERS);
		return count;
	}

	private void initializePlayers(int num) {
		scanner.nextLine(); // Consume newline
		
		System.out.println("\n--- Player Setup ---");
		System.out.println("Each player starts with " + GameConfig.INITIAL_KITES + " kites and " + 
				GameConfig.INITIAL_STRING + " string length.");
		
		for (int i = 1; i <= num; i++) {
			String name = ConsoleUtils.promptPlayerName(i);
			players.add(new BasicPlayer(name));
			System.out.println("Player " + i + " (" + name + ") has joined the game!");
		}
		
		System.out.println("\nAll players are ready! Let the Kite Fight begin!");
	}

	private void showFinalResults() {
		players.sort((p1, p2) -> Integer.compare(p2.getKiteCount(), p1.getKiteCount()));
		
		System.out.println("\n===== FINAL RESULTS =====");
		for (int i = 0; i < players.size(); i++) {
			Player p = players.get(i);
			System.out.println((i+1) + ". " + p.getName() + " - " + p.getKiteCount() + " kites");
		}
		
		Player winner = players.get(0);
		System.out.println("\n🏁 WINNER: " + winner.getName() + " with " + winner.getKiteCount() + " kites!");
	}
}
