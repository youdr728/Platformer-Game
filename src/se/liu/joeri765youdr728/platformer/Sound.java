package se.liu.joeri765youdr728.platformer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

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


    public void setFileSound(int i) throws Exception {

	    AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
	    clipSound = AudioSystem.getClip();
	    clipSound.open(ais);

    }
    public void setFileMusic(int i) throws Exception {
	    AudioInputStream ais = AudioSystem.getAudioInputStream(musicURL[i]);
	    clipMusic = AudioSystem.getClip();
	    clipMusic.open(ais);


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
