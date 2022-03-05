package se.liu.joeri765youdr728.Platformer;

public class Player extends AbstractEntity
{
    private int speed;
    private GameWorld world;
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
}
