package se.liu.joeri765youdr728.platformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
    private List<Entity> entities;
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

    private final static String SEPARATOR = File.separator;
    private static final Logger LOGGER = Logger.getLogger(GameWorld.class.getName() );
    private static SimpleFormatter formatter = new SimpleFormatter();
    private static FileHandler fh;

    static {
	try {
	    fh = new FileHandler("LogFile.log", 0, 1, true);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public GameWorld(GamePanel panel) {
	this.mapTileNum = new int[ROW][COL];
	this.entities = new ArrayList<>();
	this.gameTime = 120;
	this.panel = panel;
	loadMapFromFile("Maps" + SEPARATOR + "map0" + mapNumber);
	createEntityList();
	panel.playMusic(0);
    }



    public void loadMapFromFile(String mapFile){
	try (InputStream is = getClass().getResourceAsStream(mapFile)) {
	    LOGGER.addHandler(fh);
	    fh.setFormatter(formatter);

	    BufferedReader br = new BufferedReader(new InputStreamReader(is));

	    for (int h = 0; h < ROW; h++) {
		String line = br.readLine();
		String[] numbers = line.split(" ");
		for (int w = 0; w < COL; w++) {
		    mapTileNum[h][w] = Integer.parseInt(numbers[w]);
		}
	    }
	}catch(IOException e){
	    LOGGER.log(Level.FINE, e.getMessage());
	    e.printStackTrace();
	}
    }
    public void createEntityList(){
	for (int h = 0; h < ROW; h++) {
	    for (int w = 0; w < COL; w++) {
		if (mapTileNum[h][w] != 0){
		    int n = mapTileNum[h][w];
		    switch (n) {
			case 1:
			    entities.add(new Platform(w * TILE_SIZE, h * TILE_SIZE, n, 0, 0 , TILE_SIZE, TILE_SIZE));
			    break;
			case 2:
			    player = new Player(w * TILE_SIZE, h * TILE_SIZE, n, 9, 3, 30, 45, this);
			    break;
			case 3:
			    entities.add(new Obstacle(w * TILE_SIZE, h * TILE_SIZE, n, 3, 15, 42, 33));
			    break;
			case 4:
			    entities.add(new Goal(w * TILE_SIZE, h * TILE_SIZE, n, 24, 36, 6, 12, this));
			    break;
			case 5:
			    entities.add(new Coin(w * TILE_SIZE, h * TILE_SIZE, n, 3, 24, 42, 24));
			    break;
			case 6:
			    entities.add(new TimeBoost(w * TILE_SIZE, h * TILE_SIZE, n, 9, 12, 30, 30));
			    break;
			case 7:
			    entities.add(new TimeBoost(w * TILE_SIZE, h * TILE_SIZE, n, 12, 18, 24, 24));
			    break;
			case 8:
			    entities.add(new JumpBoost(w * TILE_SIZE, h * TILE_SIZE, n, 12, 18, 24, 24));
			    break;
			case 9:
			    enemy = new Enemy(w * TILE_SIZE, h * TILE_SIZE, n, 0, 0, 48, 48, this);
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
	String nextMap = "Maps" + SEPARATOR + "map0" + mapNumber;
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
		panel.playSoundEffect(2);
		coinCounter += 1;
		break;

	    case GOAL:
		if(mapNumber == 4){
		    panel.stopMusic();
		    panel.playMusic(1);
		}
		else if(mapNumber == 5){
		    this.gameWon = true;
		    highScoreList.addHighscore(this);
		}

		if (mapNumber != 5) {
		    this.mapTileNum = new int[ROW][COL];
		    this.entities = new ArrayList<>();
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
		entities.remove(entity);
		gameTime += TimeBoost.getTime(this);
		panel.playSoundEffect(4);
		break;

	    case POWER_UP_JUMP:
		entities.remove(entity);
		player.setJumpBoostOn();
		player.setSpeedBoostOff();
		boostTimeCounter = 0;
		panel.playSoundEffect(3);
		break;

	    case POWER_UP_SPEED:
		entities.remove(entity);
		player.setSpeedBoostOn();
		player.setJumpBoostOff();
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