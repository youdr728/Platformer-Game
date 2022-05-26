package se.liu.joeri765youdr728.platformer.game;

import java.awt.*;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * Abstract class for what all entity in the game should contain such as its position, and the functions related to it
 */
public abstract class AbstractEntity implements Entity
{
    protected int x;
    protected int y;
    protected EntityType entityType;

    protected int collisionX, collisionY, collisionWidth, collisionHeight;

    protected final static int HEIGHT = 48;
    protected final static int WIDTH = 48;

    protected AbstractEntity(final int x, final int y, final EntityType entityType, int collisionX, int collisionY, int collisionWidth,
			     int collisionHeight) {
	this.x = x;
	this.y = y;
	this.entityType = entityType;
	this.collisionX = collisionX;
	this.collisionY = collisionY;
	this.collisionWidth = collisionWidth;
	this.collisionHeight = collisionHeight;
    }


    @Override public int getX() {
	return x;
    }

    @Override public int getY() {
	return y;
    }

    @Override public int getHeight() {
	return HEIGHT;
    }

    @Override public int getWidth() {
	return WIDTH;
    }

    @Override public EntityType getEntityType() {
	return entityType;
    }

    @Override public Rectangle getRectangle() {return new Rectangle(getCollisionX(),
								    getCollisionY(), getCollisionWidth(), getCollisionHeight());}

    public int getCollisionX() {
	return getX() + collisionX;
    }

    public int getCollisionY() {
	return getY() + collisionY;
    }

    public int getCollisionWidth() {
	return collisionWidth;
    }

    public int getCollisionHeight() {
	return collisionHeight;
    }

}
