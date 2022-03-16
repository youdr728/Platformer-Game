package se.liu.joeri765youdr728.Platformer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class MouseHandler extends MouseAdapter
{
    private MenuPanel menu;
    public MouseHandler(MenuPanel menu) {
	this.menu = menu;
    }

    @Override public void mouseClicked(final MouseEvent e) {
	if(e.getButton() == MouseEvent.BUTTON1){
	    menu.mouseClicked(e.getX(), e.getY());
	}
    }

    @Override public void mouseMoved(final MouseEvent e) {
	menu.mouseMoved(e.getX(), e.getY());
    }

}
