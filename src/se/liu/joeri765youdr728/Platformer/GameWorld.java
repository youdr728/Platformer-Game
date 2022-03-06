package se.liu.joeri765youdr728.Platformer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameWorld
{
    private int row = 12;
    private int col = 16;
    private int mapTileNum[][];
    private List<Entity> entityList;
    private Platform platform;
    private Player player;

    private List<WorldListener> listeners;


    public GameWorld() {
	this.mapTileNum = new int[row][col];
	this.entityList = new ArrayList<>();
	this.listeners = new ArrayList<>();
	//this.player = createPlayer();
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
			    platform = new Platform(w * 48, h * 48, n);
			    entityList.add(platform);
			    break;
			case 2:

			    player = new Player(w * 48, h * 48, n,20, this);

		    }

		}
	    }

	}
    }
    //public Player createPlayer(){
	//Player player = new Player(2,10, 2, 13, this);
	//return player;
   // }

    public void addWorldListener(WorldListener wl){
	listeners.add(wl);
    }

    public void notifyListeners(){
	for (WorldListener elem: listeners) {
	    elem.worldChanged();
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
}