/*
 Projectile
 Class represents a projectile
 @author Ken Tjie
 @created 2016 - 06 - 01
 @update 2016 - 18 - 2015
 @version 1.4 
 */

import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Projectile {
  protected int x, y, maxX, maxY, radius;
  
  /*
   Projectile
   @param radius: radius of the projectile
   @Post-condition: instantiates an instance of the Projectile class
   */
  public Projectile (int radius) {
    this.radius = radius;
  }//end radius
  
  /*
   setX
   @param x: x-coordinate of character
   @Post-condition: sets the x value of the character
   */
  public void setX (int x) {
    this.x = x;
    maxX = this.x + 2  * radius;
  }//setX
  
  /*
   getX
   @Post-condition: returns the x value
   @return x
   */
  public int getX () {
    return x;
  }//end getX
  
  /*
   setY
   @param y: y-coordinate of character
   @Post-condition: sets the y value of the character
   */
  public void setY (int y) {
    this.y = y;
  }//end setY
  
  /*
   getY
   @Post-condition: returns the y value
   @return y
   */
  public int getY () {
    return y;
  }//end getY
  
  /*
   setMaxX
   @param x: x-coordinate of character
   @Post-condition: sets the maxX value of the character
   */
  public void setMaxX (int x) {
    maxX = x;
  }//setMaxX
  
  /*
   getMaxX
   @Post-condition: returns maxX value
   @return maxX
   */
  public int getMaxX () {
    return maxX;
  }//end getMaxX
  
  /*
   setMaxY
   @param y: y-coordinate of projectile
   @Post-condition: sets the maxY value of the projectile
   */
  public void setMaxY (int y) {
    maxY = y;
  }//end setMaxY
  
  /*
   getMaxY
   @Post-condition: returns the maxY value
   @return maxY
   */
  public int getMaxY () {
    return maxY;
  }//end getMaxY
  
  /*
   isColliding
   @Post-condition: checks if collidee is colliding with projectile
   @return true if character collides
   @return false if character doesn't collide
   */
  public boolean isColliding(Character collidee) {
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
  
  public abstract void move(int level, Character target);
  public abstract void collisionReaction(Character attacker, Character defender);
  public abstract void draw(Graphics gc);
}//end class