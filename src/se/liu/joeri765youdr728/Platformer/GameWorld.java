package se.liu.joeri765youdr728.Platformer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class GameWorld
{
    int row = 16;
    int col = 12;
    int mapTileNum[][];

    public GameWorld() {
	this.mapTileNum = new int[col][row];
    }

    public void loadMapFromFile(String mapfile){
	try {
	    InputStream is = getClass().getResourceAsStream(mapfile);
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));

	    for (int c = 0; c < col; c++) {
		String line = br.readLine();
		String numbers[] = line.split(" ");
		for (int r = 0; r < row; r++) {
		    mapTileNum[c][r] = Integer.parseInt(numbers[r]);
		}
	    }
	}catch(Exception e){

	}
	System.out.println(Arrays.deepToString(mapTileNum));
    }
}