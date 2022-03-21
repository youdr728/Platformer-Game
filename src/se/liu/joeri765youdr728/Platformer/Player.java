package se.liu.joeri765youdr728.Platformer;

import java.awt.*;

public class Player extends AbstractEntity
{
    private int speed;
    private int jumpSpeed;
    private int fallspeed = 8;

    private int normalSpeed = 4;
    private int normalJumpSpeed = 16;

    private int boostSpeed = 7;
    private int boostJumpSpeed = 20;


    private GameWorld world;
    private boolean platformCollision;
    private boolean isJumping = false;
    private boolean canJump = true;
    private boolean isOnJumpBoost = false;
    private boolean isOnSpeedBoost = false;

    private final int startX;
    private final int startY;

    private int platformY;



    public Player(final int x, final int y, final int typeNumber,
		  int collisionX, int collisionY, int collisionWidth, int collisionHeight, final GameWorld world) {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
	this.world = world;
	this.startX = x;
	this.startY = y;
	this.speed = this.normalSpeed;
	this.jumpSpeed = this.normalJumpSpeed;
    }

    public void respawnPlayer(){
	//System.out.println(startX);
	//System.out.println(startY);
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
		jumpSpeed = boostJumpSpeed;
	    }
	    else{
		jumpSpeed = normalJumpSpeed;
	    }
	    setPlatformCollision(false);
	    setIsJumping(false);

	}else{
	    jumpSpeed -= 1;
	}



    }
    public void moveDown(){
	canJump = false;
	this.y += fallspeed;
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
    public void movePlayer(Direction dir){

	if (dir.equals(Direction.RIGHT)){
	    this.x += speed;
	    tryCollision();
	    if (platformCollision || (this.x > world.getWorldWidth() - this.getWidth())) {
		this.x -= speed;
		setPlatformCollision(false);
	    }

	}
	else if (dir.equals(Direction.LEFT)){
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

	for (int i=0; i<world.getEntityList().size(); i++) {
	    Entity entity = world.getEntityList().get(i);
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

    public void jumpBoostOn() {
	isOnJumpBoost = true;
	jumpSpeed = boostJumpSpeed;
    }

    public void jumpBoostOff() {
	isOnJumpBoost = false;
	jumpSpeed = normalJumpSpeed;
    }

    public void speedBoostOn() {
	isOnSpeedBoost = true;
	speed = boostSpeed;
    }

    public void speedBoostOff() {
	isOnSpeedBoost = false;
	speed = normalSpeed;
    }

    public int getSpeed() {
	return speed;
    }

    public int getStartX() {
	return startX;
    }

    public int getStartY() {
	return startY;
    }

    public void setPlatformCollision(boolean bool) {this.platformCollision = bool;}

    public void setIsJumping(boolean jumping) {
	this.isJumping = jumping;
    }

    public boolean isJumping() {
	return isJumping;
    }

    public boolean isPlatformCollision() {
	return platformCollision;
    }

    public boolean CanJump() {
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

