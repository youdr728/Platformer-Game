package se.liu.joeri765youdr728.Platformer;

public class Player extends AbstractEntity
{
    private int speed;
    private GameWorld world;
    private EntityType collidedEntity;

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
	    // If collision put -5 on x

	}
	else if (dir.equals(Direction.LEFT)){
	    this.x -= speed;
	    // If collision put +5 on x
	}
	else if (dir.equals(Direction.UP)){
	    this.y -= speed;
	    // If collision put +5 on x
	}
	else if (dir.equals(Direction.DOWN)){
	    this.y += speed;
	    // If collision put +5 on x
	}
	tryCollision();

    }

    public void tryCollision() {
	for (Entity entity : world.getEntityList()) {

	    if(this.x == entity.getX() && this.y == entity.getY() ) {
		collidedEntity = entity.getEntityType();
		System.out.println("hey");
	    }
	}
    }

    public int getSpeed() {
	return speed;
    }
}
