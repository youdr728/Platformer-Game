package se.liu.joeri765youdr728.Platformer;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class Platform extends AbstractEntity
{
    public Platform(final int x, final int y, final int typeNumber,
		    int collisionX, int collisionY, int collisionWidth, int collisionHeight) {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
    }
}
