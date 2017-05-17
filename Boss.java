/*
 Boss
 Class represents character of a boss
 @author Ken Tjie
 @create 2016 - 08 - 01
 @update 2016 - 18 - 01
 @version 1.7 
 */

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Color;

public class Boss extends Character {
  private String type;
  
  /*
   Boss
   @param name: name for the boss
   @param type: type for the boss
   @param hp: health for the boss
   @param attack: base attack stat of the boss
   @param defense: base defense stat for the boss
   @Precondition: name must not be null
   @Post-condition: instanitates an instance of the Boss class
   */
  public Boss (String name, String type, int hp, int attack, int defense) {
    super(name, hp, attack, defense);
    this.type = type;
    sprite = new ImageIcon("sprites/" + name + ".png");
    x = 1300 - sprite.getIconWidth();
    y = 0;
    maxX = x + sprite.getIconWidth();
    maxY = y + sprite.getIconHeight();
  }//end constructor
  
  /*
   getType
   @Post-condition: returns the type of the boss
   @return type
   */
  public String getType () {
    return type;
  }//end getType
  
  /*
   move
   @param distance: distance to move in pixels
   @Post-condition: moves the sprite of the Boss class
   */
  public void move (int distance) {
    y += distance;
    maxY += distance;
  }//end move
  
  /*
   updateHP
   @param gc: place to draw
   @Post-condition: draws rectangle accodring to health of the boss
   */
  public void updateHP (Graphics gc) { 
    gc.setColor(Color.GREEN);
    
    if ((currentHealth/health) * 100 <= 50)
      gc.setColor(Color.YELLOW);
    
    if ((currentHealth/health) * 100 <= 12)
      gc.setColor(Color.RED);
    
    gc.drawString(name + " HP: " + Math.round(currentHealth) + " / " + (int) health, 798, 620);
    gc.fillRect((int)(1300 - 2 - (currentHealth/health) * 500), 630, (int)((currentHealth/health) * 500), 20);
  }//end updateHP
}//end class