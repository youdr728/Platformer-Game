package se.liu.joeri765youdr728.Platformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class MenuPanel extends AbstractPanel
{
    private static final Logger LOGGER = Logger.getLogger(MenuPanel.class.getName() );

    private BufferedImage background = null;

    private MyButton buttonPlay = null, buttonHighscore = null, buttonQuit = null;

    private MainFrame mainFrame;

    private Sound sound = new Sound();

    private final static String SEPARATOR = File.separator;

    public MenuPanel(MainFrame mainFrame) {
	this.mainFrame = mainFrame;

	try {
	    background = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "menu_background.png"));
	} catch (IOException e) {
	    LOGGER.log(Level.FINE, e.getMessage());
	    e.printStackTrace();
	}

	playMusic(2);
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

    public void playMusic(int i){
	sound.setFileMusic(i);
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
