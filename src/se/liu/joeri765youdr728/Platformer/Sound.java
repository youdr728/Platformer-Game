package se.liu.joeri765youdr728.Platformer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound
{
    Clip clipSound;
    Clip clipMusic;
    URL soundURL[] = new URL[10];
    URL musicURL[] = new URL[10];

    public Sound() {
	soundURL[0] = getClass().getResource("Sounds/player_death.wav");
	soundURL[1] = getClass().getResource("Sounds/door.wav");
	soundURL[2] = getClass().getResource("Sounds/point.wav");
	soundURL[3] = getClass().getResource("Sounds/speed_jump_powerup.wav");
	soundURL[4] = getClass().getResource("Sounds/time_powerup.wav");
	soundURL[5] = getClass().getResource("Sounds/jump.wav");
	soundURL[6] = getClass().getResource("Sounds/fireball.wav");
	soundURL[7] = getClass().getResource("Sounds/menu_button.wav");


	musicURL[0] = getClass().getResource("Sounds/game_background_music.wav");
	musicURL[1] = getClass().getResource("Sounds/boss_background_music.wav");
	musicURL[2] = getClass().getResource("Sounds/menu_background_music.wav");

    }


    public void setFileSound(int i){
	try{
	    AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
	    clipSound = AudioSystem.getClip();
	    clipSound.open(ais);

	}catch (Exception e){

	}
    }
    public void setFileMusic(int i){
	try{
	    AudioInputStream ais = AudioSystem.getAudioInputStream(musicURL[i]);

	    clipMusic = AudioSystem.getClip();
	    clipMusic.open(ais);
	}catch (Exception e){

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
