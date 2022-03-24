package se.liu.joeri765youdr728.Platformer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
public class Sound
{


    private Clip clipSound = null;
    private Clip clipMusic = null;
    private URL[] soundURL = new URL[10];
    private URL[] musicURL = new URL[10];

    private final static String SEPARATOR = File.separator;

    private static final Logger LOGGER = Logger.getLogger(Sound.class.getName() );
    private static SimpleFormatter formatter = new SimpleFormatter();
    private static FileHandler fh;

    static {
	try {
	    fh = new FileHandler("LogFile.log", 0, 1, true);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public Sound() {
	soundURL[0] = ClassLoader.getSystemResource("audio" + SEPARATOR + "player_death.wav");
	soundURL[1] = ClassLoader.getSystemResource("audio" + SEPARATOR +"door.wav");
	soundURL[2] = ClassLoader.getSystemResource("audio" + SEPARATOR +"point.wav");
	soundURL[3] = ClassLoader.getSystemResource("audio" + SEPARATOR +"speed_jump_powerup.wav");
	soundURL[4] = ClassLoader.getSystemResource("audio" + SEPARATOR +"time_powerup.wav");
	soundURL[5] = ClassLoader.getSystemResource("audio" + SEPARATOR +"jump8bits.wav");
	soundURL[6] = ClassLoader.getSystemResource("audio" + SEPARATOR +"fireball.wav");
	soundURL[7] = ClassLoader.getSystemResource("audio" + SEPARATOR +"menu_button.wav");


	musicURL[0] = ClassLoader.getSystemResource("audio" + SEPARATOR +"game_background_music.wav");
	musicURL[1] = ClassLoader.getSystemResource("audio" + SEPARATOR +"boss_background_music.wav");
	musicURL[2] = ClassLoader.getSystemResource("audio" + SEPARATOR +"menu_background_music.wav");
	musicURL[3] = ClassLoader.getSystemResource("audio" + SEPARATOR +"score_background_music.wav");
    }


    public void setFileSound(int i){
	try{
	    LOGGER.addHandler(fh);
	    fh.setFormatter(formatter);

	    AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
	    clipSound = AudioSystem.getClip();
	    clipSound.open(ais);

	} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
	    LOGGER.log(Level.FINE, e.getMessage());
	    e.printStackTrace();
	}
    }
    public void setFileMusic(int i){
	try{
	    LOGGER.addHandler(fh);
	    fh.setFormatter(formatter);

	    AudioInputStream ais = AudioSystem.getAudioInputStream(musicURL[i]);

	    clipMusic = AudioSystem.getClip();
	    clipMusic.open(ais);

	} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
	    LOGGER.log(Level.FINE, e.getMessage());
	    e.printStackTrace();
	}
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
