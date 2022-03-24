package se.liu.joeri765youdr728.Platformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class GamePanel extends JComponent implements  Runnable
{
    //Screen settings
    private final int originalTileSize = 16;
    private final int scale = 3;

    private final int tileSize = originalTileSize * scale;
    private final int columns = 20;
    private final int rows = 20;
    private final int screenHeight = rows * tileSize;
    private final int screenWidth = columns * tileSize;

    private final static int fontSize = 50;

    private static final Logger LOGGER = Logger.getLogger(GamePanel.class.getName() );

    private GameWorld world;
    protected final EnumMap<EntityType, BufferedImage> tileMap = createTileMap();

    private final int FPS = 60;
    private boolean gameOver = false;
    private boolean replay = false;

    private Sound sound = new Sound();

    private KeyHandler keyH = new KeyHandler();
    private Thread gameThread = null;

    private MainFrame mainFrame;

    private BufferedImage loseImage = null, winImage = null;

    public GamePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

	this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	this.setDoubleBuffered(true);
        this.repaint();
        this.world = new GameWorld(this);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        try {
            loseImage = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"lose_image.png"));

            winImage = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"win_image2.png"));
        } catch (IOException e) {
            LOGGER.log(Level.FINE, e.getMessage());
            e.printStackTrace();
        }
    }

    public static EnumMap<EntityType, BufferedImage> createTileMap(){
        BufferedImage wall = null, platform = null, player = null, spikes = null, door = null, chest = null, timeBoost = null,
                jumpBoost = null, speedBoost = null, enemy = null, enemyAttack = null;

        try{
            platform = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"platform2.png"));
            wall = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"wall.png"));
            player = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"knight3.png"));
            spikes = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"spikes4.png"));
            door = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"door.png"));
            chest = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"chest.png"));
            timeBoost = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"time_powerup.png"));
            jumpBoost = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"jump_powerup.png"));
            speedBoost = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"speed_powerup.png"));
            enemy = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"wizard4.png"));
            enemyAttack = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles" + File.separator +"enemy_attack3.png"));


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
    public void stopGameThread(){
        gameThread = null;
    }

    public void checkGameOver() {
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

        double drawInterval = 1000000000/FPS; // 1 second in nanoseconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            long currentTime = System.nanoTime();
            checkGameOver();

            if(gameOver){
                stopMusic();
                updatePauseKeys();
                if(replay){
                    world = new GameWorld(this);
                    keyH.keyReset();
                    gameOver = false;
                    replay = false;

                }
            }else{

                world.updateWorld();
                updateGameKeys();

            }

            repaint();


            try {
                double remaningTime = nextDrawTime - System.nanoTime();
                remaningTime /= 1000000;
                if(remaningTime < 0){
                    remaningTime = 0;
                }

                Thread.sleep((long) remaningTime);

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
        for (int h = 0; h < rows; h++) {
            for (int w = 0; w < columns; w++) {
                g.drawImage(tileMap.get(EntityType.WALL), w * tileSize, h * tileSize, tileSize, tileSize,null);
            }
        }
        //Paint start door
        g.drawImage(tileMap.get(EntityType.GOAL),
                    world.getPlayer().getStartX(),
                    world.getPlayer().getStartY(),
                    tileSize,
                    tileSize,null);
        //Paint Entitys
        for (int i = 0; i < world.getEntityList().size(); i++) {
            g.drawImage(tileMap.get(world.getEntityList().get(i).getEntityType()),
                        world.getEntityList().get(i).getX(),
                        world.getEntityList().get(i).getY(),
                        world.getEntityList().get(i).getWidth(),
                        world.getEntityList().get(i).getHeight(),null);
        }

        //Paint Enemy Attacks
        if(world.getEnemy() != null){
            for (EnemyAttack attack: world.getEnemyAttack()) {
                g.drawImage(tileMap.get(EntityType.ENEMY_ATTACK),attack.x, attack.y, attack.width, attack.height,null);
            }
        }


        //Paint timer
        String text = Integer.toString(world.getGameTime());
        Font font = new Font("Ubuntu", Font.BOLD, fontSize);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int x = ((getWidth() - fm.stringWidth(text)) / 2);
        g.setColor(Color.WHITE);
        g.drawString(text, x,tileSize);

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

                g.setFont(new Font("Comic Sans MS", Font.PLAIN, fontSize));
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
