package se.liu.joeri765youdr728.Platformer;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class HighScoreList
{

    private List<HighScore> highscoreList = new ArrayList<>();

    private static final Logger LOGGER = Logger.getLogger(HighScoreList.class.getName() );
    private static SimpleFormatter formatter = new SimpleFormatter();
    private static FileHandler fh;

    static {
	try {
	    fh = new FileHandler("LogFile.log", 0, 1, true);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void addHighscore(GameWorld world){

	HighScore highscore = new HighScore(world.getScoreTime(), world.getDeathCounter(), world.getCoinCounter() );
	highscoreList.add(highscore);
	saveHighscoreList();
    }

    public void saveHighscoreList() {

	Gson gson = new Gson();


	try  {
	    LOGGER.addHandler(fh);
	    fh.setFormatter(formatter);

	    PrintWriter pw = new PrintWriter("Highscores.txt");
	    pw.println(gson.toJson(this));
	    pw.flush();
	}
	catch (IOException e) {
	    LOGGER.log(Level.FINE,e.getMessage());
	    e.printStackTrace();
	}

    }

    public static HighScoreList loadHighscoreList()  {
	try{
	    LOGGER.addHandler(fh);
	    fh.setFormatter(formatter);

	    Gson gson = new Gson();
	    FileReader fr = new FileReader("Highscores.txt");
	    HighScoreList scores = gson.fromJson(fr, HighScoreList.class);

	    return scores;

	} catch (IOException e){
	    LOGGER.log(Level.FINE,e.getMessage());
	}
	return new HighScoreList();
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
