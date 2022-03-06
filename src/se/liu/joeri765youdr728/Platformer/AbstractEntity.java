package se.liu.joeri765youdr728.Platformer;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractEntity implements Entity
{
    protected int x;
    protected int y;
    protected int typeNumber;

    protected final Map<Integer, EntityType> entityTypeMap = creatEntityTypeMap();
    protected final Map<Integer, Integer> sizeMap = creatSizeMap();

    public AbstractEntity(final int x, final int y, final int typeNumber) {
	this.x = x;
	this.y = y;
	this.typeNumber = typeNumber;
    }

    public static Map<Integer, EntityType> creatEntityTypeMap(){
	Map<Integer, EntityType> entityTypeMap = new HashMap<>();
	entityTypeMap.put(1, EntityType.PLATFORM);
	entityTypeMap.put(2, EntityType.PLAYER);
	entityTypeMap.put(3, EntityType.OBSTACLE);
	entityTypeMap.put(4, EntityType.GOAL);
	entityTypeMap.put(5, EntityType.COINS);
	entityTypeMap.put(6, EntityType.POWER_UP1);
	entityTypeMap.put(7, EntityType.POWER_UP2);
	entityTypeMap.put(8, EntityType.POWER_UP3);

	return entityTypeMap;
    }
    public static Map<Integer, Integer> creatSizeMap(){
	Map<Integer, Integer> SizeMap = new HashMap<>();
	SizeMap.put(1, 48);
	SizeMap.put(2, 48);
	SizeMap.put(3, 48);
	SizeMap.put(4, 48);
	SizeMap.put(5, 48);
	SizeMap.put(6, 48);
	SizeMap.put(7, 48);

	return SizeMap;
    }

    @Override public int getX() {
	return x;
    }

    @Override public int getY() {
	return y;
    }

    @Override public int getHeight() {
	return sizeMap.get(typeNumber);
    }

    @Override public int getWidth() {
	return sizeMap.get(typeNumber);
    }

    @Override public EntityType getEntityType() {
	return entityTypeMap.get(typeNumber);
    }
}
