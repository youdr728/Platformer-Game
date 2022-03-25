package se.liu.joeri765youdr728.platformer;

import se.liu.joeri765youdr728.platformer.input.MouseHandler;

import javax.swing.*;
import java.awt.*;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public abstract class AbstractPanel extends JPanel
{
    protected static final int ORIGINAL_TILE_SIZE = 16;
    protected static final int SCALE = 3;

    protected static final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    protected static final int COLUMNS = 20;
    protected static final int ROWS = 20;
    protected static final int SCREEN_HEIGHT = ROWS * TILE_SIZE;
    protected static final int SCREEN_WIDTH = COLUMNS * TILE_SIZE;

    protected AbstractPanel() {
	this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
	this.setDoubleBuffered(true);
	this.repaint();
	this.setFocusable(true);
	MouseHandler mouseHandler = new MouseHandler(this);
	this.addMouseListener(mouseHandler);
	this.addMouseMotionListener(mouseHandler);
    }


    public abstract void mouseClicked(int x, int y);

    public abstract void mouseMoved(int x, int y);

}
