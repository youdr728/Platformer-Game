package se.liu.joeri765youdr728.Platformer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.Buffer;
import java.util.EnumMap;

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
    private BufferedImage wall;


    public GamePanel() {
	this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	this.setDoubleBuffered(true);
        this.repaint();

    }



    @Override
    protected void paintComponent(Graphics g) {

        try {
            wall = ImageIO.read(getClass().getResourceAsStream("wall.png"));


        } catch (IOException e) {
            e.printStackTrace();
        }

        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        //icon = new ImageIcon("wall.png");
        //BufferedIma  ge image =


        //Paint background
        for (int h = 0; h < columns; h++) {
            for (int w = 0; w < rows; w++) {
                g.drawImage(wall, h * tileSize, w * tileSize, tileSize, tileSize,null);
            }
        }




    }



}
