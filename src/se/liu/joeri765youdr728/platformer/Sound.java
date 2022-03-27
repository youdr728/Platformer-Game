package se.liu.joeri765youdr728.platformer;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.EnumMap;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class Sound
{


    private Clip clipSound = null;
    private Clip clipMusic = null;

    protected final EnumMap<MusicType, URL> soundMap = createSoundURLMap();
    protected final EnumMap<MusicType, URL> musicMap = createMusicURLMap();

    private final static String SEPARATOR = File.separator;


    public static EnumMap<MusicType, URL> createSoundURLMap(){
	URL playerDeath = null, door = null, point = null, speedJumpPowerup = null, timePowerup = null, jump = null, fireball = null;

	Logger logger = Logger.getLogger(Sound.class.getName() );
	SimpleFormatter formatter = new SimpleFormatter();
	FileHandler fileHandler = null;
	try {
	    fileHandler = new FileHandler("LogFile.log", 0, 1, true);
	    logger.addHandler(fileHandler);
	    fileHandler.setFormatter(formatter);

	    playerDeath = ClassLoader.getSystemResource("audio" + SEPARATOR + "player_death.wav");
	    door = ClassLoader.getSystemResource("audio" + SEPARATOR +"door.wav");
	    point = ClassLoader.getSystemResource("audio" + SEPARATOR +"point.wav");
	    speedJumpPowerup = ClassLoader.getSystemResource("audio" + SEPARATOR +"speed_jump_powerup.wav");
	    timePowerup = ClassLoader.getSystemResource("audio" + SEPARATOR +"time_powerup.wav");
	    jump = ClassLoader.getSystemResource("audio" + SEPARATOR +"jump8bits.wav");
	    fireball = ClassLoader.getSystemResource("audio" + SEPARATOR +"fireball.wav");

	} catch (IOException e) {
	    logger.info(e.getMessage());
	    e.printStackTrace();
	}
	logger.removeHandler(fileHandler);
	fileHandler.close();

	EnumMap<MusicType, URL> soundMap = new EnumMap<>(MusicType.class);
	soundMap.put(MusicType.PLAYER_DEATH, playerDeath);
	soundMap.put(MusicType.DOOR, door);
	soundMap.put(MusicType.POINT, point);
	soundMap.put(MusicType.SPEED_JUMP_POWERUP, speedJumpPowerup);
	soundMap.put(MusicType.TIME_POWERUP, timePowerup);
	soundMap.put(MusicType.JUMP, jump);
	soundMap.put(MusicType.FIREBALL, fireball);
	return soundMap;
    }
    public static EnumMap<MusicType, URL> createMusicURLMap(){
	URL gameBackground = null, bossBackground = null, menuBackground = null, scoreBackground = null;

	Logger logger = Logger.getLogger(Sound.class.getName() );
	SimpleFormatter formatter = new SimpleFormatter();
	FileHandler fileHandler = null;
	    try {
		fileHandler = new FileHandler("LogFile.log", 0, 1, true);
		logger.addHandler(fileHandler);
		fileHandler.setFormatter(formatter);

		gameBackground = ClassLoader.getSystemResource("audio" + SEPARATOR +"game_background_music.wav");
		bossBackground = ClassLoader.getSystemResource("audio" + SEPARATOR +"boss_background_music.wav");
		menuBackground = ClassLoader.getSystemResource("audio" + SEPARATOR +"menu_background_music.wav");
		scoreBackground = ClassLoader.getSystemResource("audio" + SEPARATOR +"score_background_music.wav");


	    } catch (IOException e) {
		logger.info(e.getMessage());
		e.printStackTrace();


	    logger.removeHandler(fileHandler);
	    fileHandler.close();
	}



	EnumMap<MusicType, URL> musicMap = new EnumMap<>(MusicType.class);
	musicMap.put(MusicType.GAME_BACKGROUND, gameBackground);
	musicMap.put(MusicType.BOSS_BACKGROUND, bossBackground);
	musicMap.put(MusicType.MENU_BACKGROUND, menuBackground);
	musicMap.put(MusicType.SCORE_BACKGROUND, scoreBackground);

	return musicMap;
    }

    public void setFileSound(MusicType musicType, String type){
	Logger logger = Logger.getLogger(Sound.class.getName() );
	SimpleFormatter formatter = new SimpleFormatter();
	FileHandler fileHandler = null;
	try{
	    fileHandler = new FileHandler("LogFile.log", 0, 1, true);
	    logger.addHandler(fileHandler);
	    fileHandler.setFormatter(formatter);

	    if(type.equals("sound")){
		AudioInputStream ais = AudioSystem.getAudioInputStream(soundMap.get(musicType));
		clipSound = AudioSystem.getClip();
		clipSound.open(ais);
	    }
	    else{
		AudioInputStream ais = AudioSystem.getAudioInputStream(musicMap.get(musicType));

		clipMusic = AudioSystem.getClip();
		clipMusic.open(ais);
	    }


	} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
	    logger.info(e.getMessage());
	    e.printStackTrace();
	    System.exit(1);
	}
	logger.removeHandler(fileHandler);
	fileHandler.close();
    }


    public void playSound(){
	clipSound.start();
    }

    public void loop(){
	clipMusic.start();
	clipMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
	clipMusic.stop();
    }
}
