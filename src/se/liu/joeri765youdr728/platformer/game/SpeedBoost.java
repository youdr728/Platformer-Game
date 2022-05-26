package se.liu.joeri765youdr728.platformer.game;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * A class for the entity Speedboost which is a powerup that the player can pick up for a small boost in vertical speed
 */
public class SpeedBoost extends AbstractEntity
{
    public SpeedBoost(final int x, final int y, final EntityType entityType, final int collisionX, final int collisionY, final int collisionWidth,
		      final int collisionHeight)
    {
	super(x, y, entityType, collisionX, collisionY, collisionWidth, collisionHeight);
    }
}
