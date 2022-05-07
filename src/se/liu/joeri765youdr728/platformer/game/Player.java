package se.liu.joeri765youdr728.platformer.game;

import se.liu.joeri765youdr728.platformer.input.Keys;

import java.awt.*;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class Player extends AbstractEntity
{
    /**
     * Initialize player stats
     */
    private final int startX;
    private final int startY;

    private int speed;
    private int jumpSpeed;

    private static final int FALL_SPEED = 8;
    private static final int NORMAL_SPEED = 4;
    private static final int NORMAL_JUMP_SPEED = 16;
    private static final int BOOST_SPEED = 6;
    private static final int BOOST_JUMP_SPEED = 20;


    private World world;
    private boolean platformCollision;
    private boolean isJumping = false;
    private boolean canJump = true;
    private boolean isOnJumpBoost = false;
    private boolean isOnSpeedBoost = false;



    private int platformY;



    public Player(final int x, final int y, final EntityType entityType,
		  int collisionX, int collisionY, int collisionWidth, int collisionHeight, final World world) {
	super(x, y, entityType, collisionX, collisionY, collisionWidth, collisionHeight);
	this.world = world;
	this.startX = x;
	this.startY = y;
	this.speed = NORMAL_SPEED;
	this.jumpSpeed = NORMAL_JUMP_SPEED;
    }

    public void respawnPlayer(){
	this.x = startX;
	this.y = startY;

    }
    public void jump(){

	canJump = false;
	this.y -= jumpSpeed;
	tryCollision();
	if (platformCollision || this.y < 0 || jumpSpeed == 0) {
	    this.y += jumpSpeed;
	    if(isOnJumpBoost) {
		jumpSpeed = BOOST_JUMP_SPEED;
	    }
	    else{
		jumpSpeed = NORMAL_JUMP_SPEED;
	    }
	    setPlatformCollision(false);
	    setIsJumping(false);

	}else{
	    jumpSpeed -= 1;
	}



    }
    public void moveDown(){
	canJump = false;
	this.y += FALL_SPEED;
	tryCollision();
	if (platformCollision) {
	    this.y = platformY;
	    setPlatformCollision(false);
	    canJump = true;
	}
	if(this.y > world.getWorldHeight()){
	    respawnPlayer();
	}

    }
    public void movePlayer(Keys dir){
	if (dir.equals(Keys.RIGHT)){
	    this.x += speed;
	    tryCollision();
	    if (platformCollision || (this.x > world.getWorldWidth() - this.getWidth())) {
		this.x -= speed;
		setPlatformCollision(false);
	    }
	}
	else if (dir.equals(Keys.LEFT)){
	    this.x -= speed;
	    tryCollision();
	    if (platformCollision || this.x < 0) {
		this.x += speed;
		setPlatformCollision(false);
	    }
	}

    }

    public void tryCollision() {
	Rectangle playerRec = this.getRectangle();

	for (int i = 0; i<world.getEntities().size(); i++) {
	    Entity entity = world.getEntities().get(i);
	    Rectangle entityRec = entity.getRectangle();

	    if(playerRec.intersects(entityRec)) {
		world.applyCollision(entity);
	    }
	}
	if(world.getEnemy() != null){
	    for (int i = 0; i < world.getEnemyAttack().size(); i++) {
		EnemyAttack attack = world.getEnemyAttack().get(i);
		Rectangle attackRec = attack.getRectangle();

		if(playerRec.intersects(attackRec)){
		    world.applyCollision(attack);

		}
	    }
	}
    }

    public void setJumpBoostOn() {
	isOnJumpBoost = true;
	jumpSpeed = BOOST_JUMP_SPEED;
    }

    public void setJumpBoostOff() {
	isOnJumpBoost = false;
	jumpSpeed = NORMAL_JUMP_SPEED;
    }

    public void setSpeedBoostOn() {
	isOnSpeedBoost = true;
	speed = BOOST_SPEED;
    }

    public void setSpeedBoostOff() {
	isOnSpeedBoost = false;
	speed = NORMAL_SPEED;
    }

    public int getStartX() {
	return startX;
    }

    public int getStartY() {
	return startY;
    }

    public void setPlatformCollision(boolean b) {this.platformCollision = b;}

    public void setIsJumping(boolean jumping) {
	this.isJumping = jumping;
    }

    public boolean isJumping() {
	return isJumping;
    }

    public boolean canJump() {
	return canJump;
    }

    public void setPlatformY(final int platformY) {
	this.platformY = platformY;
    }

    public boolean isOnJumpBoost() {
	return isOnJumpBoost;
    }

    public boolean isOnSpeedBoost() {
	return isOnSpeedBoost;
    }
}

