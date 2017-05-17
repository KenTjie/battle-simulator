/*
 BossProjectile
 Class represents bosses' projectiles
 @author Ken Tjie
 @create 2016 - 07 - 01
 @uodate 2016 - 18 - 01
 @version 1.9 
 */

import java.awt.Graphics;
import java.awt.Color;

public class BossProjectile extends Projectile {
  private String type;
  private int velY = 2;
  
  /*
   BossPorjectile
   @param x: x-coordinate
   @param y: y-coordinate
   @param type: type of the projectile
   @Pre-condition: type must not be null
   @Post-condition: searches the scores array for the specified points
   */
  public BossProjectile (int x, int y, String type) {
    super(20);
    this.type = type;
    this.x = x - 2 * radius - 5;
    this.y = y - radius;
    maxX = this.x + 2 * radius;
    maxY = this.y + 2 * radius;
  }//end constructor
  
  /*
   move
   @param level: current level of the game
   @param target: character to be targeted
   @Precondition: level must be between 1 - 6
   @Post-condition: moves the projectiles movement according to the level
   */
  public void move(int level, Character target) {
    x -= 2;
    maxX -= 2;
    
    if (level == 3 || level == 4) {
      if (y < 0) {
        y = 0;
        maxY = y + 2 * radius;
        velY = -velY;
      }//end if
      
      else if (maxY > 650) {
        maxY = 650;
        y = 650 - 2*radius;
        velY = -velY;
      }//end if
      
      if (level == 4 && radius < 30 && x > 500 && maxX < 900) {
        radius ++;
        x -= 1;
        maxX += 1;
        y -= 1;
        maxY += 1;
      }//end if
      
      y += velY;
      maxY += velY;
    }//end if
    
    else if (level == 5) {
      int randomNum = (int)(Math.random()*10000);
      if (y < 0 || maxY > 650 || randomNum < 20)
        velY = -velY;
      
      y += velY;
      maxY += velY;
    }//end else if
    
    else if (level == 6) {
      if ((target.getY() + target.getMaxY()) / 2  > (y + maxY) / 2 && x > 400) {
        y += velY;
        maxY += velY;
      }//end if
      
      else if ((target.getY() + target.getMaxY()) / 2  < (y + maxY) / 2 && x > 400) {
        y -= velY;
        maxY -= velY;
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
    double hpDecrease = (((210) / 250.0) * ((double)attacker.getAttack() / defender.getDefense()) * 80 + 2)*(1.5 * (((Math.random() * 16) + 85) / 100));
    defender.setHealth(defender.getHealth() - hpDecrease);
  }//end collisionReaction
  
  /*
   draw
   @param gc: place to draw
   @Post-condition: draws a projectile accoridng to the type of the boss
   */
  public void draw(Graphics gc) {
    //change color according to type
    switch (type) {
      case "Normal":
        gc.setColor(Color.LIGHT_GRAY);
        break;
      case "Fighting":
        gc.setColor(Color.RED);
        break;
      case "Flying":
        gc.setColor(new Color (0, 255, 255));
        break;
      case "Rock":
        gc.setColor(new Color (102, 51, 0));
        break;
      case "Ground":
        gc.setColor(new Color (204, 102, 0));
        break;
      case "Steel":
        gc.setColor(Color.GRAY);
        break;
      case "Fire":
        gc.setColor(Color.ORANGE);
        break;
      case "Water":
        gc.setColor(Color.BLUE);
        break;
      case "Grass":
        gc.setColor(Color.GREEN);
        break;
      case "Dark":
        gc.setColor(Color.BLACK);
        break;
      case "Ghost":
        gc.setColor(new Color(51, 0 , 102));
        break;
      case "Psychic":
        gc.setColor(Color.MAGENTA);
        break;
      case "Bug":
        gc.setColor(new Color(102, 155, 102));
        break;
      case "Poison":
        gc.setColor(new Color(153, 51, 255));
        break;
      case "Electric":
        gc.setColor(Color.YELLOW);
        break;
      case "Ice":
        gc.setColor(new Color(153, 255, 255));
        break;
      case "Dragon":
        gc.setColor(new Color(51, 51, 255));
        break;
      case "Fairy":
        gc.setColor(Color.PINK);
        break;
      default:
        gc.setColor(Color.WHITE);
        break;
    }//end switch
    
    gc.fillOval(x, y, 2 * radius, 2 * radius);
  }//end draw
}//end class