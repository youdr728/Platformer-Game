package se.liu.joeri765youdr728.platformer.game;

import se.liu.joeri765youdr728.platformer.highscore.HighScoreList;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 *
 * A class that contains all the functions and variable related to updating and creating what goes on in the background
 */
public class World
{
    private static final int ROW = 20;
    private static final int COL = 20;
    private static final int WORLD_WIDTH = COL * 48;
    private static final int WORLD_HEIGHT = ROW * 48;
    private static final int TILE_SIZE = 48;

    private static final int MAP_5 = 5;

    private int[][] mapTileNum;
    private List<Entity> entities;
    private Player player = null;
    private int mapNumber = 1;

    private int gameTime;
    private int gameTimeCounter = 0;
    private int boostTimeCounter = 0;
    private int deathCounter = 0, scoreTime = 0, coinCounter = 0;

    private boolean gameWon = false;
    private boolean playerDead = false;

    private Enemy enemy = null;

    private final static String SEPARATOR = File.separator;


    public World() {
	this.mapTileNum = new int[ROW][COL];
	this.entities = new ArrayList<>();
	this.gameTime = 120;
	loadMapFromFile("maps" + SEPARATOR + "map0" + mapNumber);
	createEntityList();
    }

    public void loadMapFromFile(String mapFile){
	Logger logger = Logger.getLogger(World.class.getName() );
	SimpleFormatter formatter = new SimpleFormatter();
	FileHandler fileHandler = null;

	try (InputStream is = getClass().getResourceAsStream(mapFile)) {
	    fileHandler = new FileHandler("LogFile.log", 0, 1, true);
	    logger.addHandler(fileHandler);
	    fileHandler.setFormatter(formatter);

	    if(is != null){
		BufferedReader br = new BufferedReader(new InputStreamReader(is));

		for (int h = 0; h < ROW; h++) {
		    String line = br.readLine();
		    String[] numbers = line.split(" ");
		    for (int w = 0; w < COL; w++) {
			mapTileNum[h][w] = Integer.parseInt(numbers[w]);
		    }
		}
	    }


	}catch(IOException e){
	    //kod analys varning: Du skrev i ett mail att om vi inte lyckades
	    //komma på en bra lösning till dom här problem att vi skulle lämmna en komentar då
	    logger.info(e.getMessage());
	    e.printStackTrace();
	}
	logger.removeHandler(fileHandler);
	if(fileHandler != null){
	    fileHandler.close();
	}

    }
    public void createEntityList(){
	//Creates a list off all the entitys that we load in from loadMapFromFile funtction
	for (int h = 0; h < ROW; h++) {
	    for (int w = 0; w < COL; w++) {
		if (mapTileNum[h][w] != 0){
		    int n = mapTileNum[h][w];

		    EntityType entityType = EntityType.getEntityType(n);
		    int xPosition = w * TILE_SIZE;
		    int yPosition = h * TILE_SIZE;

		    switch (entityType) {
			case PLATFORM:
			    entities.add(new Platform(xPosition, yPosition, EntityType.PLATFORM, 0, 0 , TILE_SIZE, TILE_SIZE));
			    break;
			case PLAYER:
			    player = new Player(xPosition, yPosition, EntityType.PLAYER, 9, 3, 30, 45, this);
			    break;
			case OBSTACLE:
			    entities.add(new Obstacle(xPosition, yPosition, EntityType.OBSTACLE, 3, 15, 42, 33));
			    break;
			case GOAL:
			    entities.add(new Goal(xPosition, yPosition, EntityType.GOAL, 24, 36, 6, 12));
			    break;
			case COINS:
			    entities.add(new Coin(xPosition, yPosition, EntityType.COINS, 3, 24, 42, 24));
			    break;
			case POWER_UP_TIME:
			    entities.add(new TimeBoost(xPosition, yPosition, EntityType.POWER_UP_TIME, 9, 12, 30, 30));
			    break;
			case POWER_UP_SPEED:
			    entities.add(new SpeedBoost(xPosition, yPosition, EntityType.POWER_UP_SPEED, 12, 18, 24, 24));
			    break;
			case POWER_UP_JUMP:
			    entities.add(new JumpBoost(xPosition, yPosition, EntityType.POWER_UP_JUMP, 12, 18, 24, 24));
			    break;
			case ENEMY:
			    enemy = new Enemy(xPosition, yPosition, EntityType.ENEMY, 0, 0, 48, 48);
			    entities.add(enemy);
			    break;

		    }
		}
	    }
	}
    }

    public void updateWorld(){
	startPowerupTimer();
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
	final int oneSecond = 60;
	gameTimeCounter += 1;

	if(gameTimeCounter == oneSecond){
	    gameTimeCounter = 0;
	    gameTime -= 1;
	    scoreTime += 1;

	}
    }

    public void startPowerupTimer(){
	if (player.isOnJumpBoost() || player.isOnSpeedBoost()) {
	    final int fourSeconds = 240;
	    boostTimeCounter += 1;
	    if(boostTimeCounter == fourSeconds) {
		player.setSpeedBoostOff();
		player.setJumpBoostOff();
		boostTimeCounter = 0;
	    }
	}
    }


    public String getNextMap(){
	mapNumber += 1;
	String nextMap = "maps" + SEPARATOR + "map0" + mapNumber;
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
		entities.remove(entity);
		//kod analys varning: Inte mycket vi kan göra för att ta bort den här varningen då vi behöver remova entity i flera cases.
		//Sen kan vi inte heller förenkal statmentet då det är så enkelt det kan vara.
		coinCounter += 1;
		break;

	    case GOAL:
		if(mapNumber == MAP_5){
		    HighScoreList highScoreList = HighScoreList.loadHighscoreList();
		    this.gameWon = true;
		    highScoreList.addHighscore(this);
		}
		if (mapNumber != MAP_5) {
		    this.mapTileNum = new int[ROW][COL];
		    this.entities = new ArrayList<>();
		    loadMapFromFile(this.getNextMap());
		    gameTime = 120;
		    this.createEntityList();
		    player.respawnPlayer();

		}
		break;

	    case OBSTACLE:
		player.respawnPlayer();
		playerDead = true;
		break;

	    case POWER_UP_TIME:
		entities.remove(entity);
		gameTime += TimeBoost.getTimeBoost();
		break;

	    case POWER_UP_JUMP:
		entities.remove(entity);
		player.setJumpBoostOn();
		player.setSpeedBoostOff();
		boostTimeCounter = 0;
		break;

	    case POWER_UP_SPEED:
		entities.remove(entity);
		player.setSpeedBoostOn();
		player.setJumpBoostOff();
		boostTimeCounter = 0;
		break;

	    case ENEMY:
		player.respawnPlayer();
		break;

	    case ENEMY_ATTACK:
		player.respawnPlayer();
		getEnemyAttack().remove(entity);
		break;
	}


    }

    public Enemy getEnemy() {
	return enemy;
    }

    public List<EnemyAttack> getEnemyAttack(){
	return enemy.getEnemyAttacks();
    }

    public List<Entity> getEntities() {
	return entities;
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