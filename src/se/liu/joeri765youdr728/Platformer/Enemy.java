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

    public Enemy(final int x, final int y, final int typeNumber, final int collisionX, final int collisionY, final int collisionWidth,
		 final int collisionHeight, final GameWorld world) {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
	this.world = world;
	this.enemyAttackList = new ArrayList<>();
	this.randomNumber = getRandomNumberUsingNextInt(20,45);
    }

    public void shootAttack(){

	if(attackCounter == randomNumber){
	    randomNumber = getRandomNumberUsingNextInt(30,45);
	    EnemyAttack attack = new EnemyAttack(x, y, 10, 12, 12, 27, 27);
	    enemyAttackList.add(attack);
	    attackCounter = 0;
	}else {
	    attackCounter += 1;
	}

    }
    public int getRandomNumberUsingNextInt(int min, int max) {
	Random random = new Random();
	return random.nextInt(max - min) + min;
    }

    public void moveAttack(){
	for (EnemyAttack attack: enemyAttackList) {
		attack.x -= 6;
		if(attack.x < 0){
		    enemyAttackList.remove(attack);
		    break;
		}
	}
    }

    public List<EnemyAttack> getEnemyAttackList() {
	return enemyAttackList;
    }
}
