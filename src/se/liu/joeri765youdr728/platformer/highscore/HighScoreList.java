package se.liu.joeri765youdr728.platformer.highscore;

import com.google.gson.Gson;
import se.liu.joeri765youdr728.platformer.game.GameWorld;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class HighScoreList
{

    private List<HighScore> highscoreList = new ArrayList<>();

    public void addHighscore(GameWorld world) throws Exception {

	HighScore highscore = new HighScore(world.getScoreTime(), world.getDeathCounter(), world.getCoinCounter() );
	highscoreList.add(highscore);
	saveHighscoreList();
    }

    public void saveHighscoreList() throws Exception {

	Gson gson = new Gson();


	PrintWriter printWriter = new PrintWriter("Highscores.txt");

	    printWriter.println(gson.toJson(this));
	    printWriter.flush();

    }

    public static HighScoreList loadHighscoreList() throws Exception {
	Gson gson = new Gson();
	FileReader fileReader = new FileReader("Highscores.txt");

	    HighScoreList scores = gson.fromJson(fileReader, HighScoreList.class);

	    return scores;
    }

    public List<HighScore> getHighscoreList() {
	return highscoreList;
    }
    public int getLength(){
	return highscoreList.size();
    }
    public HighScore getHighscore(int n){
	return highscoreList.get(n);
    }
}
