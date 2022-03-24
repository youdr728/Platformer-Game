package se.liu.joeri765youdr728.Platformer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author      Johannes Eriksson <joeri765 @ student.liu.se>
 * @author      Yousef Drgham <youdr728 @ student.liu.se>
 * @version     1.0
 * @since       1.0
 */
public class Sound
{
    private static final Logger LOGGER = Logger.getLogger(Sound.class.getName() );

    private Clip clipSound = null;
    private Clip clipMusic = null;
    private URL[] soundURL = new URL[10];
    private URL[] musicURL = new URL[10];

    public Sound() {
	soundURL[0] = getClass().getResource("Sounds" + File.separator + "player_death.wav");
	soundURL[1] = getClass().getResource("Sounds" + File.separator +"door.wav");
	soundURL[2] = getClass().getResource("Sounds" + File.separator +"point.wav");
	soundURL[3] = getClass().getResource("Sounds" + File.separator +"speed_jump_powerup.wav");
	soundURL[4] = getClass().getResource("Sounds" + File.separator +"time_powerup.wav");
	soundURL[5] = getClass().getResource("Sounds" + File.separator +"jump.wav");
	soundURL[6] = getClass().getResource("Sounds" + File.separator +"fireball.wav");
	soundURL[7] = getClass().getResource("Sounds" + File.separator +"menu_button.wav");


	musicURL[0] = getClass().getResource("Sounds" + File.separator +"game_background_music.wav");
	musicURL[1] = getClass().getResource("Sounds" + File.separator +"boss_background_music.wav");
	musicURL[2] = getClass().getResource("Sounds" + File.separator +"menu_background_music.wav");
	musicURL[3] = getClass().getResource("Sounds" + File.separator +"score_background_music.wav");
    }


    public void setFileSound(int i){
	try{
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
