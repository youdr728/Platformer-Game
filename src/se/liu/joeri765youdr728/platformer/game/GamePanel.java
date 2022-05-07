package se.liu.joeri765youdr728.platformer.game;

import se.liu.joeri765youdr728.platformer.input.Direction;
import se.liu.joeri765youdr728.platformer.MainFrame;
import se.liu.joeri765youdr728.platformer.input.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
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
 */
public class GamePanel extends JComponent implements  Runnable
{

    //------Classes
    private MainFrame mainFrame;
    private World world;
    private KeyHandler keyH = new KeyHandler();
    private Thread gameThread = null;

    //------Images and fileReading
    protected final EnumMap<EntityType, BufferedImage> tileMap = createTileMap();
    private BufferedImage loseImage = null, winImage = null;
    private final static String SEPARATOR = File.separator;
    private final static String URL_STRING = "images" + SEPARATOR;

    //------Screen settings
    private static final int ORIGINAL_TILE_SIZE = 16;
    private static final int SCALE = 3;

    private static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    private static final int COLUMNS = 20;
    private static final int ROWS = 20;
    private static final int SCREEN_HEIGHT = ROWS * TILE_SIZE;
    private static final int SCREEN_WIDTH = COLUMNS * TILE_SIZE;

    private static final int FONT_SIZE = 50;

    //------Booleans
    private boolean gameOver = false;
    private boolean replay = false;

    //------Image size, and location
    private final static int END_SCREEN_WIDTH = 730;
    private final static int END_SCREEN_HEIGHT = 500;
    private final static int END_SCREEN_X_AND_Y = 100;

    private final static int END_SCREEN_TEXT_Y = 352;
    private final static int END_SCREEN_TEXT_TIME_X = 170;
    private final static int END_SCREEN_TEXT_DEATHS_X = 422;
    private final static int END_SCREEN_TEXT_COINS_X = 690;


    //------FontMetrics
    private FontMetrics fontMetrics;

    public GamePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

	this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	this.setDoubleBuffered(true);
        this.repaint();
        this.world = new World();
        this.addKeyListener(keyH);
        this.setFocusable(true);

        Canvas c = new Canvas();
        Font font = new Font("Ubuntu", Font.BOLD, FONT_SIZE);
        this.fontMetrics = c.getFontMetrics(font);


