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
			    entityList.add(new Platform(w * 48, h * 48, n));
			    break;
			case 2:
			    player = new Player(w * 48, h * 48, n,3, this);
			    break;
			case 3:
			    entityList.add(new Obstacle(w * 48, h * 48, n));
			    break;
			case 4:
			    entityList.add(new Goal(w * 48, h * 48, n, this));
			    break;
			case 5:
			    entityList.add(new Goal(w * 48, h * 48, n, this));
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



    public void applyCollision(Entity entity) {
	EntityType entityType = entity.getEntityType();

	switch(entityType) {
	    case PLATFORM:
		player.setPlatformCollision(true);
		break;

	    case COINS:
		break;

	    case GOAL:
		break;

	    case OBSTACLE:
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