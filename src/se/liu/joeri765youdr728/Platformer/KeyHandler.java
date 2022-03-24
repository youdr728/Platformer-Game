package se.liu.joeri765youdr728.Platformer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class KeyHandler extends KeyAdapter
{
    private boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, replayPressed, quitPressed;


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

    public boolean isUpPressed() {
	return upPressed;
    }

    public boolean isDownPressed() {
	return downPressed;
    }

    public boolean isLeftPressed() {
	return leftPressed;
    }

    public boolean isRightPressed() {
	return rightPressed;
    }

    public boolean isSpacePressed() {
	return spacePressed;
    }

    public boolean isReplayPressed() {
	return replayPressed;
    }

    public boolean isQuitPressed() {
	return quitPressed;
    }

    public void resetKeys() {
	leftPressed = false;
	rightPressed = false;
	spacePressed = false;
    }
}
