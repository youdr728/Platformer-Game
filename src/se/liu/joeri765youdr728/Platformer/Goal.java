package se.liu.joeri765youdr728.Platformer;

import javax.swing.*;

public class Goal extends AbstractEntity
{
    public Goal(final int x, final int y, final int typeNumber,
		int collisionX, int collisionY, int collisionWidth, int collisionHeight, final GameWorld world) {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
    }


}
