/*
 Player
 Class prepresents player's character
 @author Ken Tjie
 @create 2016 - 06 - 01
 @update 2016 - 18 - 01
 @version 1.8 
 */

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;

public class Player extends Character {  
  /*
   Player
   @Post-condition: instantiates an instance of the Player class
   */
  public Player () {
    super("Pikachu", 35, 55, 50);
    sprite = new ImageIcon("sprites/Pikachu.png");
    x = 0;
    y = 0;
    maxX = x + sprite.getIconWidth();
    maxY = y + sprite.getIconHeight();
  }//end constructor
  
  /*
   Player
   @param name: name of player
   @Precondition: name must not be null
   @Post-condition: instantiates an instance of the Player class
   */
  public Player (String name) {
    super(name, 100, 80, 100);
    this.name = name;
    sprite = new ImageIcon("sprites/Pikachu.png");
    x = 0;
    y = 0;
    maxX = x + sprite.getIconWidth();
    maxY = y + sprite.getIconHeight();
  }//end constructor
  
  /*
   move
   @param velX: velocity of the x-coordinates
   @param velY: velocity of the y-coordinates
   @Post-condition: moves sprite of the player
   */
  public void move (int velX, int velY) {
    if (x < 0) {
      x = 0;
      maxX = x + sprite.getIconWidth();
      velX = 0;
    }//end if
    
    if (maxX > 400) {
      x = 400 - sprite.getIconWidth();
      maxX = 400;
      velX = 0;
    }//end if
    
    if (y < 0) {
      y = 0;
      maxY = y + sprite.getIconHeight();
      velX = 0;
    }//end if
    
    if (maxY > 650) {
      y = 650 - sprite.getIconHeight();
      maxY = 650;
      velX = 0;
    }//end if
    
    x += velX;
    maxX += velX;
    y += velY;
    maxY += velY;
  }//end move
  
  /*
   updateHP
   @param gc: place to draw
   @Post-condition: draws the health bar accodring to health of the player
   */
  public void updateHP (Graphics gc) {    
    
    gc.setColor(Color.GREEN);
    
    if (currentHealth/health <= 0.5)
      gc.setColor(Color.YELLOW);
    
    if (currentHealth/health <= 0.125)
      gc.setColor(Color.RED);
    
    gc.drawString(name + " HP: " + Math.round(currentHealth) + " / " + (int) health, 5, 620);
    gc.fillRect(5, 630, (int)((currentHealth/health) * 500), 20);
  }//end updateHP
}//end class