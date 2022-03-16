package se.liu.joeri765youdr728.Platformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;

public class GamePanel extends JComponent implements  Runnable
{
    //Screen settings
    final int originalTileSize = 16;
    final  int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int columns = 20;
    final int rows = 20;
    final int screenHeight = rows * tileSize;
    final int screenWidth = columns * tileSize;


    public static BufferedImage wall, platform, player, spikes, door, chest, timeBoost, jumpBoost, speedBoost;
    private GameWorld world;
    protected final EnumMap<EntityType, BufferedImage> tileMap = createTileMap();

    final int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public GamePanel() {
	this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	this.setDoubleBuffered(true);
        this.repaint();
        this.world = new GameWorld();
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public static EnumMap<EntityType, BufferedImage> createTileMap(){

        try {
            platform = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/platform2.png"));
            wall = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/wall.png"));
            player = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/knight3.png"));
            spikes = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/spikes4.png"));
            door = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/door.png"));
            chest = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/chest.png"));
            timeBoost = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/time_powerup.png"));
            jumpBoost = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/jump_powerup.png"));
            speedBoost = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/speed_powerup.png"));

        } catch (IOException e) {
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

        return tileMap;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public boolean gameOver() {
        if (world.getGameTime() <= 0) {
            return true;
        }
        return false;
    }


    @Override public void run() {

        double drawInterval = 1000000000/FPS; // 1 second in nanoseconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            long currentTime = System.nanoTime();

            update();

            world.updateWorld();

            repaint();



            try {
                double remaningTime = nextDrawTime - System.nanoTime();
                remaningTime = remaningTime/1000000;
                if(remaningTime < 0){
                    remaningTime = 0;
                }

                Thread.sleep((long) remaningTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (gameOver()) {
                int reply = JOptionPane.showConfirmDialog(null, "Play Again?", "", JOptionPane.YES_NO_OPTION);

                if (reply == JOptionPane.NO_OPTION) {
                    break;
                }
                else if (reply == JOptionPane.YES_OPTION) {
                    world = new GameWorld();
                    world.setGameTime(100);
                    keyH.keyReset();
                }
            }
        }

    }

    public void update(){
        if (keyH.upPressed){
            world.getPlayer().movePlayer(Direction.UP);
        }
        if (keyH.downPressed){
            world.getPlayer().movePlayer(Direction.DOWN);
        }
        if (keyH.leftPressed){
            world.getPlayer().movePlayer(Direction.LEFT);
        }
        if (keyH.rightPressed){
            world.getPlayer().movePlayer(Direction.RIGHT);
        }
        if (keyH.spacePressed && world.getPlayer().CanJump()){
            world.getPlayer().setJumping(true);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

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

        //Paint time
       // g.setColor(Color.BLACK);
       // g.drawRect(world.getWorldWidth(),30,200,100 );
       // g.drawImage(tileMap.get(EntityType.PLATFORM), world.getWorldWidth()/2 - 5,5,98,60,null );
        String text = Integer.toString(world.getGameTime());
        Font font = new Font("Ubuntu", Font.BOLD,50);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        int x = ((getWidth() - fm.stringWidth(text)) / 2);
        //int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();

        g.setColor(Color.WHITE);
        g.drawString(text, x,tileSize);


        //Paint player
        g.drawImage(tileMap.get(EntityType.PLAYER),
                    world.getPlayer().x,
                    world.getPlayer().y,
                    world.getPlayer().getWidth(),
                    world.getPlayer().getHeight(),null);



    }
}
