package se.liu.joeri765youdr728.Platformer;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound
{
    Clip clip;
    URL soundURL[] = new URL[10];

    public Sound() {
	soundURL[0] = getClass().getResource("Sounds/player_death.wav");
	soundURL[1] = getClass().getResource("Sounds/game_background_music.wav");
	soundURL[2] = getClass().getResource("Sounds/door.wav");
	soundURL[3] = getClass().getResource("Sounds/point.wav");
	soundURL[4] = getClass().getResource("Sounds/speed_jump_powerup.wav");
	soundURL[5] = getClass().getResource("Sounds/time_powerup.wav");
	soundURL[6] = getClass().getResource("Sounds/jump.wav");

	soundURL[7] = getClass().getResource("Sounds/menu_background_music.wav");
	soundURL[8] = getClass().getResource("Sounds/menu_button.wav");

    }

    public void setFile(int i){
	try{
	    AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
	    clip = AudioSystem.getClip();
	    clip.open(ais);
	}catch (Exception e){

	}
    }

    public void play(){
	clip.start();
    }

    public void loop(){
	clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
	clip.stop();
    }
}
