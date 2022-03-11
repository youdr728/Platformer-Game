package se.liu.joeri765youdr728.Platformer;

public class Obstacle extends AbstractEntity
{

    public Obstacle(final int x, final int y, final int typeNumber,
		    int collisionX, int collisionY, int collisionWidth, int collisionHeight) {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
    }
}
