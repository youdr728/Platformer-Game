package se.liu.joeri765youdr728.Platformer;

import javax.swing.*;
import java.awt.*;

public class Frame
{
    private JFrame menuFrame = new JFrame();
    private JFrame gameFrame = new JFrame();

    private CardLayout cardLayout = new CardLayout();

    private JPanel panel = new JPanel(cardLayout);

    MenuPanel menuPanel = new MenuPanel(this);
    GamePanel gamePanel = new GamePanel();

    public void show(){

	menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	menuFrame.setResizable(false);
	//frame.setLocationRelativeTo(null);


	//panel.add(gamePanel, "game");
	//panel.add(menuPanel, "menu");

	//cardLayout.show(panel, "menu");
	menuFrame.add(menuPanel);
	menuFrame.pack();
	menuFrame.setVisible(true);

    }

    public void startGame(){
	menuFrame.dispose();
	gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	gameFrame.setResizable(false);
	//frame.setLocationRelativeTo(null);


	//panel.add(gamePanel, "game");
	//panel.add(menuPanel, "menu");

	//cardLayout.show(panel, "menu");
	gameFrame.add(gamePanel);
	gameFrame.pack();
	gameFrame.setVisible(true);
	gamePanel.startGameThread();

    }

    public static void main(String[] args) {
	Frame frame = new Frame();
	frame.show();
    }
}
