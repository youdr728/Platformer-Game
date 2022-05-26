package se.liu.joeri765youdr728.platformer.game;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * A class for the entity enemy that describes what a enemy is and the functions related to a enemy like creating and moving its attack
 */
public class Enemy extends AbstractEntity
{
    private List<EnemyAttack> enemyAttacks;

    private int attackCounter = 0;
    private int randomNumber;
    private final static int ENEMY_ATTACK_SPEED = 6;

    private static final Random RANDOM = new Random();

    public Enemy(final int x, final int y, final EntityType entityType, final int collisionX, final int collisionY, final int collisionWidth,
		 final int collisionHeight) {
	super(x, y, entityType, collisionX, collisionY, collisionWidth, collisionHeight);
	this.enemyAttacks = new ArrayList<>();
    }

    public void shootAttack(){
	//Shoots an attack at random intervals
	if(attackCounter == randomNumber){
	    final int slowestAttack = 50;
	    final int fastestAttack = 30;
	    final int tileSize = 48;

	    randomNumber = getRandomNumberUsingNextInt(fastestAttack, slowestAttack);
	    EnemyAttack
		    attack = new EnemyAttack(x - tileSize, y, EntityType.ENEMY_ATTACK, 12, 12, 27, 27);
	    enemyAttacks.add(attack);
	    attackCounter = 0;
	}
	else {
	    attackCounter += 1;
	}

    }
    public int getRandomNumberUsingNextInt(int min, int max) {
	return RANDOM.nextInt(max - min) + min;
    }

    public void moveAttack(){
	for (int i = 0; i < enemyAttacks.size(); i++) {
	    enemyAttacks.get(i).x -= ENEMY_ATTACK_SPEED;
	    if( enemyAttacks.get(i).x < 0){
		enemyAttacks.remove(enemyAttacks.get(i));
		break;
	    }
	}
    }

    public List<EnemyAttack> getEnemyAttacks() {
	return enemyAttacks;
    }
}
