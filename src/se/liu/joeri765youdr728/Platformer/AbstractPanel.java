package se.liu.joeri765youdr728.Platformer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractPanel extends JPanel
{
    final int originalTileSize = 16;
    final int scale = 3;

    final int tileSize = originalTileSize * scale;
    final int columns = 20;
    final int rows = 20;
    final int screenHeight = rows * tileSize;
    final int screenWidth = columns * tileSize;

    protected int typeNumber;

    private Frame frame;

    MouseHandler mouseHandler = new MouseHandler(this);

    public AbstractPanel() {
	this.setPreferredSize(new Dimension(screenWidth, screenHeight));
	this.setDoubleBuffered(true);
	this.repaint();
	this.setFocusable(true);
	this.addMouseListener(mouseHandler);
	this.addMouseMotionListener(mouseHandler);
	this.frame = frame;
    }


    public void mouseClicked(int x, int y){

    }


    public void mouseMoved(int x, int y){

    }

}
