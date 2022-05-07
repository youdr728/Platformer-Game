package se.liu.joeri765youdr728.platformer.highscore;

import se.liu.joeri765youdr728.platformer.AbstractPanel;
import se.liu.joeri765youdr728.platformer.MainFrame;
import se.liu.joeri765youdr728.platformer.MusicType;
import se.liu.joeri765youdr728.platformer.input.Buttons;
import se.liu.joeri765youdr728.platformer.input.MyButton;
import se.liu.joeri765youdr728.platformer.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class ScorePanel extends AbstractPanel
{


    private BufferedImage background = null;

    private MyButton buttonBack = null, sortScoreTime = null, sortScoreCoins = null, sortScoreDeaths = null;

    private MainFrame mainFrame;

    private HighScoreList highScoreList = null;

    private static final int FONT_SIZE = 35, SCORES_WIDTH = 1, SCORES_HEIGHT = 10, OFFSET = 35, START_X = 130, START_Y = 275;
    private String[][] scores;

    private Sound sound = new Sound();

    private final static String SEPARATOR = File.separator;


    //------- Button locations

    private final static int BACK_BUTTON_X = 750;
    private final static int BACK_BUTTON_Y = 610;
    private final static int BACK_BUTTON_WIDHT = 100;
    private final static int BACK_BUTTON_HEIGHT = 50;

    private final static int TIME_BUTTON_X = 310;
    private final static int DEATHS_BUTTON_X = 490;
    private final static int COINS_BUTTON_X = 660;

    private final static int BUTTON_Y = 135;
    private final static int BUTTON_WIDTH = 150;
    private final static int BUTTON_HEIGHT = 65;



    public ScorePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        scores = new String[SCORES_HEIGHT][SCORES_WIDTH];
        playMusic(MusicType.SCORE_BACKGROUND);
        createButtons();

        Logger logger = Logger.getLogger(ScorePanel.class.getName() );
        SimpleFormatter formatter = new SimpleFormatter();
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("LogFile.log", 0, 1, true);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(formatter);

            background = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "HighScore_list.png"));
        }
        catch (IOException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        logger.removeHandler(fileHandler);
        if(fileHandler != null){
            fileHandler.close();
        }

    }

    public void playMusic(MusicType musicType){
        sound.setFileSound(musicType, "music");
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }


    public void createButtons(){
        buttonBack = new MyButton(Buttons.BACK, BACK_BUTTON_X, BACK_BUTTON_Y , BACK_BUTTON_WIDHT, BACK_BUTTON_HEIGHT);
        sortScoreTime = new MyButton(Buttons.TIME, TIME_BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        sortScoreDeaths = new MyButton(Buttons.DEATHS, DEATHS_BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        sortScoreCoins = new MyButton(Buttons.COINS, COINS_BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
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
            mainFrame.setCurrentFrame("highscoreFrame");
            mainFrame.startMenu();

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


        g.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);

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
