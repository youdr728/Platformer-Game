package se.liu.joeri765youdr728.Platformer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy extends AbstractEntity
{
    private GameWorld world;
    private List<EnemyAttack> enemyAttackList;

    private int attackCounter = 0;
    private int randomNumber;
    private final int SLOWEST_ATTACK = 50, FASTEST_ATTACK = 30;

    public Enemy(final int x, final int y, final int typeNumber, final int collisionX, final int collisionY, final int collisionWidth,
		 final int collisionHeight, final GameWorld world) {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
	this.world = world;
	this.enemyAttackList = new ArrayList<>();
    }

    public void shootAttack(){

	if(attackCounter == randomNumber){
	    randomNumber = getRandomNumberUsingNextInt(FASTEST_ATTACK, SLOWEST_ATTACK);
	    EnemyAttack attack = new EnemyAttack(x - 48, y, 10, 12, 12, 27, 27);
	    world.playSound(6);
	    enemyAttackList.add(attack);
	    attackCounter = 0;
	}
	else {
	    attackCounter += 1;
	}

    }
    public int getRandomNumberUsingNextInt(int min, int max) {
	Random random = new Random();
	return random.nextInt(max - min) + min;
    }

    public void moveAttack(){
	for (int i = 0; i < enemyAttackList.size(); i++) {
	    enemyAttackList.get(i).x -= 6;
	    if( enemyAttackList.get(i).x < 0){
		enemyAttackList.remove(enemyAttackList.get(i));
		break;
	    }
	}
    }

    public List<EnemyAttack> getEnemyAttackList() {
	return enemyAttackList;
    }
}
