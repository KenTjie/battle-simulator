/*
 MusicPlayer
 Class to play music
 @author Ken Tjie
 @create 2016 - 07 - 01
 @update 2016- 08 - 01
 @version 1.1
 */

import java.applet.Applet;
import java.net.URL;
import java.net.MalformedURLException;
import java.applet.AudioClip;

public class MusicPlayer {
  /*
   MusicPlayer
   @Post-condition: instantiates an instance of the MusicPlayer class
   */
  public MusicPlayer () {
    AudioClip[] sounds = new AudioClip[2];
    int randomNum = (int)(Math.random()*sounds.length);
    try {
      sounds[0] = Applet.newAudioClip(new URL("file:music/ORASGymLeader.wav"));
      sounds[1] = Applet.newAudioClip(new URL("file:music/ORASFrontierBrain.wav"));
    } 
    catch (MalformedURLException e) {}//end try catch
    
    sounds[randomNum].loop();
  }//end constructor
}//end class