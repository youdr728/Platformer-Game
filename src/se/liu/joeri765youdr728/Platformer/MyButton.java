package se.liu.joeri765youdr728.Platformer;

import org.w3c.dom.css.Rect;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class MyButton
{
    private int x;
    private int y;
    private int width;
    private int height;
    private int imageNumber;

    private Rectangle bounds;

    private boolean mouseOver;

    protected final Map<Integer, BufferedImage> buttonImageMap = createButtonImageMap();

    private static BufferedImage playImage, highscoreImage, quitImage;

    public MyButton(int imageNumber, int x,  int y, int width, int height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.imageNumber = imageNumber;

	initBounds();
    }
    private void initBounds(){
	this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g){

	g.drawImage(buttonImageMap.get(imageNumber), x, y, width, height, null);
	g.setColor(Color.WHITE);
	g.drawRect(x, y, width, height);
	if(mouseOver){
	    g.setColor(Color.CYAN);
	    g.drawRect(x,y,width,height);
	}




    }
    public void setMouseOver(boolean mouseOver){
	this.mouseOver = mouseOver;
    }

    public static Map<Integer, BufferedImage> createButtonImageMap(){

	try {
	    playImage = ImageIO.read(MenuPanel.class.getResourceAsStream("Tiles/button_start.png"));
	    highscoreImage = ImageIO.read(MenuPanel.class.getResourceAsStream("Tiles/button_highscore.png"));
	    quitImage = ImageIO.read(MenuPanel.class.getResourceAsStream("Tiles/button_close.png"));

	} catch (IOException e) {
	    e.printStackTrace();
	}

	Map<Integer, BufferedImage> buttonImageMap = new HashMap<>();
	buttonImageMap.put(1, playImage);
	buttonImageMap.put(2, highscoreImage);
	buttonImageMap.put(3,quitImage);


	return buttonImageMap;
    }

    public Rectangle getBounds() {
	return bounds;
    }
}
