package se.liu.joeri765youdr728.Platformer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPanel extends JPanel
{
    protected final int originalTileSize = 16;
    protected final int scale = 3;

    protected final int tileSize = originalTileSize * scale;
    protected final int columns = 20;
    protected final int rows = 20;
    protected int screenHeight = rows * tileSize;
    protected int screenWidth = columns * tileSize;

    protected int typeNumber;

    protected AbstractPanel() {
	this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	this.setDoubleBuffered(true);
	this.repaint();
	this.setFocusable(true);
	MouseHandler mouseHandler = new MouseHandler(this);
	this.addMouseListener(mouseHandler);
	this.addMouseMotionListener(mouseHandler);
    }


    public void mouseClicked(int x, int y){}

    public void mouseMoved(int x, int y){}

}
