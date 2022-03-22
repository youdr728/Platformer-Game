package se.liu.joeri765youdr728.Platformer;

public class HighScore
{

    private int time, coins, deaths;

    public HighScore(final int Time, final int Deaths, int Coins) {
	this.time = Time;
	this.coins = Coins;
	this.deaths = Deaths;
    }

    public int getTime() {
	return time;
    }

    public int getCoins() {
	return coins;
    }

    public int getDeaths() {
	return deaths;
    }
}
