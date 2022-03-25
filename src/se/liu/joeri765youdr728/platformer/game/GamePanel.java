package se.liu.joeri765youdr728.platformer.game;

import se.liu.joeri765youdr728.platformer.input.Direction;
import se.liu.joeri765youdr728.platformer.MainFrame;
import se.liu.joeri765youdr728.platformer.Sound;
import se.liu.joeri765youdr728.platformer.input.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
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
    private GameWorld world;
    private Sound sound = new Sound();
    private KeyHandler keyH = new KeyHandler();
    private Thread gameThread = null;

    //------Logging
    private static final Logger LOGGER = Logger.getLogger(GamePanel.class.getName() );
    private static SimpleFormatter formatter = new SimpleFormatter();
    private static FileHandler fh;
    static {
        try {
            fh = new FileHandler("LogFile.log", 0, 1, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //------Images and fileReading
    protected final EnumMap<EntityType, BufferedImage> tileMap = createTileMap();
    private BufferedImage loseImage = null, winImage = null;
    private final static String SEPARATOR = File.separator;

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






    public GamePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

	this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	this.setDoubleBuffered(true);
        this.repaint();
        this.world = new GameWorld(this);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        try {
            LOGGER.addHandler(fh);
            fh.setFormatter(formatter);

            loseImage = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "lose_image.png"));
            winImage = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "win_image2.png"));

        } catch (IOException e) {
            LOGGER.log(Level.FINE, e.getMessage());
            e.printStackTrace();
        }
    }

    public static EnumMap<EntityType, BufferedImage> createTileMap(){
        BufferedImage wall = null, platform = null, player = null, spikes = null, door = null, chest = null, timeBoost = null,
                jumpBoost = null, speedBoost = null, enemy = null, enemyAttack = null;

        try{
            LOGGER.addHandler(fh);
            fh.setFormatter(formatter);

            platform = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "platform2.png"));
            wall = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "wall.png"));
            player = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "knight3.png"));
            spikes = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "spikes4.png"));
            door = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "door.png"));
            chest = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "chest.png"));
            timeBoost = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "time_powerup.png"));
            jumpBoost = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "jump_powerup.png"));
            speedBoost = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "speed_powerup.png"));
            enemy = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "wizard4.png"));
            enemyAttack = ImageIO.read(ClassLoader.getSystemResource("images" + SEPARATOR + "enemy_attack3.png"));


        } catch (IOException e) {
            LOGGER.log(Level.FINE, e.getMessage());
            e.printStackTrace();
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
    public void playMusic(int i){
        sound.setFileMusic(i);
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSoundEffect(int i){
        sound.setFileSound(i);
        sound.playSound();

    }

    @Override public void run() {
        final int fps = 60;
        final int billion = 1000000000;
        double drawInterval = billion/(double)fps; // 1 second in nanoseconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            tryIfGameOver();

            if(gameOver){
                stopMusic();
                updatePauseKeys();
                if(replay){
                    world = new GameWorld(this);
                    keyH.resetKeys();
                    gameOver = false;
                    replay = false;

                }
            }else{

                world.updateWorld();
                updateGameKeys();

            }

            repaint();


            try {
                LOGGER.addHandler(fh);
                fh.setFormatter(formatter);

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1000000;
                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
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
            playSoundEffect(5);
        }

    }
    public void updatePauseKeys(){
        if(keyH.isReplayPressed()){
            stopMusic();
            replay = true;
        }
        if(keyH.isQuitPressed()){
            stopMusic();
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
            g.drawImage(tileMap.get(world.getEntities().get(i).getEntityType()),
                        world.getEntities().get(i).getX(),
                        world.getEntities().get(i).getY(),
                        world.getEntities().get(i).getWidth(),
                        world.getEntities().get(i).getHeight(), null);
        }

        //Paint Enemy Attacks
        if(world.getEnemy() != null){
            for (EnemyAttack attack: world.getEnemyAttack()) {
                g.drawImage(tileMap.get(EntityType.ENEMY_ATTACK), attack.x, attack.y, AbstractEntity.WIDTH, AbstractEntity.HEIGHT, null);
            }
        }


        //Paint timer
        String text = Integer.toString(world.getGameTime());
        Font font = new Font("Ubuntu", Font.BOLD, FONT_SIZE);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int x = ((getWidth() - fm.stringWidth(text)) / 2);
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
                g.drawImage(winImage, 100, 100 ,731, 500, this);
                g.drawString(time, 170, 352);
                g.drawString(deaths, 422, 352);
                g.drawString(coins, 690, 352);
            }
            else{
                g.drawImage(loseImage, 100, 100 ,731, 400, this);
            }

        }

    }


}
