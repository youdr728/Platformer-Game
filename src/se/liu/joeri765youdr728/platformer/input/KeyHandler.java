package se.liu.joeri765youdr728.platformer.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * A class for Handeling all the different keyinputs in the game
 */
public class KeyHandler extends KeyAdapter
{
    private boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed, replayPressed, quitPressed;


    @Override public void keyPressed(final KeyEvent e) {
	int code = e.getKeyCode();

	if(code == KeyEvent.VK_W){
	    //Kod analys weakwarning: Har en chain av if statments här för att det ska vara möjligt
	    //att till exempel hoppa samtidigt som man rör på spelaren fram och tillbaka
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
	    //Kod analys weakwarning: Har en chain av if statments här för att det ska vara möjligt
	    //att till exempel hoppa samtidigt som man rör på spelaren fram och tillbaka
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

    public boolean isMovementKeyPressed(Keys keys){
	switch (keys){
	    case LEFT -> {
		return leftPressed;
	    }
	    case RIGHT -> {
		return rightPressed;
	    }
	    case UP -> {
		return upPressed;
	    }
	    case DOWN -> {
		return downPressed;
	    }
	    case SPACE -> {
		return spacePressed;
	    }
	    case QUIT -> {
		return quitPressed;
	    }
	    case REPLAY -> {
		return replayPressed;
	    }
	}
	return false;
    }

    public void resetKeys() {
	leftPressed = false;
	rightPressed = false;
	spacePressed = false;
    }
}