        Logger logger = Logger.getLogger(GamePanel.class.getName() );
        SimpleFormatter formatter = new SimpleFormatter();
        try {
            FileHandler fileHandler = new FileHandler("LogFile.log", 0, 1, true);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(formatter);

            loseImage = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "lose_image.png"));
            winImage = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "win_image2.png"));

        } catch (IOException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
    }

    public EnumMap<EntityType, BufferedImage> createTileMap(){
        BufferedImage wall = null, platform = null, player = null, spikes = null, door = null, chest = null, timeBoost = null,
                jumpBoost = null, speedBoost = null, enemy = null, enemyAttack = null;

        Logger logger = Logger.getLogger(GamePanel.class.getName() );
        SimpleFormatter formatter = new SimpleFormatter();
        FileHandler fileHandler = null;
        try{
            fileHandler = new FileHandler("LogFile.log", 0, 1, true);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(formatter);

            platform = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "platform2.png"));
            wall = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "wall.png"));
            player = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "knight3.png"));
            spikes = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "spikes4.png"));
            door = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "door.png"));
            chest = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "chest.png"));
            timeBoost = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "time_powerup.png"));
            jumpBoost = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "jump_powerup.png"));
            speedBoost = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "speed_powerup.png"));
            enemy = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "wizard4.png"));
            enemyAttack = ImageIO.read(ClassLoader.getSystemResource(URL_STRING + "enemy_attack3.png"));


        } catch (IOException e) {
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        logger.removeHandler(fileHandler);
        if(fileHandler != null){
            fileHandler.close();
        }


        EnumMap<EntityType, BufferedImage> tileMap = new EnumMap<>(EntityType.class);
        tileMap.put(EntityType.WALL, wall);
        tileMap.put(EntityType.PLATFORM, platform);
        tileMap.put(EntityType.PLAYER, player);
        tileMap.put(EntityType.OBSTACLE, spikes);
        tileMap.put(EntityType.GOAL, door);
        tileMap.put(EntityType.COINS, chest);
        tileMap.put(EntityType.POWER_UP_TIME, timeBoost);
        tileMap.put(EntityType.POWER_UP_JUMP, jumpBoost);
        tileMap.put(EntityType.POWER_UP_SPEED, speedBoost);
        tileMap.put(EntityType.ENEMY, enemy);
        tileMap.put(EntityType.ENEMY_ATTACK, enemyAttack);


        return tileMap;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void tryIfGameOver() {
        if (world.getGameTime() == 0 || world.isGameWon()) {
            gameOver = true;
        }

    }


    @Override public void run() {
        final int fps = 60;
        final int billion = 1000000000;
        double drawInterval = billion/(double)fps; // 1 second in nanoseconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            tryIfGameOver();

            if(gameOver){
                updatePauseKeys();
                if(replay){
                    world = new World();
                    keyH.resetKeys();
                    gameOver = false;
                    replay = false;
                }
            }else{
                world.updateWorld();
                updateGameKeys();
            }
            repaint();

            Logger logger = Logger.getLogger(GamePanel.class.getName() );
            SimpleFormatter formatter = new SimpleFormatter();
            FileHandler fileHandler = null;
            try {
                fileHandler = new FileHandler("LogFile.log", 0, 1, true);
                logger.addHandler(fileHandler);
                fileHandler.setFormatter(formatter);

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }


                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException | IOException e) {
                logger.info(e.getMessage());
                e.printStackTrace();

            }
            logger.removeHandler(fileHandler);
            if(fileHandler != null){
                fileHandler.close();
            }
        }
    }

    public void updateGameKeys(){
        if (keyH.isUpPressed()){
            world.getPlayer().movePlayer(Direction.UP);
        }
        if (keyH.isDownPressed()){
            world.getPlayer().movePlayer(Direction.DOWN);
        }
        if (keyH.isLeftPressed()){
            world.getPlayer().movePlayer(Direction.LEFT);
        }
        if (keyH.isRightPressed()){
            world.getPlayer().movePlayer(Direction.RIGHT);
        }
        if (keyH.isSpacePressed() && world.getPlayer().canJump()){
            world.getPlayer().setIsJumping(true);
        }

    }
    public void updatePauseKeys(){
        if(keyH.isReplayPressed()){
            replay = true;
        }
        if(keyH.isQuitPressed()){
            mainFrame.setCurrentFrame("gameFrame");
            mainFrame.startMenu();

        }
    }


    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);



        //Paint background
        for (int h = 0; h < ROWS; h++) {
            for (int w = 0; w < COLUMNS; w++) {
                g.drawImage(tileMap.get(EntityType.WALL), w * TILE_SIZE, h * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
            }
        }
        //Paint start door
        g.drawImage(tileMap.get(EntityType.GOAL),
                    world.getPlayer().getStartX(),
                    world.getPlayer().getStartY(), TILE_SIZE, TILE_SIZE, null);
        //Paint Entitys
        for (int i = 0; i < world.getEntities().size(); i++) {
            Entity entity= world.getEntities().get(i);
            g.drawImage(tileMap.get(entity.getEntityType()), entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight(), null);
        }

        //Paint Enemy Attacks
        if(world.getEnemy() != null){
            for (EnemyAttack attack: world.getEnemyAttack()) {
                g.drawImage(tileMap.get(EntityType.ENEMY_ATTACK), attack.x, attack.y, AbstractEntity.WIDTH, AbstractEntity.HEIGHT, null);
            }
        }


        //Paint timer
        String text = Integer.toString(world.getGameTime());
        g.setFont(new Font("Ubuntu", Font.BOLD, FONT_SIZE));
        int x = ((getWidth() - fontMetrics.stringWidth(text)) / 2);
        g.setColor(Color.WHITE);
        g.drawString(text, x, TILE_SIZE);

        //Paint player
        g.drawImage(tileMap.get(EntityType.PLAYER),
                    world.getPlayer().x,
                    world.getPlayer().y,
                    world.getPlayer().getWidth(),
                    world.getPlayer().getHeight(),null);

        // Game Over
        if(gameOver){

            if(world.isGameWon()){
                String time = Integer.toString(world.getScoreTime());
                String deaths = Integer.toString(world.getDeathCounter());
                String coins = Integer.toString(world.getCoinCounter());

                g.setFont(new Font("Comic Sans MS", Font.PLAIN, FONT_SIZE));
                g.setColor(Color.WHITE);
                g.drawImage(winImage, END_SCREEN_X_AND_Y, END_SCREEN_X_AND_Y ,END_SCREEN_WIDTH, END_SCREEN_HEIGHT, this);
                g.drawString(time, END_SCREEN_TEXT_TIME_X, END_SCREEN_TEXT_Y);
                g.drawString(deaths, END_SCREEN_TEXT_DEATHS_X, END_SCREEN_TEXT_Y);
                g.drawString(coins, END_SCREEN_TEXT_COINS_X, END_SCREEN_TEXT_Y);
            }
            else{
                g.drawImage(loseImage, END_SCREEN_X_AND_Y, END_SCREEN_X_AND_Y ,END_SCREEN_WIDTH, END_SCREEN_HEIGHT, this);
            }

        }

    }


}
