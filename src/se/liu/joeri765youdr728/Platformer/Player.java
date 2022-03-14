package se.liu.joeri765youdr728.Platformer;

import java.awt.*;

public class Player extends AbstractEntity
{
    private int speed;
    private int jumpSpeed = 16;
    private int fallspeed = 8;


    private GameWorld world;
    private Entity collidedEntity;
    private boolean platformCollision;
    private boolean isJumping = false;
    private boolean canJump = true;

    private final int startX;
    private final int startY;

    private int platformY;



    public Player(final int x, final int y, final int typeNumber,
		  int collisionX, int collisionY, int collisionWidth, int collisionHeight,
		  final int speed, final GameWorld world) {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
	this.world = world;
	this.speed = speed;
	this.startX = x;
	this.startY = y;


    }

    public void respawnPlayer(){
	this.x = startX;
	this.y = startY;

    }
    public void jump(){
	setJumping(true);
	canJump = false;
	this.y -= jumpSpeed;
	tryCollision();
	if (platformCollision || this.y < 0 || jumpSpeed == 0) {
	    this.y += jumpSpeed;
	    jumpSpeed = 16;
	    setPlatformCollision(false);
	    setJumping(false);
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
	if(this.y > world.getWorldHeigt()){
	    respawnPlayer();
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

    public int getStartX() {
	return startX;
    }

    public int getStartY() {
	return startY;
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

    public void setPlatformY(final int platformY) {
	this.platformY = platformY;
    }
}

