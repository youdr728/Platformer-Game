package se.liu.joeri765youdr728.platformer.game;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * A class for the entity Obstacle which is the spikes that kills the player if they colide in the game
 */
public class Obstacle extends AbstractEntity
{

    public Obstacle(final int x, final int y, final EntityType entityType,
		    int collisionX, int collisionY, int collisionWidth, int collisionHeight) {
	super(x, y, entityType, collisionX, collisionY, collisionWidth, collisionHeight);
    }
}
