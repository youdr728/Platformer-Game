package se.liu.joeri765youdr728.Platformer;

import java.util.List;

public class Coin extends AbstractEntity
{

    private GameWorld world;

    public Coin(final int x, final int y, final int typeNumber,
                int collisionX, int collisionY, int collisionWidth, int collisionHeight, final GameWorld world) {
	super(x, y, typeNumber, collisionX, collisionY, collisionWidth, collisionHeight);
        this.world = world;

    }



    public void addToScore(){
	//adds to player score
    }




}
