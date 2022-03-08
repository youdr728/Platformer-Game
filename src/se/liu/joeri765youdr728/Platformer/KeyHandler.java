package se.liu.joeri765youdr728.Platformer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener
{
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;

    @Override public void keyTyped(final KeyEvent e) {

    }

    @Override public void keyPressed(final KeyEvent e) {
	int code = e.getKeyCode();

	if(code == KeyEvent.VK_W){

	    upPressed = true;
	}
	if(code == KeyEvent.VK_S){
	    downPressed = true;
	}
	if(code == KeyEvent.VK_A){
	    leftPressed = true;
	}
	if(code == KeyEvent.VK_D){
	    rightPressed = true;
	}
	if(code == KeyEvent.VK_SPACE){
	    spacePressed = true;
	}

    }

    @Override public void keyReleased(final KeyEvent e) {
	int code = e.getKeyCode();

	if(code == KeyEvent.VK_W){
	    upPressed = false;
	}
	if(code == KeyEvent.VK_S){
	    downPressed = false;
	}
	if(code == KeyEvent.VK_A){
	    leftPressed = false;
	}
	if(code == KeyEvent.VK_D){
	    rightPressed = false;
	}
	if(code == KeyEvent.VK_SPACE){
	    spacePressed = false;
	}
    }
}
