package se.liu.joeri765youdr728.Platformer;

import javax.swing.*;

public class Frame
{
    public void show(){
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	frame.setResizable(false);
	//frame.setLocationRelativeTo(null);

	GamePanel gamePanel = new GamePanel();
	frame.add(gamePanel);

	frame.pack();
	frame.setVisible(true);
    }

    public static void main(String[] args) {
	Frame frame = new Frame();
	frame.show();
    }
}
