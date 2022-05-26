package se.liu.joeri765youdr728.platformer.highscore;

import se.liu.joeri765youdr728.platformer.input.Buttons;
import se.liu.joeri765youdr728.platformer.input.MyButton;

import java.util.Comparator;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * A class for comparing the and listing them in the right order depending on if you want  time, deaths or coins to be first
 */
public class ScoreComparator implements Comparator<HighScore>
{
    private MyButton button;

    public ScoreComparator(final MyButton button) {
	this.button = button;
    }

    @Override public int compare(final HighScore highScore, final HighScore t1) {
	// Compares the different scores so we can list them in the right order
	if (button.getImageType() == Buttons.TIME) {
	    return Integer.compare(highScore.getTime(), t1.getTime());
	}
	else if (button.getImageType() == Buttons.DEATHS) {
	    return Integer.compare(highScore.getDeaths(), t1.getDeaths());
	}
	else if (button.getImageType() == Buttons.COINS) {
	    return Integer.compare(t1.getCoins(), highScore.getCoins());
	}
	return 0;
    }
}
