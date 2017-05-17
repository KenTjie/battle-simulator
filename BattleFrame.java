/*
 BattleFrame
 Class displays window for the game
 @author Ken Tjie
 @create 2016 - 06 - 01
 @update 2016 - 18 - 01
 @version 1.6 
 */

import javax.swing.JFrame;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.awt.Dimension;

public class BattleFrame extends JFrame {
  private Boss[] chosenBosses = new Boss[6];
  private Score[] scores;
  
  /*
   BattleFrame
   @Precondition al text files must exist
   @Post-condition: instantiates an instance of the BattleFrame class
   @Post-condition: fills the array of scores and bosses and contians the instance of the BattlePanel class to play
   */
  public BattleFrame () throws FileNotFoundException {
    super("Battle!");
    
    int count = 0;
    String temp;
    Scanner input = new Scanner (new FileReader("scores.txt"));
    
    //count the lines in scores.txt
    while (input.hasNextLine()) {
      count ++;
      temp = input.nextLine();
    }//end while
    
    input.close();
    
    scores = new Score[count/3 + 1];
    
    input = new Scanner (new FileReader("scores.txt"));
    
    //store the scores in the array called scores
    for (int i = 0; i < scores.length - 1; i++)
      scores[i] = new Score(input.nextLine(), Integer.parseInt(input.nextLine()), Integer.parseInt(input.nextLine()));
    
    input.close();
    
    //fill the first two index positions with regular Pokemon
    ArrayList<Boss> bosses = new ArrayList<Boss>();  
    input = new Scanner (new FileReader("bosses/regulars.txt"));
    
    while (input.hasNextLine())
      bosses.add(new Boss(input.nextLine(), input.nextLine(), Integer.parseInt(input.nextLine()), Integer.parseInt(input.nextLine()), Integer.parseInt(input.nextLine())));
    
    input.close();
    
    for (int i = 0; i < 2; i++) {
      int randomNum = (int)(Math.random()*bosses.size());
      chosenBosses[i] = bosses.remove(randomNum);
    }//end for
    
    //fill the next two index positions with mega Pokemon
    bosses = new ArrayList<Boss>();  
    input = new Scanner (new FileReader("bosses/megas.txt"));
    
    while (input.hasNextLine())
      bosses.add(new Boss(input.nextLine(), input.nextLine(), Integer.parseInt(input.nextLine()), Integer.parseInt(input.nextLine()), Integer.parseInt(input.nextLine())));
    
    input.close();
    
    for (int i = 2; i < 4; i++) {
      int randomNum = (int)(Math.random()*bosses.size());
      chosenBosses[i] = bosses.remove(randomNum);
    }//end for
    
    //fill the last two index positions with legendary Pokemon
    bosses = new ArrayList<Boss>();  
    input = new Scanner (new FileReader("bosses/legendaries.txt"));
    
    while (input.hasNextLine())
      bosses.add(new Boss(input.nextLine(), input.nextLine(), Integer.parseInt(input.nextLine()), Integer.parseInt(input.nextLine()), Integer.parseInt(input.nextLine())));
    
    input.close();
    
    for (int i = 4; i < 6; i++) {
      int randomNum = (int)(Math.random()*bosses.size());
      chosenBosses[i] = bosses.remove(randomNum);
    }//end for
    
    add (new BattlePanel(chosenBosses, scores));
    this.getContentPane().setPreferredSize(new Dimension(1300, 650));
    this.pack();
    setResizable(false);
    setVisible(true);
  }//end constructor
}//end class