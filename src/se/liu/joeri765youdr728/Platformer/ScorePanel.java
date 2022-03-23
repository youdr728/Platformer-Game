package se.liu.joeri765youdr728.Platformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ScorePanel extends AbstractPanel
{
    BufferedImage background;

    private MyButton button_back, sortScore_time, sortScore_coins, sortScore_deaths;

    private Frame frame;

    private HighScoreList highScoreList;

    private String text;

    private final int fontSize = 35, scoresWidth = 1, scoresHeight = 10, offset = 35, startX = 130, startY = 275;
    private String[][] scores;

    private Sound sound = new Sound();

    public ScorePanel(Frame frame, int typeNumber) {
        super.typeNumber = typeNumber;
        this.frame = frame;
        scores = new String[scoresHeight][scoresWidth];
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
        button_back = new MyButton(4, screenWidth - 210, screenHeight/2 + 130, 100, 50);
        sortScore_time = new MyButton(5, screenWidth/2 - 170, 135, 150, 65);
        sortScore_deaths = new MyButton(6, screenWidth/2 + 10, 135, 150, 65);
        sortScore_coins = new MyButton(7, screenWidth/2 + 180, 135, 150, 65);
    }

    public void drawButtons(Graphics g){
        button_back.draw(g);
        sortScore_time.draw(g);
        sortScore_deaths.draw(g);
        sortScore_coins.draw(g);
    }

    @Override
    public void mouseClicked(int x, int y){
        if(button_back.getBounds().contains(x, y)){
            frame.setCurrentFrame("highscoreFrame");
            frame.startMenu();

        }
        if(sortScore_time.getBounds().contains(x, y)){
            highScoreList = HighScoreList.loadHighscoreList();
            showHighScores(sortScore_time);
        }
        if(sortScore_deaths.getBounds().contains(x, y)){
            highScoreList = HighScoreList.loadHighscoreList();
            showHighScores(sortScore_deaths);
        }
        if(sortScore_coins.getBounds().contains(x, y)){
            highScoreList = HighScoreList.loadHighscoreList();
            showHighScores(sortScore_coins);
        }
    }

    @Override
    public void mouseMoved(int x, int y){
        button_back.setMouseOver(false);
        sortScore_time.setMouseOver(false);
        sortScore_deaths.setMouseOver(false);
        sortScore_coins.setMouseOver(false);

        if(button_back.getBounds().contains(x, y)){
            button_back.setMouseOver(true);
        }
        if(sortScore_time.getBounds().contains(x, y)){
            sortScore_time.setMouseOver(true);
        }
        if(sortScore_deaths.getBounds().contains(x, y)){
            sortScore_deaths.setMouseOver(true);
        }
        if(sortScore_coins.getBounds().contains(x, y)){
            sortScore_coins.setMouseOver(true);
        }

        repaint();
    }

    public void showHighScores(MyButton button) {
        highScoreList.getHighscoreList().sort(new ScoreComparator(button));


        for(int columns = 0; columns<scoresWidth; columns++) {
            for(int rows = 0; rows<scoresHeight; rows++) {
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

        g.setFont(new Font("Comic Sans MS", Font.PLAIN, fontSize));
        for(int columns = 0; columns<scoresWidth; columns++) {
            for(int rows = 0; rows<scoresHeight; rows++) {
                if (scores[rows][columns] != null){
                    g.drawString(scores[rows][columns], startX, startY + offset*rows);
            }
        }
       }

        drawButtons(g);
    }

}
