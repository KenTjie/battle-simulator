/*
 PlayerProjectile
 Class represents the player's porjectile
 @author Ken Tjie
 @create 2015 - 07 - 01
 @update 2015 - 18 - 01
 @version 1.5 
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class PlayerProjectile extends Projectile{  
  /*
   PlayerProjetile
   @param x: x-coordinate
   @param y: y-coordinate
   @Post-condition: instantiates an instance of the PlayerProjectile class
   */
  public PlayerProjectile (int x, int y) {
    super(20);
    this.x = x + 5;
    this.y = y - radius;
    maxX = this.x + 2*radius;
    maxY = this.y + 2*radius;
  }//end constructor
  
  /*
   move
   @param level: game's current level
   @param target: character to be targeted
   @Precondiiton: level must be between 1 - 6
   @Post-condition: moves the projectile according to level
   */
  public void move(int level, Character target) {
    x += 5;
    maxX = x + 2*radius;
    
    if (level == 6) {
      if ((target.getY() + target.getMaxY()) / 2  > (y + maxY) / 2 && maxX < 400) {
        y += 4;
        maxY += 4;
      }//end if
      
      else if ((target.getY() + target.getMaxY()) / 2  < (y + maxY) / 2 && maxX < 400) {
        y -= 4;
        maxY -= 4;
      }//end else if
    }//end if
  }//end move
  
  /*
   collisionReaction
   @param attacker: character that attacks
   @param defender: character that defends
   @Post-condition: calculates damage to deal to the defending character
   */
  public void collisionReaction(Character attacker, Character defender) {
    double hpDecrease = (((210)/250.0)*((double)attacker.getAttack()/defender.getDefense())*(80) + 2)*(1.5*(((Math.random()*16) + 85) / 100));
    defender.setHealth(defender.getHealth() - (hpDecrease/4));
  }//end collisionReaction
  
  /*
   draw
   @param gc: place to draw
   @Post-condition: draws the projectile on the screen
   */
  public void draw(Graphics gc) {
    gc.setColor(Color.YELLOW);
    gc.fillOval(x, y, 2*radius, 2*radius);
  }//end draw 
}//end class