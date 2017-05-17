/*
 Character
 Class represents a character
 @author Ken Tjie
 @create 2015 - 06 - 01
 @update 2015 - 18 - 01
 @version 1.6 
 */

import java.awt.Graphics;
import javax.swing.ImageIcon;

public abstract class Character {
  protected String name;
  protected int x, y, maxX, maxY, health, attack, defense;
  protected double currentHealth;
  protected ImageIcon sprite;
  
  /*
   Character
   @param name: name for the character
   @param hp: health for the character
   @param attack: base attack stat of the character
   @param defense: base defense stat for the character 
   @Precondition: name must not be null
   @Post-condition: instanitates an instance of the Character class
   */
  public Character (String name, int hp, int attack, int defense) {
    this.name = name;
    health = 204 + 2 * hp;
    this.attack = (int)(1.10 * (2 * attack + 5));
    this.defense = (int)(1.10 * (2 * defense + 5));
    currentHealth = (double)health;
  }//end constructor
  
  /*
   setName
   @param name: name for the character
   @Precondition: name must not be null
   @Post-condition: sets the name of the character
   */
  public void setName (String name) {
    this.name = name;
  }//end setName
  
  /*
   getName
   @Post-condition: returns name of character
   @return name
   */
  public String getName () {
    return name;
  }//end getName
  
  /*
   setX
   @param x: x-coordinate of character
   @Post-condition: sets the x value of the character
   */
  public void setX (int x) {
    this.x = x;
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
   @Post-condition: returns the maxX vlaue
   @return maxX
   */
  public int getMaxX () {
    return maxX;
  }//end getMaxX
  
  /*
   setMaxY
   @param y: y-coordinate of character
   @Post-condition: sets the maxY value of the character
   */
  public void setMaxY (int y) {
    maxY = y;
  }//end getMaxY
  
  /*
   getMaxY
   @Post-condition: returns the maxY value
   @return maxY
   */
  public int getMaxY () {
    return maxY;
  }//end getMaxY
  
  /*
   getMaxHealth
   @Post-condition: returns health stat of character
   @return health
   */
  public int getMaxHealth () {
    return health;
  }//end getMaxHealth
  
  /*
   getHealth
   @Post-condition: returns character's health
   @return currentHealth
   */
  public double getHealth () {
    return currentHealth;
  }//end getHealth
  
  /*
   setHealth
   @param hp: new value of currenthealth
   @Post-condition: sets the currentHealth of the character
   */
  public void setHealth(double hp) {
    currentHealth = hp;
    
    if (currentHealth > health)
      currentHealth = (double)health;
  }//end setHealth
  
  /*
   getAttack
   @Post-condition: returns attack
   @return attack
   */
  public int getAttack() {
    return attack;
  }//end getAttack
  
  /*
   getDefense
   @Post-condition: returns defense
   @return defense
   */
  public int getDefense() {
    return defense;
  }//end getDefense
  
  /*
   toString
   @Post-condition: prints out the stats of the character
   @return stats of the character
   */
  public String toString () {
    return name + "\nHP: " + health + "\nAttack: " + attack + "\nDefense: " + defense + "\n";
  }//end toString
  
  /*
   draw
   @param gc: place to draw
   @Post-condition: draws the sprites on the screen
   */
  public void draw(Graphics gc) {
    sprite.paintIcon(null, gc, x, y);
  }//end draw
  
  public abstract void updateHP (Graphics gc);
}//end class