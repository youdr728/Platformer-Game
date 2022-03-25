package se.liu.joeri765youdr728.platformer.game;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class Goal extends AbstractEntity
{
    public Goal(final int x, final int y, final int typeNumber,
		int collisionX, int collisionY, int collisionWidth, int collisionHeight, final GameWorld world) {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
    }


}
