package se.liu.joeri765youdr728.Platformer;

import javax.swing.*;

public class Goal extends AbstractEntity
{

    private GameWorld world;
    public Goal(final int x, final int y, final int typeNumber, final GameWorld world) {
	super(x, y, typeNumber);
	this.world = world;
    }

    public void moveToNextMap(){
	String map = JOptionPane.showInputDialog("select map number");
	world.loadMapFromFile("Maps/map" + map);
	world.createEntityList();

    }

}
