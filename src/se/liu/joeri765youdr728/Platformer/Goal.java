package se.liu.joeri765youdr728.Platformer;

import javax.swing.*;

public class Goal extends AbstractEntity
{

    private GameWorld world;
    private int mapNumber;

    public Goal(final int x, final int y, final int typeNumber,
		int collisionX, int collisionY, int collisionWidth, int collisionHeight, final GameWorld world) {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
	this.world = world;
    }

    public String moveToNextMap(){
	mapNumber = mapNumber + 1;
	String nextMap = "Maps/map" + mapNumber;
	return "Maps/map" + mapNumber;
    }

}
