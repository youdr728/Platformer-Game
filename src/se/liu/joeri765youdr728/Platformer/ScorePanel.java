package se.liu.joeri765youdr728.Platformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScorePanel extends AbstractPanel
{
    private BufferedImage background = null;

    private MyButton buttonBack = null, sortScoreTime = null, sortScoreCoins = null, sortScoreDeaths = null;

    private Frame frame;

    private HighScoreList highScoreList = null;

    private String text = null;

    private static final int FONT_SIZE = 35, SCORES_WIDTH = 1, SCORES_HEIGHT = 10, OFFSET = 35, START_X = 130, START_Y = 275;
    private String[][] scores;

    private Sound sound = new Sound();

    public ScorePanel(Frame frame) {
        this.frame = frame;
        scores = new String[SCORES_HEIGHT][SCORES_WIDTH];
        playMusic(3);
        createButtons();

    }

    public void playMusic(int i){
        sound.setFileMusic(i);
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSoundEffect(int i){
        sound.setFileSound(i);
        sound.playSound();

    }

    public void createButtons(){
        buttonBack = new MyButton(4, screenWidth - 210, screenHeight/2 + 130, 100, 50);
        sortScoreTime = new MyButton(5, screenWidth/2 - 170, 135, 150, 65);
        sortScoreDeaths = new MyButton(6, screenWidth/2 + 10, 135, 150, 65);
        sortScoreCoins = new MyButton(7, screenWidth/2 + 180, 135, 150, 65);
    }

    public void drawButtons(Graphics g){
        buttonBack.draw(g);
        sortScoreTime.draw(g);
        sortScoreDeaths.draw(g);
        sortScoreCoins.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y){
        if(buttonBack.getBounds().contains(x, y)){
            frame.setCurrentFrame("highscoreFrame");
            frame.startMenu();

        }
        if(sortScoreTime.getBounds().contains(x, y)){
            highScoreList = HighScoreList.loadHighscoreList();
            showHighScores(sortScoreTime);
        }
        if(sortScoreDeaths.getBounds().contains(x, y)){
            highScoreList = HighScoreList.loadHighscoreList();
            showHighScores(sortScoreDeaths);
        }
        if(sortScoreCoins.getBounds().contains(x, y)){
            highScoreList = HighScoreList.loadHighscoreList();
            showHighScores(sortScoreCoins);
        }
    }

    @Override
    public void mouseMoved(int x, int y){
        buttonBack.setMouseOver(false);
        sortScoreTime.setMouseOver(false);
        sortScoreDeaths.setMouseOver(false);
        sortScoreCoins.setMouseOver(false);

        if(buttonBack.getBounds().contains(x, y)){
            buttonBack.setMouseOver(true);
        }
        if(sortScoreTime.getBounds().contains(x, y)){
            sortScoreTime.setMouseOver(true);
        }
        if(sortScoreDeaths.getBounds().contains(x, y)){
            sortScoreDeaths.setMouseOver(true);
        }
        if(sortScoreCoins.getBounds().contains(x, y)){
            sortScoreCoins.setMouseOver(true);
        }

        repaint();
    }

    public void showHighScores(MyButton button) {
        highScoreList.getHighscoreList().sort(new ScoreComparator(button));


        for(int columns = 0; columns < SCORES_WIDTH; columns++) {
            for(int rows = 0; rows < SCORES_HEIGHT; rows++) {
                if(rows < highScoreList.getLength()){
                HighScore highscore = highScoreList.getHighscore(rows);
                scores[rows][columns] = "\n" + (rows + 1)  + ".  TIME: " + highscore.getTime() + "   DEATHS: " + highscore.getDeaths() +
                                        "   COINS: " + highscore.getCoins();
            }
        }
        }
    }


    @Override protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        try {
            background = ImageIO.read(MenuPanel.class.getResourceAsStream("Tiles/HighScore_list.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(background,0,0, screenWidth, screenHeight, this);

        g.setFont(new Font("Comic Sans MS", Font.PLAIN, FONT_SIZE));
        for(int columns = 0; columns < SCORES_WIDTH; columns++) {
            for(int rows = 0; rows < SCORES_HEIGHT; rows++) {
                if (scores[rows][columns] != null){
                    g.drawString(scores[rows][columns], START_X, START_Y + OFFSET * rows);
            }
        }
       }

        drawButtons(g);
    }

}