package se.liu.joeri765youdr728.Platformer;

public class Player extends AbstractEntity
{
    private int speed;
    private GameWorld world;
    private EntityType collidedEntity;

    public Player(final int x, final int y, final int typeNumber, final int speed, final GameWorld world) {
	super(x, y, typeNumber);
	this.world = world;
	this.speed = speed;
    }

    private void respawnPlayer(){

    }
    private void movePlayer(Direction dir){
	if (dir.equals(Direction.RIGHT)){
	    this.x += 5;
	    // If collision put -5 on x

	}
	else if (dir.equals(Direction.LEFT)){
	    this.x -= 5;
	    // If collision put +5 on x
	}
    }

    public void tryCollision() {
	for (Entity entity : world.getEntityList()) {
	    if(this.x == entity.getX() && this.y == entity.getY() && entity.getEntityType() != EntityType.PLAYER) {
		collidedEntity = entity.getEntityType();
		System.out.println("hey");
	    }
	}
    }
}
