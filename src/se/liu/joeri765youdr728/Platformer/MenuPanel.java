package se.liu.joeri765youdr728.Platformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class MenuPanel extends JPanel
{
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int columns = 20;
    final int rows = 20;
    final int screenHeight = rows * tileSize;
    final int screenWidth = columns * tileSize;

    BufferedImage background;

    private MyButton buttonPlay, buttonHighscore, buttonQuit;

    MouseHandler mouseHandler = new MouseHandler(this);

    public MenuPanel() {
	this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	this.setDoubleBuffered(true);
	this.repaint();
	this.setFocusable(true);
	this.addMouseListener(mouseHandler);
	this.addMouseMotionListener(mouseHandler);

	createButtons();

    }

    public void createButtons(){
	buttonPlay = new MyButton(1, screenWidth/2, 400, 100, 50);


    }

    public void drawButtons(Graphics g){
	buttonPlay.draw(g);
    }



    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	try {
	    background = ImageIO.read(MenuPanel.class.getResourceAsStream("Tiles/GameName.png"));
	} catch (IOException e) {
	    e.printStackTrace();
	}
	g.drawImage(background,0,0, screenWidth, screenHeight, this);

	drawButtons(g);
    }

    public void mouseClicked(int x, int y){
	if(buttonPlay.getBounds().contains(x, y)){
	    System.out.println("click");
	}
    }
    public void mouseMoved(int x, int y){
	buttonPlay.setMouseOver(false);

	if(buttonPlay.getBounds().contains(x, y)){
	    buttonPlay.setMouseOver(true);
	}
	repaint();
    }
}
