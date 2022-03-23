package se.liu.joeri765youdr728.Platformer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler extends KeyAdapter
{
    public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, replayPressed, quitPressed;


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
	if(code == KeyEvent.VK_ENTER){
	    replayPressed = true;
	}
	if(code == KeyEvent.VK_ESCAPE){
	    quitPressed = true;
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
	if(code == KeyEvent.VK_ENTER){
	    replayPressed = false;
	}
	if(code == KeyEvent.VK_ESCAPE){
	    quitPressed = false;
	}
    }

    public void keyReset() {
	leftPressed = false;
	rightPressed = false;
	spacePressed = false;
    }
}
