package se.liu.joeri765youdr728.Platformer;

import javax.swing.*;

public class Frame
{
    private JFrame menuFrame = new JFrame();
    private JFrame gameFrame = new JFrame();
    private JFrame scoreFrame = new JFrame();

    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;

    private String gameFrameString = "gameFrame";
    private String highscoreFrameString = "highscoreFrame";
    private String menuFrameString = "menuFrame";

    private String currentFrame = menuFrameString;

    public void startMenu(){
	menuPanel = new MenuPanel(this, 1);

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
	scorePanel = new ScorePanel(this, 2);

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
	Frame frame = new Frame();
	frame.startMenu();
    }
}
