/*
 BattlePlayer
 Class to instantiate BattleFrame and MusicPlayer classes
 @author Ken Tjie
 @create 2016 - 06 - 01
 @update 2016 - 06 - 01
 @version 1.3 
 */

import javax.swing.JFrame;
import java.io.FileNotFoundException;

public class BattlePlayer {
  public static void main (String [] args) throws FileNotFoundException {
    BattleFrame battleFrame = new BattleFrame();
    battleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //MusicPlayer music = new MusicPlayer();
  }//end main
}//end class