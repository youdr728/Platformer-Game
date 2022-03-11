package se.liu.joeri765youdr728.Platformer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameWorld
{
    private int row = 12;
    private int col = 16;
    private int worldWidth = col * 48;
    private int worldHeight = row * 48;

    private int mapTileNum[][];
    private List<Entity> entityList;
    private Player player;
    private Goal goal;
    private int mapNumber = 1;


    public GameWorld() {
	this.mapTileNum = new int[row][col];
	this.entityList = new ArrayList<>();
	loadMapFromFile("Maps/map01");
	createEntityList();
    }

    public void loadMapFromFile(String mapfile){
	try {
	    InputStream is = getClass().getResourceAsStream(mapfile);
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));

	    for (int h = 0; h < row; h++) {
		String line = br.readLine();
		String numbers[] = line.split(" ");
		for (int w = 0; w < col; w++) {
		    mapTileNum[h][w] = Integer.parseInt(numbers[w]);
		}
	    }
	}catch(Exception e){
	}
    }
    public void createEntityList(){
	for (int h = 0; h < row; h++) {
	    for (int w = 0; w < col; w++) {
		if (mapTileNum[h][w] != 0){
		    int n = mapTileNum[h][w];
		    switch (n) {
			case 1:
			    entityList.add(new Platform(w * 48, h * 48, n, 0,0 ,48, 48));
			    break;
			case 2:
			    player = new Player(w * 48, h * 48, n,9, 0, 30, 48, 4, this);
			    break;
			case 3:
			    entityList.add(new Obstacle(w * 48, (h * 48)+ 10, n, 0, 0, 48, 48));
			    break;
			case 4:
			    entityList.add(new Goal(w * 48, h * 48, n, 24, 36, 6, 12, this));
			    break;
			case 5:
			    entityList.add(new Coin(w * 48, h * 48, n, 3, 24, 42,24, this));
			    break;
		    }

		}
	    }

	}
    }
    //public Player createPlayer(){
	//Player player = new Player(2,10, 2, 13, this);
	//return player;
   // }

    public void updateWorld(){

	if(!player.isJumping()){
	    player.moveDown();
	}
	else{
	    player.jump();
	}


    }

    public String getNextMap(){
	mapNumber = mapNumber + 1;
	String nextMap = "Maps/map0" + mapNumber;
	return nextMap;
    }

    public void applyCollision(Entity entity) {
	EntityType entityType = entity.getEntityType();

	switch(entityType) {
	    case PLATFORM:
		player.setPlatformCollision(true);
		break;

	    case COINS:
		entityList.remove(entity);
		break;

	    case GOAL:
		System.out.println("hey" + 1);
		this.mapTileNum = new int[row][col];
		this.entityList = new ArrayList<>();
		loadMapFromFile(this.getNextMap());
		this.createEntityList();
		player.respawnPlayer();
		break;

	    case OBSTACLE:
		player.respawnPlayer();
		break;
	}

    }








    public int getRow() {
	return row;
    }

    public int getCol() {
	return col;
    }

    public int[][] getMapTileNum() {
	return mapTileNum;
    }

    public List<Entity> getEntityList() {
	return entityList;
    }

    public Player getPlayer() {
	return player;
    }

    public int getWorldWidth() {
	return worldWidth;
    }

    public int getWorldHeigt() {
	return worldHeight;
    }
}