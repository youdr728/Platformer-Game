package se.liu.joeri765youdr728.platformer.game;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * A class for the entity coin which is a collectible that the player can pick up and it describes what a coin is in the game
 */
public class Coin extends AbstractEntity
{

    public Coin(final int x, final int y, final EntityType entityType,
                int collisionX, int collisionY, int collisionWidth, int collisionHeight) {
	super(x, y, entityType, collisionX, collisionY, collisionWidth, collisionHeight);

    }
}
