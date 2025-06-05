package model;

import constants.GameConfig;

public class BasicPlayer implements Player {
	private String name;
	private int baseString;
	private int kiteCount;
	private boolean eliminated;
	private int lastBoost;

	public BasicPlayer(String name) {
		this.name = name;
		this.baseString = GameConfig.INITIAL_STRING;
		this.kiteCount = GameConfig.INITIAL_KITES;
		this.lastBoost = 0;
		this.eliminated = false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getTotalString() {
		return baseString + lastBoost;
	}

	@Override
	public int getKiteCount() {
		return kiteCount;
	}

	@Override
	public boolean isEliminated() {
		return eliminated;
	}

	@Override
	public void addBoost(int boost) {
		lastBoost += boost;
	}

	@Override
	public void resetBoost() {
		lastBoost = 0;
	}

	@Override
	public void reduceString(int amount) {
		baseString = Math.max(0, baseString - amount);
	}

	@Override
	public void addKite() {
		kiteCount++;
	}

	@Override
	public void removeKite() {
		kiteCount--;
	}

	@Override
	public void eliminate() {
		eliminated = true;
	}
}
