package se.liu.joeri765youdr728.Platformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuPanel extends AbstractPanel
{
    BufferedImage background;

    private MyButton buttonPlay, buttonHighscore, buttonQuit;

    private Frame frame;


    public MenuPanel(Frame frame, int typeNumber) {
	super.typeNumber = typeNumber;
	this.frame = frame;

	createButtons();

    }

    public void createButtons(){
	buttonPlay = new MyButton(1, screenWidth/2-240, 505, 480, 75);
	buttonHighscore = new MyButton(2, screenWidth/2 - 220, 600, 440,75);
	buttonQuit = new MyButton(3, screenWidth/2 - 120, 695, 240, 75);


    }

    public void drawButtons(Graphics g){
	buttonPlay.draw(g);
	buttonHighscore.draw(g);
	buttonQuit.draw(g);
    }

    public void startGame(){
	//frame.getContentPane().removeAll();
	//GamePanel gamePanel = new GamePanel();
	//frame.getContentPane().add(gamePanel);
	//gamePanel.startGameThread();
    }



    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	try {
	    background = ImageIO.read(MenuPanel.class.getResourceAsStream("Tiles/menu_background.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	g.drawImage(background,0,0, screenWidth, screenHeight, this);

	drawButtons(g);
    }

    @Override
    public void mouseClicked(int x, int y){
	if(buttonPlay.getBounds().contains(x, y)){
	    frame.startGame();
	}
	if(buttonHighscore.getBounds().contains(x, y)){
	    frame.openHighScore();
	    frame.setOnMenu(true);
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
