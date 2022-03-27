package se.liu.joeri765youdr728.platformer.highscore;

import com.google.gson.Gson;
import se.liu.joeri765youdr728.platformer.game.World;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
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


    public void addHighscore(World world){

	HighScore highscore = new HighScore(world.getScoreTime(), world.getDeathCounter(), world.getCoinCounter() );
	highscoreList.add(highscore);
	saveHighscoreList();
    }

    public void saveHighscoreList() {

	Gson gson = new Gson();
	Logger logger = Logger.getLogger(HighScoreList.class.getName() );
	SimpleFormatter formatter = new SimpleFormatter();
	FileHandler fileHandler = null;
	try  (PrintWriter pw = new PrintWriter("Highscores.txt")){
	    fileHandler = new FileHandler("LogFile.log", 0, 1, true);
	    logger.addHandler(fileHandler);


	    pw.println(gson.toJson(this));
	    pw.flush();
	}
	catch (IOException e) {
	    logger.info(e.getMessage());
	    e.printStackTrace();
	}
	logger.removeHandler(fileHandler);
	fileHandler.close();

    }

    public static HighScoreList loadHighscoreList()  {

	Gson gson = new Gson();
	Logger logger = Logger.getLogger(HighScoreList.class.getName() );
	SimpleFormatter formatter = new SimpleFormatter();
	FileHandler fileHandler = null;
	try (FileReader fr = new FileReader("Highscores.txt")){
	    fileHandler = new FileHandler("LogFile.log", 0, 1, true);
	    logger.addHandler(fileHandler);
	    fileHandler.setFormatter(formatter);

	    HighScoreList scores = gson.fromJson(fr, HighScoreList.class);

	    return scores;

	} catch (IOException e){
	    logger.info(e.getMessage());
	    e.printStackTrace();
	}
	logger.removeHandler(fileHandler);
	fileHandler.close();
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
