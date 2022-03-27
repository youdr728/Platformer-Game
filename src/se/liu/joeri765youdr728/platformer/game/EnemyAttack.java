package se.liu.joeri765youdr728.platformer.game;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class EnemyAttack extends AbstractEntity
{
    public EnemyAttack(final int x, final int y, final EntityType entityType, final int collisionX, final int collisionY, final int collisionWidth,
		       final int collisionHeight) {
	super(x, y, entityType, collisionX, collisionY, collisionWidth, collisionHeight);
    }


}
