package se.liu.joeri765youdr728.platformer.menu;

import se.liu.joeri765youdr728.platformer.AbstractPanel;
import se.liu.joeri765youdr728.platformer.MainFrame;
import se.liu.joeri765youdr728.platformer.MusicType;
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
	fileHandler.close();

	playMusic(MusicType.MENU_BACKGROUND);
	createButtons();
    }

    public void createButtons(){
	buttonPlay = new MyButton(1, SCREEN_WIDTH / 2 - 240, 505, 480, 75);
	buttonHighscore = new MyButton(2, SCREEN_WIDTH / 2 - 220, 600, 440, 75);
	buttonQuit = new MyButton(3, SCREEN_WIDTH / 2 - 120, 695, 240, 75);


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
