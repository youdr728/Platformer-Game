package se.liu.joeri765youdr728.Platformer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

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
