package se.liu.joeri765youdr728.Platformer;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    private static BufferedImage playImage = null, highscoreImage = null, quitImage = null, backImage = null, timeImage = null, deathsImage =
	    null, coinsImage = null;

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

    public int getImageNumber() {
	return imageNumber;
    }

    public static Map<Integer, BufferedImage> createButtonImageMap(){

	try {
	    playImage = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/button_start.png"));
	    highscoreImage = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/button_highscore.png"));
	    quitImage = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/button_close.png"));
	    backImage = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/button_back.png"));
	    timeImage = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/button_time.png"));
	    deathsImage = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/button_deaths.png"));
	    coinsImage = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/button_coins.png"));

	} catch (IOException e) {
	    e.printStackTrace();
	}

	Map<Integer, BufferedImage> buttonImageMap = new HashMap<>();
	buttonImageMap.put(1, playImage);
	buttonImageMap.put(2, highscoreImage);
	buttonImageMap.put(3, quitImage);
	buttonImageMap.put(4, backImage);
	buttonImageMap.put(5,timeImage);
	buttonImageMap.put(6, deathsImage);
	buttonImageMap.put(7, coinsImage);


	return buttonImageMap;
    }

    public Rectangle getBounds() {
	return bounds;
    }
}
