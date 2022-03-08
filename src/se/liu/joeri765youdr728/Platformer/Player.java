package se.liu.joeri765youdr728.Platformer;

import java.awt.*;

public class Player extends AbstractEntity
{
    private int speed;
    private GameWorld world;
    private Entity collidedEntity;
    private boolean platformCollision;

    public Player(int x,int y, final int typeNumber, final int speed, final GameWorld world) {
	super(x, y, typeNumber);
	this.world = world;
	this.speed = speed;


    }

    public void respawnPlayer(){

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
	else if (dir.equals(Direction.UP)){
	    this.y -= speed;
	    tryCollision();
	    if (platformCollision || this.y < 0) {
		this.y += speed;
		setPlatformCollision(false);
	    }
	}
	else if (dir.equals(Direction.DOWN)){
	    this.y += speed;
	    tryCollision();
	    if (platformCollision || this.y > (world.getWorldHeigt()-48)) {
		this.y -= speed;
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
}

