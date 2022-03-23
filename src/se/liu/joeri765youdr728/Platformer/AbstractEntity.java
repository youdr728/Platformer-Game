package se.liu.joeri765youdr728.Platformer;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


public abstract class AbstractEntity implements Entity
{
    protected int x;
    protected int y;
    protected int typeNumber;

    protected final Map<Integer, EntityType> entityTypeMap = creatEntityTypeMap();
    protected int collisionX, collisionY, collisionWidth, collisionHeight;

    protected int height = 48;
    protected int width = 48;

    protected AbstractEntity(final int x, final int y, final int typeNumber, int collisionX, int collisionY, int collisionWidth,
			     int collisionHeight) {
	this.x = x;
	this.y = y;
	this.typeNumber = typeNumber;
	this.collisionX = collisionX;
	this.collisionY = collisionY;
	this.collisionWidth = collisionWidth;
	this.collisionHeight = collisionHeight;
    }

    public static Map<Integer, EntityType> creatEntityTypeMap(){
	Map<Integer, EntityType> entityTypeMap = new HashMap<>();
	entityTypeMap.put(1, EntityType.PLATFORM);
	entityTypeMap.put(2, EntityType.PLAYER);
	entityTypeMap.put(3, EntityType.OBSTACLE);
	entityTypeMap.put(4, EntityType.GOAL);
	entityTypeMap.put(5, EntityType.COINS);
	entityTypeMap.put(6, EntityType.POWER_UP_TIME);
	entityTypeMap.put(7, EntityType.POWER_UP_JUMP);
	entityTypeMap.put(8, EntityType.POWER_UP_SPEED);
	entityTypeMap.put(9, EntityType.ENEMY);
	entityTypeMap.put(10, EntityType.ENEMY_ATTACK);

	return entityTypeMap;
    }


    @Override public int getX() {
	return x;
    }

    @Override public int getY() {
	return y;
    }

    @Override public int getHeight() {
	return height;
    }

    @Override public int getWidth() {
	return width;
    }

    @Override public EntityType getEntityType() {
	return entityTypeMap.get(typeNumber);
    }

    @Override public int getCollisionX() {
	return getX() + collisionX;
    }

    @Override public int getCollisionY() {
	return getY() + collisionY;
    }

    @Override public int getCollisionWidth() {
	return collisionWidth;
    }

    @Override public int getCollisionHeight() {
	return collisionHeight;
    }

    @Override public Rectangle getRectangle() {return new Rectangle(getCollisionX(),
						getCollisionY(), getCollisionWidth(), getCollisionHeight());}
}
