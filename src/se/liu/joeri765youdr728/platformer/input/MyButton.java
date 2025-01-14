package se.liu.joeri765youdr728.platformer.input;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * Class for a cutsom button that we made that contains its position, dimensions and picture
 */
public class MyButton
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Buttons imageType;

    private Rectangle bounds;

    private boolean mouseOver;

    protected final EnumMap<Buttons, BufferedImage> buttonImageMap = createButtonImageMap();

    private final static String SEPARATOR = File.separator;

    public MyButton(Buttons imageType, int x,  int y, int width, int height) {
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.imageType = imageType;

	initBounds();
    }
    private void initBounds(){
	this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g){

	g.drawImage(buttonImageMap.get(imageType), x, y, width, height, null);
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


    public static EnumMap<Buttons, BufferedImage> createButtonImageMap(){
	//kod analys varning: Från vad jag kan se av att läsa på felmedelandet så gör vi inte det här på fel sätt
	BufferedImage playImage = null, highscoreImage = null, quitImage = null, backImage = null, timeImage = null, deathsImage =
		null, coinsImage = null;

	Logger logger = Logger.getLogger(MyButton.class.getName() );
	SimpleFormatter formatter = new SimpleFormatter();
	FileHandler fileHandler = null;
	try {
	    fileHandler = new FileHandler("LogFile.log", 0, 1, true);
	    logger.addHandler(fileHandler);
	    fileHandler.setFormatter(formatter);

	    String urlString = "images" + SEPARATOR;
	    playImage = ImageIO.read(ClassLoader.getSystemResource(urlString + "button_start.png"));
	    highscoreImage = ImageIO.read(ClassLoader.getSystemResource(urlString + "button_highscore.png"));
	    quitImage = ImageIO.read(ClassLoader.getSystemResource(urlString + "button_close.png"));
	    backImage = ImageIO.read(ClassLoader.getSystemResource(urlString + "button_back.png"));
	    timeImage = ImageIO.read(ClassLoader.getSystemResource(urlString + "button_time.png"));
	    deathsImage = ImageIO.read(ClassLoader.getSystemResource(urlString + "button_deaths.png"));
	    coinsImage = ImageIO.read(ClassLoader.getSystemResource(urlString + "button_coins.png"));

	} catch (IOException e){
	    //kod analys varning: Du skrev i ett mail att om vi inte lyckades
	    //komma på en bra lösning till dom här problem att vi skulle lämmna en komentar då

	    logger.info(e.getMessage());
	    e.printStackTrace();
	}
	logger.removeHandler(fileHandler);
	if(fileHandler != null){
	    fileHandler.close();
	}

	EnumMap<Buttons, BufferedImage> buttonImageMap = new EnumMap<>(Buttons.class);
	buttonImageMap.put(Buttons.PLAY, playImage);
	buttonImageMap.put(Buttons.HIGHSCORE, highscoreImage);
	buttonImageMap.put(Buttons.QUIT, quitImage);
	buttonImageMap.put(Buttons.BACK, backImage);
	buttonImageMap.put(Buttons.TIME,timeImage);
	buttonImageMap.put(Buttons.DEATHS, deathsImage);
	buttonImageMap.put(Buttons.COINS, coinsImage);


	return buttonImageMap;
    }

    public Rectangle getBounds() {
	return bounds;
    }
    public Buttons getImageType() {
	return imageType;
    }
}
