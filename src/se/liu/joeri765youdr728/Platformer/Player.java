package se.liu.joeri765youdr728.Platformer;

import java.awt.*;

public class Player extends AbstractEntity
{
    private int speed;
    private GameWorld world;
    private Entity collidedEntity;
    private boolean platformCollision;
    private boolean isJumping;
    private int jumpCounter = 0;
    private boolean canJump = true;

    public Player(int x,int y, final int typeNumber, final int speed, final GameWorld world) {
	super(x, y, typeNumber);
	this.world = world;
	this.speed = speed;
	this.isJumping = false;


    }

    public void respawnPlayer(){

    }
    public void jump(){
	isJumping = true;
	canJump = false;
	this.jumpCounter += 2;
	this.y -= 7;
	tryCollision();
	if (platformCollision || this.y < 0) {
	    this.y += 7;
	    jumpCounter = 31;
	    setPlatformCollision(false);
	    setJumping(false);

	}
	else if (jumpCounter > 30) {
	    setJumping(false);

	}

    }
    public void moveDown(){
	this.y += 5;
	tryCollision();
	if (platformCollision || this.y > (world.getWorldHeigt()-48)) {
	    this.y -= 5;
	    setPlatformCollision(false);
	    jumpCounter = 0;
	    setCanJump(true);
	}
    }
    public void movePlayer(Direction dir){

	if (dir.equals(Direction.RIGHT)){
	    this.x += speed;
	    tryCollision();
	    if (platformCollision || (this.x > world.getWorldWidth()- 48)) {
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
		collidedEntity = entity;
		world.applyCollision(entity);
	    }
	}
    }

    public int getSpeed() {
	return speed;
    }

    public void setPlatformCollision(boolean bool) {this.platformCollision = bool;}

    public void setJumping(boolean jumping) {
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

    public void setCanJump(boolean canJump) {
	this.canJump = canJump;
    }
}

