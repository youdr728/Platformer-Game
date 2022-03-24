package se.liu.joeri765youdr728.Platformer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class GameWorld
{
    private static final int ROW = 20;
    private static final int COL = 20;
    private static final int WORLD_WIDTH = COL * 48;
    private static final int WORLD_HEIGHT = ROW * 48;
    private static final int TILE_SIZE = 48;

    private int[][] mapTileNum;
    private List<Entity> entityList;
    private Player player = null;
    private int mapNumber = 1;

    private int gameTime;
    private int gameTimeCounter = 0;

    private int boostTimeCounter = 0;

    private int deathCounter = 0, scoreTime = 0, coinCounter = 0;

    private HighScoreList highScoreList = HighScoreList.loadHighscoreList();

    private boolean gameWon = false;
    private boolean playerDead = false;
    private GamePanel panel;

    private Enemy enemy = null;



    public GameWorld(GamePanel panel) {
	this.mapTileNum = new int[ROW][COL];
	this.entityList = new ArrayList<>();
	this.gameTime = 120;
	this.panel = panel;
	loadMapFromFile("Maps/map0" + mapNumber);
	createEntityList();
	panel.playMusic(0);
    }



    public void loadMapFromFile(String mapfile){
	try {
	    InputStream is = getClass().getResourceAsStream(mapfile);
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));

	    for (int h = 0; h < ROW; h++) {
		String line = br.readLine();
		String[] numbers = line.split(" ");
		for (int w = 0; w < COL; w++) {
		    mapTileNum[h][w] = Integer.parseInt(numbers[w]);
		}
	    }
	}catch(Exception e){
	}
    }
    public void createEntityList(){
	for (int h = 0; h < ROW; h++) {
	    for (int w = 0; w < COL; w++) {
		if (mapTileNum[h][w] != 0){
		    int n = mapTileNum[h][w];
		    switch (n) {
			case 1:
			    entityList.add(new Platform(w * TILE_SIZE, h * TILE_SIZE, n, 0, 0 , TILE_SIZE, TILE_SIZE));
			    break;
			case 2:
			    player = new Player(w * TILE_SIZE, h * TILE_SIZE, n, 9, 3, 30, 45, this);
			    break;
			case 3:
			    entityList.add(new Obstacle(w * TILE_SIZE, h * TILE_SIZE, n, 3, 15, 42, 33));
			    break;
			case 4:
			    entityList.add(new Goal(w * TILE_SIZE, h * TILE_SIZE, n, 24, 36, 6, 12, this));
			    break;
			case 5:
			    entityList.add(new Coin(w * TILE_SIZE, h * TILE_SIZE, n, 3, 24, 42, 24, this));
			    break;
			case 6:
			    entityList.add(new TimeBoost(w * TILE_SIZE, h * TILE_SIZE, n, 9, 12, 30, 30));
			    break;
			case 7:
			    entityList.add(new TimeBoost(w * TILE_SIZE, h * TILE_SIZE, n, 12, 18, 24, 24));
			    break;
			case 8:
			    entityList.add(new JumpBoost(w * TILE_SIZE, h * TILE_SIZE, n, 12, 18, 24, 24));
			    break;
			case 9:
			    enemy = new Enemy(w * TILE_SIZE, h * TILE_SIZE, n, 0, 0, 48, 48, this);
			    entityList.add(enemy);
			    break;


		    }


		}
	    }

	}
    }

    public void updateWorld(){
	powerupTimer();
	timer();
	if(enemy != null){
	    updateEnemy();
	}
	if(!player.isJumping()){
	    player.moveDown();
	}
	else{
	    player.jump();
	}

	if (playerDead) {
	    deathCounter += 1;
	    playerDead = false;
	}

    }
    public void updateEnemy(){
	enemy.shootAttack();
	enemy.moveAttack();
    }
    public void timer(){
	gameTimeCounter += 1;
	if(gameTimeCounter == 60){
	    gameTimeCounter = 0;
	    gameTime -= 1;
	    scoreTime += 1;

	}
    }

    public void powerupTimer(){
	if (player.isOnJumpBoost() || player.isOnSpeedBoost()) {
	    boostTimeCounter += 1;
	    if(boostTimeCounter == 240) {
		player.speedBoostOff();
		player.jumpBoostOff();
		boostTimeCounter = 0;
	    }

	}


    }



    public String getNextMap(){
	mapNumber += 1;
	String nextMap = "Maps/map0" + mapNumber;
	return nextMap;
    }



    public void applyCollision(Entity entity) {
	EntityType entityType = entity.getEntityType();

	switch(entityType) {
	    case PLATFORM:
		player.setPlatformCollision(true);
		player.setPlatformY(entity.getY() - TILE_SIZE);
		break;

	    case COINS:
		entityList.remove(entity);
		panel.playSoundEffect(2);
		coinCounter += 1;
		break;

	    case GOAL:
		System.out.println(mapNumber);
		if(mapNumber == 4){
		    panel.stopMusic();
		    panel.playMusic(1);
		    this.mapTileNum = new int[ROW][COL];
		    this.entityList = new ArrayList<>();
		    loadMapFromFile(this.getNextMap());
		    gameTime = 120;
		    this.createEntityList();
		    player.respawnPlayer();
		    panel.playSoundEffect(1);
		}
		else if(mapNumber == 5){
		    this.gameWon = true;
		    highScoreList.addHighscore(this);
		}
		else{
		    System.out.println("Hello");
		    this.mapTileNum = new int[ROW][COL];
		    this.entityList = new ArrayList<>();
		    loadMapFromFile(this.getNextMap());
		    gameTime = 120;
		    this.createEntityList();
		    player.respawnPlayer();
		    panel.playSoundEffect(1);
		}
		break;

	    case OBSTACLE:
		player.respawnPlayer();
		panel.playSoundEffect(0);
		playerDead = true;
		break;

	    case POWER_UP_TIME:
		entityList.remove(entity);
		gameTime += TimeBoost.getTime(this);
		panel.playSoundEffect(4);
		break;

	    case POWER_UP_JUMP:
		entityList.remove(entity);
		player.jumpBoostOn();
		player.speedBoostOff();
		boostTimeCounter = 0;
		panel.playSoundEffect(3);
		break;

	    case POWER_UP_SPEED:
		entityList.remove(entity);
		player.speedBoostOn();
		player.jumpBoostOff();
		boostTimeCounter = 0;
		panel.playSoundEffect(3);
		break;

	    case ENEMY:
		player.respawnPlayer();
		break;

	    case ENEMY_ATTACK:
		panel.playSoundEffect(0);
		player.respawnPlayer();
		getEnemyAttack().remove(entity);
		break;
	}


    }
    public void playSound(int i){
	panel.playSoundEffect(i);
    }

    public void addToEntityList(Entity entity){
	entityList.add(entity);
    }

    public int getRow() {
	return ROW;
    }

    public int getCol() {
	return COL;
    }

    public Enemy getEnemy() {
	return enemy;
    }

    public List<EnemyAttack> getEnemyAttack(){
	return enemy.getEnemyAttackList();
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
	return WORLD_WIDTH;
    }

    public int getWorldHeight() {
	return WORLD_HEIGHT;
    }

    public int getGameTime() {
	return gameTime;
    }

    public void setGameTime(final int gameTime) {
	this.gameTime = gameTime;
    }

    public int getDeathCounter() {
	return deathCounter;
    }

    public int getScoreTime() {
	return scoreTime;
    }
    public int getCoinCounter() {
	return coinCounter;
    }
    public boolean isGameWon() {
	return gameWon;
    }
}