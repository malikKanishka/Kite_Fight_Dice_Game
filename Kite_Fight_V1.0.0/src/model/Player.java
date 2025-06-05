package model;

public interface Player {
	String getName();

	int getTotalString();

	int getKiteCount();

	boolean isEliminated();

	void addBoost(int boost);

	void resetBoost();

	void reduceString(int amount);

	void addKite();

	void removeKite();

	void eliminate();
}
