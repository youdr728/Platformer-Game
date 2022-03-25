package se.liu.joeri765youdr728.platformer.input;

import se.liu.joeri765youdr728.platformer.AbstractPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class MouseHandler extends MouseAdapter
{
    private AbstractPanel panel;

    public MouseHandler(AbstractPanel panel) {
	this.panel = panel;
    }

    @Override public void mouseClicked(final MouseEvent e) {
	if(e.getButton() == MouseEvent.BUTTON1){
	    panel.mouseClicked(e.getX(), e.getY());
	}
    }

    @Override public void mouseMoved(final MouseEvent e) {
	panel.mouseMoved(e.getX(), e.getY());
    }


}
