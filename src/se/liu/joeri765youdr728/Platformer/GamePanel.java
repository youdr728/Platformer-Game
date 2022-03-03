package se.liu.joeri765youdr728.Platformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class GamePanel extends JComponent
{
    //Screen settings
    final int originalTileSize = 16;
    final  int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int columns = 16;
    final int rows = 12;
    final int screenHeight = rows * tileSize;
    final int screenWidth = columns * tileSize;
    public static BufferedImage wall,platform, player;
    private GameWorld world;
    protected final Map<Integer, BufferedImage> tileMap = creatTileMap();


    public GamePanel() {
	this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	this.setDoubleBuffered(true);
        this.repaint();
        this.world = new GameWorld();

    }

    public static Map<Integer, BufferedImage> creatTileMap(){

        try {
            platform = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/earth.png"));
            wall = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/wall.png"));
            player = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/player.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Integer, BufferedImage> tileMap = new HashMap<>();
        tileMap.put(0, wall);
        tileMap.put(1, platform);
        tileMap.put(2, player);

        return tileMap;
    }



    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;

        //Paint background
        for (int h = 0; h < rows; h++) {
            for (int w = 0; w < columns; w++) {
                g.drawImage(tileMap.get(0), w * tileSize, h * tileSize, tileSize, tileSize,null);
            }
        }

        //Paint Entitys
        for (int h = 0; h < rows; h++) {
            for (int w = 0; w < columns; w++) {
                if (world.getMapTileNum()[h][w] != 0){
                    g.drawImage(tileMap.get(world.getMapTileNum()[h][w]),w * tileSize, h * tileSize, tileSize, tileSize,null);
                }
            }
        }


    }



}
