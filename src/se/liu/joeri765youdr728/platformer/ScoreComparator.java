package se.liu.joeri765youdr728.platformer;

import java.util.Comparator;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class ScoreComparator implements Comparator<HighScore>
{
    private MyButton button;

    public ScoreComparator(final MyButton button) {
	this.button = button;
    }

    @Override public int compare(final HighScore highScore, final HighScore t1) {
	if (button.getImageNumber() == 5) {
	    return highScore.getTime() - t1.getTime();
	}
	else if (button.getImageNumber() ==6) {
	    return highScore.getDeaths() - t1.getDeaths();
	}
	else if (button.getImageNumber() == 7) {
	    return t1.getCoins() - highScore.getCoins();
	}
	return 0;
    }
}