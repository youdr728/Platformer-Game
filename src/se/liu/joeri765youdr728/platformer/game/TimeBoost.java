package se.liu.joeri765youdr728.platformer.game;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class TimeBoost extends AbstractEntity
{

    private final static  int TIME_BOOST = 15;
    public TimeBoost(final int x, final int y, final int typeNumber, final int collisionX, final int collisionY, final int collisionWidth,
		     final int collisionHeight)
    {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
    }

    public static int getTime() {
	return TIME_BOOST;
    }
}

