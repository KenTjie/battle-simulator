/*
 Item
 Class represents collectable items
 @author Ken Tjie
 @create 2015 - 17 - 01
 @update 2015 - 17 - 01
 @version 1.4 
 */

import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Item {
  int x, y, maxX, maxY;
  String name;
  ImageIcon sprite;
  
  /*
   Item
   @param name: name of the item
   @Pre-condition: name must be Leppa, Sitrus or Coin
   @Post-condition: instantiates an instance of the Item class
   */
  public Item (String name) {
    this.name = name;
    
    if (name.equalsIgnoreCase("Leppa"))
      sprite = new ImageIcon("sprites/leppa.png");  
    else if (name.equalsIgnoreCase("Sitrus"))
      sprite = new ImageIcon("sprites/sitrus.png");
    else if (name.equalsIgnoreCase("Coin"))
      sprite = new ImageIcon("sprites/coin.png");
    
    x = (int)(Math.random()*300) + 10;
    y = (int)(Math.random()*591) + 10;
    maxX = x + sprite.getIconWidth();
    maxY = y + sprite.getIconHeight();
  }//end constructor
  
  /*
   getName
   @Post-condition: returns name of item
   @return name
   */
  public String getName() {
    return name;
  }//end getName
  
  /*
   moveoffScreen
   @Post-condition: moves item out of boundries
   */
  public void moveOffScreen () {
    this.x = -50;
    this.y = -10 - sprite.getIconHeight();
    maxX = x + sprite.getIconWidth();
    maxY = y + sprite.getIconHeight();
  }//end moveOffScreen
  
  /*
   isColliding
   @param collidee: character the it may collide
   @Post-condition: checks if collidee is colliding with item
   @return true if character collides
   @return false if character doesn't collide
   */
  public boolean isColliding (Character collidee) {
    if (collidee.getMaxX() < x)
      return false;
    
    if (collidee.getX() > maxX)
      return false;
    
    if (collidee.getY() > maxY)
      return false;
    
    if (collidee.getMaxY() < y)
      return false;
    
    return true; 
  }//end isColliding
  
  /*
   spawn
   @param gc: place to draw
   @Post-condition: paints the sprite on the screen
   */
  public void spawn (Graphics gc) {
    sprite.paintIcon(null, gc, x, y);
  }//end spawn
}//end class