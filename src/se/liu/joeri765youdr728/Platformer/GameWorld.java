package se.liu.joeri765youdr728.Platformer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GameWorld
{
    private int row = 12;
    private int col = 16;
    private int mapTileNum[][];


    public GameWorld() {
	this.mapTileNum = new int[row][col];
	loadMapFromFile("map01");
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
	System.out.println(Arrays.deepToString(mapTileNum));
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
}