package se.liu.joeri765youdr728.Platformer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Test
{
    int row = 3;
    int col = 4;
    int mapTileNum[][];

    public Test() {
	this.mapTileNum = new int[col][row];
    }


    public void loadMap(){
	try {
	    InputStream is = getClass().getResourceAsStream("map01");
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

    public static void main(String[] args) {
	Test test = new Test();
	test.loadMap();

    }
}
