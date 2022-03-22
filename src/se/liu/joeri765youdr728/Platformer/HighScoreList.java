package se.liu.joeri765youdr728.Platformer;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class HighScoreList
{

    private List<HighScore> highscoreList = new ArrayList<>();

    public void addHighscore(GameWorld world){

	HighScore highscore = new HighScore(world.getScoreTime(), world.getDeathCounter(), world.getCoinCounter() );
	highscoreList.add(highscore);
	saveHighscoreList();
    }

    public void saveHighscoreList() {

	Gson gson = new Gson();

	try  {
	    PrintWriter pw = new PrintWriter("Highscores.txt");
	    pw.println(gson.toJson(this));
	    pw.flush();
	}
	catch (IOException e) {
	    e.printStackTrace();
	}

    }

    public static HighScoreList loadHighscoreList()  {
	try{
	    Gson gson = new Gson();
	    FileReader fr = new FileReader("Highscores.txt");
	    HighScoreList list = gson.fromJson(fr, HighScoreList.class);

	    return list;

	} catch (IOException ignored){

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
