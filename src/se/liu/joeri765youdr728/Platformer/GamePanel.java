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

public class GamePanel extends JComponent implements WorldListener
{
    //Screen settings
    final int originalTileSize = 16;
    final  int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int columns = 16;
    final int rows = 12;
    final int screenHeight = rows * tileSize;
    final int screenWidth = columns * tileSize;

    public static BufferedImage wall, platform, player;
    private GameWorld world;
    protected final EnumMap<EntityType, BufferedImage> tileMap = creatTileMap();


    public GamePanel() {
	this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	this.setDoubleBuffered(true);
        this.repaint();
        this.world = new GameWorld();
        world.addWorldListener(this);
        bindKeys();
    }

    public static EnumMap<EntityType, BufferedImage> creatTileMap(){

        try {
            platform = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/earth.png"));
            wall = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/wall.png"));
            player = ImageIO.read(GamePanel.class.getResourceAsStream("Tiles/player.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        EnumMap<EntityType, BufferedImage> tileMap = new EnumMap<>(EntityType.class);
        tileMap.put(EntityType.WALL, wall);
        tileMap.put(EntityType.PLATFORM, platform);
        tileMap.put(EntityType.PLAYER, player);

        return tileMap;
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
                        world.getEntityList().get(i).getX() * tileSize,
                        world.getEntityList().get(i).getY() * tileSize,
                           world.getEntityList().get(i).getWidth(),
                           world.getEntityList().get(i).getHeight(),null);
        }
        g.drawImage(tileMap.get(EntityType.PLAYER),
                    world.getPlayer().x * tileSize,
                    world.getPlayer().y * tileSize,
                       world.getPlayer().getWidth(),
                       world.getPlayer().getHeight(),null);

    }

    @Override public void worldChanged() {
        repaint();
    }

    private class MoveAction extends AbstractAction {
        private Direction dir;

        private MoveAction(final Direction dir) {
            this.dir = dir;
        }
        @Override public void actionPerformed(final ActionEvent e) {

            world.getPlayer().movePlayer(dir);
        }
    }

    private class QuitAction extends AbstractAction {
        @Override public void actionPerformed(final ActionEvent e) {
            // Gör vad som behövs -- öppna dialogruta och fråga, ...
            System.exit(0);
        }
    }

    private void bindKeys() {
        final InputMap in = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        in.put(KeyStroke.getKeyStroke("LEFT"), "left");
        in.put(KeyStroke.getKeyStroke("RIGHT"), "right");
        in.put(KeyStroke.getKeyStroke("UP"),"up");
        in.put(KeyStroke.getKeyStroke("DOWN"),"down");
        in.put(KeyStroke.getKeyStroke("SPACE"),"space");


        final ActionMap act = this.getActionMap();

        act.put("left", new MoveAction(Direction.LEFT));
        act.put("right", new MoveAction(Direction.RIGHT));



    }

}
