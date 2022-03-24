package se.liu.joeri765youdr728.Platformer;

import javax.swing.*;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class MainFrame
{
    private JFrame menuFrame = new JFrame();
    private JFrame gameFrame = new JFrame();
    private JFrame scoreFrame = new JFrame();

    private MenuPanel menuPanel = null;
    private GamePanel gamePanel = null;
    private ScorePanel scorePanel = null;

    private String menuFrameString = "menuFrame";

    private String currentFrame = menuFrameString;

    public void startMenu(){
	menuPanel = new MenuPanel(this);

	String gameFrameString = "gameFrame";
	String highscoreFrameString = "highscoreFrame";
	if(currentFrame.equals(highscoreFrameString)){
	    scorePanel.stopMusic();
	    scoreFrame.remove(scorePanel);
	    scoreFrame.dispose();

	}
	else if(currentFrame.equals(gameFrameString)){
	    gameFrame.remove(gamePanel);
	    gameFrame.dispose();

	}

	menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	menuFrame.setResizable(false);

	menuFrame.add(menuPanel);
	menuFrame.pack();
	menuFrame.setVisible(true);

    }

    public void startGame(){
	gamePanel = new GamePanel(this);

	menuFrame.remove(menuPanel);
	menuFrame.dispose();

	gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	gameFrame.setResizable(false);
	gameFrame.add(gamePanel);
	gameFrame.pack();
	gameFrame.setVisible(true);
	gamePanel.startGameThread();

    }

    public void startHighscore() {
	scorePanel = new ScorePanel(this);

	menuPanel.stopMusic();
	menuFrame.remove(menuPanel);
	menuFrame.dispose();

	scoreFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	scoreFrame.setResizable(false);
	scoreFrame.add(scorePanel);
	scoreFrame.pack();
	scoreFrame.setVisible(true);
    }


    public void setCurrentFrame(final String currentFrame) {
	this.currentFrame = currentFrame;
    }

    public static void main(String[] args) {
	MainFrame mainFrame = new MainFrame();
	mainFrame.startMenu();
    }
}
