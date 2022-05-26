package se.liu.joeri765youdr728.platformer.highscore;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * A class that describes what a highscore contains
 */
public class HighScore
{

    private int time, coins, deaths;

    public HighScore(final int time, final int deaths, int coins) {
	this.time = time;
	this.deaths = deaths;
	this.coins = coins;
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
