package se.liu.joeri765youdr728.Platformer;

public class TimeBoost extends AbstractEntity
{

    private final static  int TIME_BOOST = 15;
    public TimeBoost(final int x, final int y, final int typeNumber, final int collisionX, final int collisionY, final int collisionWidth,
		     final int collisionHeight)
    {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
    }

    public static int getTime(GameWorld world) {
	return TIME_BOOST;
    }
}

