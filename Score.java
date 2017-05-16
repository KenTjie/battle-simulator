/*
 Score
 Class represents a score
 @author Ken Tjie
 @created 2016 - 12 - 01
 @updated 2016 - 14 - 2015
 @version 1.1 
 */

public class Score {
  private String name;
  private int points, levelsBeat;
  
  /*
   Score
   @param name: name of the player for the score
   @param points: number of points
   @param levelsBeat: number of levels beaten
   @Precondition: name must not be null
   @Post-condition: Instantiates an instance of the Score class
   */
  public Score (String name, int points, int levelsBeat) {
    this.name = name;
    this.points = points;
    this.levelsBeat = levelsBeat;
  }//end constructor
  
  /*
   getName
   @Post-condition: gets the name in the score
   @return name
   */
  public String getName() {
    return name;
  }//end getName
  
  /*
   getPoints
   @Post-condition: gets the points in the score
   @return points
   */
  public int getPoints() {
    return points;
  }//end getPoints
  
  /*
   getLevels
   @Post-condition: gets the number of levels beaten in the score
   @return levelsBeat
   */
  public int getLevels() {
    return levelsBeat;
  }//end getLevels
  
  /*
   toString
   @Post-conditon: return a string with the data of the score
   @return string of data for the score
   */
  public String toString () {
    return "Name: " + name + "          Points: " + points + "          Levels Beat: " + levelsBeat + "\n\n";
  }//end toString
}//end class