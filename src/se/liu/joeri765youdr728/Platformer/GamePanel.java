package se.liu.joeri765youdr728.Platformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JComponent implements  Runnable
{
    //Screen settings
    final int originalTileSize = 16;
    final  int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int columns = 16;
    final int rows = 12;
    final int screenHeight = rows * tileSize;
    final int screenWidth = columns * tileSize;

    public static BufferedImage wall, platform, player, spikes, door, chest;
    private GameWorld world;
    protected final EnumMap<EntityType, BufferedImage> tileMap = creatTileMap();

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

    public static EnumMap<EntityType, BufferedImage> creatTileMap(){

        try {
            platform = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/earth.png"));
            wall = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/wall.png"));
            player = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/player.png"));
            spikes = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/new_spikes.png"));
            door = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/door.png"));
            chest = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/chest.png"));

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

        return tileMap;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override public void run() {

        double drawInterval = 1000000000/FPS; // 1 second in nanoseconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            long currentTime = System.nanoTime();
            update();

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

        //Paint Entitys
        for (int i = 0; i < world.getEntityList().size(); i++) {
            g.drawImage(tileMap.get(world.getEntityList().get(i).getEntityType()),
                        world.getEntityList().get(i).getX(),
                        world.getEntityList().get(i).getY(),
                        world.getEntityList().get(i).getWidth(),
                        world.getEntityList().get(i).getHeight(),null);
        }

        //Paint player
        g.drawImage(tileMap.get(EntityType.PLAYER),
                    world.getPlayer().x,
                    world.getPlayer().y,
                    world.getPlayer().getWidth(),
                    world.getPlayer().getHeight(),null);

    }
}
