package se.liu.joeri765youdr728.platformer.menu;

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
public class MenuPanel extends AbstractPanel
{


    private BufferedImage background = null;

    private MyButton buttonPlay = null, buttonHighscore = null, buttonQuit = null;

    private MainFrame mainFrame;

    private Sound sound = new Sound();

    private final static String SEPARATOR = File.separator;


    //------Buttons location and size
    private final static int PLAY_BUTTON_X = 240;
    private final static int PLAY_BUTTON_Y = 505;
    private final static int PLAY_BUTTON_WIDTH = 480;


    private final static int HIGHSCORE_BUTTON_X = 260;
    private final static int HIGHSCORE_BUTTON_Y = 600;
    private final static int HIGHSCORE_BUTTON_WIDTH = 440;


    private final static int QUIT_BUTTON_X = 360;
    private final static int QUIT_BUTTON_Y = 695;
    private final static int QUIT_BUTTON_WIDTH = 240;

    private final static int BUTTON_HEIGHT = 75;



    public MenuPanel(MainFrame mainFrame) {
	this.mainFrame = mainFrame;

	Logger logger = Logger.getLogger(MenuPanel.class.getName() );
	SimpleFormatter formatter = new SimpleFormatter();
	FileHandler fileHandler = null;
	try {
	    fileHandler = new FileHandler("LogFile.log", 0, 1, true);
	    logger.addHandler(fileHandler);
	    fileHandler.setFormatter(formatter);
	    background = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "menu_background.png"));
	} catch (IOException e) {
	    logger.info(e.getMessage());
	    e.printStackTrace();
	}
	logger.removeHandler(fileHandler);
	if(fileHandler != null){
	    fileHandler.close();
	}

	playMusic(MusicType.MENU_BACKGROUND);
	createButtons();
    }

    public void createButtons(){
	buttonPlay = new MyButton(Buttons.PLAY, PLAY_BUTTON_WIDTH, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, BUTTON_HEIGHT);
	buttonHighscore = new MyButton(Buttons.HIGHSCORE, HIGHSCORE_BUTTON_X, HIGHSCORE_BUTTON_Y, HIGHSCORE_BUTTON_WIDTH, BUTTON_HEIGHT);
	buttonQuit = new MyButton(Buttons.QUIT, QUIT_BUTTON_X, QUIT_BUTTON_Y, QUIT_BUTTON_WIDTH, BUTTON_HEIGHT);


    }

    public void drawButtons(Graphics g){
	buttonPlay.draw(g);
	buttonHighscore.draw(g);
	buttonQuit.draw(g);
    }

    public void playMusic(MusicType musicType){
	sound.setFileSound(musicType, "music");
	sound.loop();
    }
    public void stopMusic(){
	sound.stop();
    }


    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);

	g.drawImage(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this);

	drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y){
	if(buttonPlay.getBounds().contains(x, y)){
	    stopMusic();
	    mainFrame.startGame();
	}
	if(buttonHighscore.getBounds().contains(x, y)){
	    mainFrame.startHighscore();
	}
	if(buttonQuit.getBounds().contains(x, y)){
	    System.exit(0);
	}
    }

    @Override
    public void mouseMoved(int x, int y){
	buttonPlay.setMouseOver(false);
	buttonHighscore.setMouseOver(false);
	buttonQuit.setMouseOver(false);

	if(buttonPlay.getBounds().contains(x, y)){
	    buttonPlay.setMouseOver(true);
	}
	if(buttonHighscore.getBounds().contains(x, y)){
	    buttonHighscore.setMouseOver(true);
	}
	if(buttonQuit.getBounds().contains(x, y)){
	    buttonQuit.setMouseOver(true);
	}
	repaint();
    }
}
