/*
 BattlePanel
 Class to control logic of the game
 @author Ken Tjie
 @create 2016 - 06 - 01
 @update 2016 - 18 - 01
 @version 2.9
 */

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class BattlePanel extends JPanel implements ActionListener, KeyListener {
  int velX = 0, velY = 0, bossMove = 1, playerPP = 10, level = 6, points = 0; //player's x and y velocity, boss's velocity, player power points, level and points
  String userName; //user's name
  boolean gameBeaten = false; //keeps track if the game has been beaten or not
  Player player = new Player(); //Player's object
  Boss boss; // boss to fight
  Boss[] bosses; //array of 6 bosses for the battle
  Score[] scores; //array of scores
  ArrayList<Projectile> projectiles = new ArrayList<Projectile>(); //ArrayList for all projectiles
  ArrayList<Item> items = new ArrayList<Item>(); //ArrayList of all the items
  Timer timer = new Timer (5, this); //Timer for animation
  
  /*
   BattlePanel
   @param Boss[] bosses: array of bosses for the game
   @param Score[] scores: array of scores
   @Precondition: bosses must have 6 instances of Boss
   @Post-condition: displays instructions, fills array of bosses and scores with given parameters, instantiates a Player
   */
  public BattlePanel (Boss[] bosses, Score[] scores) { 
    
    //display instructions and rules
    JOptionPane.showMessageDialog(null, "Welcome to Battle!");
    JOptionPane.showMessageDialog(null, "You've been challenged by a Pokemon Trainer! Guess what?");
    JOptionPane.showMessageDialog(null, "You only have your Pikachu on you!");
    JOptionPane.showMessageDialog(null, "Your job is to win the battle! \nIf you lose, you'll lose money and black out!");
    JOptionPane.showMessageDialog(null, "It's a 6 vs. 1 battle, don't mess up!");
    JOptionPane.showMessageDialog(null, "Arrow keys are to move Pikachu. Space is to shoot your attack.");
    JOptionPane.showMessageDialog(null, "You start off with 10 PP (power points) which is how many times you can shoot.\n You must keep track of how many shots you have!");
    JOptionPane.showMessageDialog(null, "Several items will pop up on your side of the field:\nThe Leppa Berry is red and will add 3 PP.\nThe Sitrus Berry is yellow and will add 50 HP to Pikachu.\nThe coins will add 10 to your score.");
    JOptionPane.showMessageDialog(null, "the more points you gain, the higher you can be on the leaderboards so collect!");
    JOptionPane.showMessageDialog(null, "IMPORTANT NOTE: Don't press another arrow key without letting go of the previous one \nor else Pikachu will stop until you press again.");
    JOptionPane.showMessageDialog(null, "You can't go off screen or too far to the right.");
    JOptionPane.showMessageDialog(null, "Also, go to the music folder to play the music manually, it ups the action!");
    JOptionPane.showMessageDialog(null, "", "!!!", JOptionPane.PLAIN_MESSAGE, new ImageIcon("vs..png"));
    
    timer.start(); //start the timer for animations
    this.bosses = bosses; //give the array of bosses to the BattlePanel
    this.scores = scores; // give the array of scores to the BattlePanel
    boss = bosses[level - 1]; //set boss to the birst boss in the array
    //give event listeners
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
    
    //ask the user for a name
    userName = JOptionPane.showInputDialog("Nickname your Pikachu! Type 'no' if you don't want to");
    while (userName == null)
      userName = JOptionPane.showInputDialog("Nickname your Pikachu! Type 'no' if you don't want to");
    
    //create a Player depending on user's input
    if (userName.equalsIgnoreCase("no"))
      player = new Player();
    else
      player = new Player(userName);
    
    JOptionPane.showMessageDialog(null, "Pokemon Trainer Red sent out "+ boss.getName() + "\nRound " + level + " : " +  player.getName() + " Vs. " + boss.getName() + "\n" + player + "\n" + boss);
  }//end constructor
  
  /*
   paintComponent
   @param gc: area to paint
   @Post-condition: paints all instances of Item, Projectile, and Characters on the screen
   */
  public void paintComponent (Graphics gc) {
    super.paintComponent(gc);
    
    Graphics2D g2 = (Graphics2D) gc;
    Font font = new Font("Comic Sans MS", Font.PLAIN, 18);
    g2.setFont(font);
    
    ImageIcon background = new ImageIcon("background.jpg");
    background.paintIcon(this, gc, 0, 0);
    
    int rng = (int)(Math.random()*1000) + 1;
    
    if (rng < 15)
      projectiles.add(new BossProjectile(boss.getX(), (boss.getY() + boss.getMaxY()) / 2, boss.getType()));
    
    if (rng > 998)
      items.add(new Item("Leppa"));
    
    if (rng < 5)
      items.add(new Item("Sitrus"));
    
    if (rng < 2)
      items.add(new Item("Coin"));
    
    for (Item currItem : items) {
      if (currItem.getName().equalsIgnoreCase("Sitrus") && player.getHealth() < player.getMaxHealth())
        currItem.spawn(gc);
      
      else if (currItem.getName().equalsIgnoreCase("Sitrus"))
        currItem.moveOffScreen();
      
      else
        currItem.spawn(gc);
    }//end for
    
    for (Projectile ball : projectiles)
      ball.draw(gc);
    
    gc.setColor(new Color(200, 0, 255));
    gc.drawString( "Level: " + level + "          " +  "Score: " + points + "                    " + player.getName() + " Vs. " + boss.getName(), 0, 20);
    
    player.updateHP(gc);
    boss.updateHP(gc);
    
    boss.draw(gc);
    player.draw(gc);
  }//end paintComponent
  
  /*
   actionPerformed
   @param e: event source
   @Post-condition: animates all the objects and checks for collisons
   */
  public void actionPerformed (ActionEvent e) {  
    player.move(velX, velY);
    
    for (Projectile ball : projectiles) {
      ball.move(level, player);
    }//end for
    
    //change velocity of the boss when it hits the edge
    if (boss.getY() < 0 || boss.getMaxY() > 650) 
      bossMove = -bossMove;    
    boss.move(bossMove);
    
    //does collision reaction according to the item
    for (Item currItem : items) {
      if (currItem.isColliding(player)) {
        currItem.moveOffScreen();
        
        if (currItem.getName().equalsIgnoreCase("Leppa"))
          playerPP += 3;
        
        else if (currItem.getName().equalsIgnoreCase("Sitrus"))
          player.setHealth(50 + player.getHealth());
        
        else if (currItem.getName().equalsIgnoreCase("Coin"))
          points += 10;
      }//end if
    }//end for
    
    //check if any projectiles are colliding
    for (Projectile ball : projectiles) {
      if (ball.isColliding(boss)) {
        ball.setX(1400);
        ball.collisionReaction(player, boss);
      }//end if
      
      else if (ball.isColliding(player)) {
        ball.setX(-100);
        ball.collisionReaction(boss, player);
      }//end else if
      
      //check if the boss' HP is 0
      if (boss.getHealth() <= 0) {
        //end the game if the player is on the last level
        if (level == 6) {
          JOptionPane.showMessageDialog(null, "Yay! You beat the game! gg");
          gameOver();
        }//end if
        nextLevel();
      }//end if
      
      //check if the player's HP is 0
      if (player.getHealth() <= 0) {
        player.setHealth(0);
        int newPoints = (int)(points * (((Math.random() * 26) + 25) / 100)); //reduce the money by 25% - 50%
        points = ((newPoints + 5) / 10) * 10;
        repaint();
        JOptionPane.showMessageDialog(null, "You lost! You are left with " + points + " coins running to the Pokemon Center");
        gameOver();
      }//end if
      
    }//end for
    
    repaint();
  }//end actionPerformed
  
  /*
   keyPressed
   @param e: key that is pressed
   @Post-condition: sets velY and velX according to the arrow key pressed
   */
  public void keyPressed (KeyEvent e) {
    int code = e.getKeyCode();
    
    //move player up
    if (code == KeyEvent.VK_UP) {
      velX = 0;
      velY = -2;
    }//end if
    
    //move player down
    if (code == KeyEvent.VK_DOWN) {
      velX = 0;
      velY = 2;
    }//end if
    
    //move player right
    if (code == KeyEvent.VK_RIGHT) {
      velX = 2;
      velY = 0;
    }//end if
    
    //move player left
    if (code == KeyEvent.VK_LEFT) {
      velX = -2;
      velY = 0;
    }//end if 
  }//end keyPressed
  
  public void keyTyped (KeyEvent e) {}//end keyTyped
  
  /*
   keyRekeased
   @param e: key that is released
   @Post-condition: creates player's projectiles, set velX and velY to 0 if keys are released
   */
  public void keyReleased (KeyEvent e) {
    int code = e.getKeyCode();
    
    //shoot a projectile if  PP is greater than 0
    if (code == KeyEvent.VK_SPACE) {
      if (playerPP > 0) {
        playerPP --;
        projectiles.add(new PlayerProjectile(player.getMaxX(), (player.getY() + player.getMaxY()) / 2));
      }//end inner if
    }//end outer if
    
    //stop the player
    else {
      velX = 0;
      velY = 0;
    }//end else
  }//end keyReleased
  
  /*
   nextLevel
   @Post-condition: updates all the variables to play the next level
   */
  public void nextLevel () {
    //stop the player
    velX = 0;
    velY = 0;
    boss.setHealth(0); //reduce boss' health to 0
    player.setHealth(player.getMaxHealth()); //restore HP
    repaint();
    
    //update boss stats and level
    JOptionPane.showMessageDialog(null, "Level complete!" + boss.getName() + " fainted!");
    boss = bosses[level];
    level ++;
    repaint();
    
    if (level == 6)
      JOptionPane.showMessageDialog(null, "Your Pikachu has learned to control his attacks a little bit! His projectiles will move according to your position for a while!");
    
    //empty old ArrayLists
    projectiles = new ArrayList <Projectile>();
    items = new ArrayList <Item>();
    JOptionPane.showMessageDialog(null, "Pokemon trainer Red sent out "+ boss.getName() + "\nRound " + level + " : " +  player.getName() + " Vs. " + boss.getName() + "\n" + player + "\n" + boss);
  }//end nextLevel
  
  /*
   gameOver
   @Post-condition: adds a score to scores, writes data to files (bosses and scores), asks the user to search, displays leaderboard
   */
  public void gameOver () {
    //add a new score to the array of scores
    if (!gameBeaten)
      scores[scores.length - 1] = new Score (player.getName(), points, level - 1);
    
    else
      scores[scores.length - 1] = new Score (player.getName(), points, level);
    
    sort(); //sort scores
    
    //write scores to a text file and all the bosses
    try {  
      PrintWriter output = new PrintWriter("scores.txt");
      
      for (int i = 0; i < scores.length; i++) {
        output.println(scores[i].getName());
        output.println(scores[i].getPoints());
        output.println(scores[i].getLevels());
      }//end for
      
      output.close();
      
      output = new PrintWriter("Previous Battle.txt");
      
      output.println(player);
      output.println("\nOpponant's Party: ");
      
      for (int i = 0; i < bosses.length; i++)
        output.println(bosses[i]);
      
      output.close();
    }//end try
    catch (FileNotFoundException e) {}//end catch
    
    int count = 0;
    
    //display the leaderboard
    String leaderboard = "Leaderboard:\n";
    for (Score curr : scores) {
      count++;
        leaderboard += curr;
      if (count == 10)
        break;
    }//end for
    
    JOptionPane.showMessageDialog(null, leaderboard);
    
    //ask the user if they want to search the leaderboards, even if the score is not in the top 10
    String input = JOptionPane.showInputDialog("Would you like to search the leaderboards?\nOnly the top 10 players are shown!\nType name, points or don't search (type 'no')");
    while (input == null || (!input.equalsIgnoreCase("name") && !input.equalsIgnoreCase("points") && !input.equalsIgnoreCase("no")))
      input = JOptionPane.showInputDialog("Would you like to search the leaderboards?\nOnly the top 10 players are shown!\nType name, points or don't search (type 'no')");
    
    if (input.equalsIgnoreCase("name")) {
      input = JOptionPane.showInputDialog("Type in a name to search.");
      
      while (input == null)
        input = JOptionPane.showInputDialog("Type in a name to search.");
      
      search(input); //search by name if the user says name
    }// end if
    
    else if(input.equalsIgnoreCase("points")) {
      int numInput = Integer.parseInt(JOptionPane.showInputDialog("Type in points to search. It must be divisible by 10."));
      
      while (input == null || numInput < 0 || numInput % 10 != 0)
        numInput = Integer.parseInt(JOptionPane.showInputDialog("Type in points to search. It must be divisible by 10."));
      
      search(numInput); //search by points if user says points
    }// end if
    
    JOptionPane.showMessageDialog(null, "Thanks for playing!");
    
    System.exit(0); //close the program
  }//end gameOver
  
  /*
   sort
   @Post-condition: sorts the scores array according to points in descending order
   */
  public void sort() {
    int i, j;
    Score currScore;
    
    for (i = 1; i < scores.length; i++) {
      currScore = scores[i];
      j = i;
      
      while (j > 0 && scores[j-1].getPoints() < currScore.getPoints()) {
        scores[j] = scores[j -1];
        j--;
      }//end while
      scores[j] = currScore;
    }//end for
  }//end sort
  
  /*
   search
   @param points number of points to be searched
   @Pre-condition scores must have at least one Score
   @Post-condition: searches the scores array for the specified points
   */
  public void search (int points) {
    int left = 0, right = scores.length, mid = 0;
    boolean found = false;
    
    //use binary search to look for certain points
    while (left != right - 1) {
      mid = (left + right) / 2;
      if (scores[mid].getPoints() > points) left = mid;
      else if (scores[mid].getPoints() < points) right = mid;
      else {
        found = true;
        left = right-1;
      }//end else
    }//end while
    
    if (found) JOptionPane.showMessageDialog(null, scores[mid]);
    else JOptionPane.showMessageDialog(null, "Sorry, not found!");
  }//end search
  
  /*
   search
   @param name name to search
   @Pre-condition scores must have at least one Score
   @Post-condition: searches the scores array for the specified name
   */
  public void search (String name) {
    int i;
    boolean found = false;
    
    //use linear search to search by name
    for (i = 0; i < scores.length; i++) {
      if (scores[i].getName().equalsIgnoreCase(name)) {
        found = true;
        break;
      }//end if
    }//end for
    
    if (found)
      JOptionPane.showMessageDialog(null, "You are number " + (i + 1) + ":  " + scores[i]);
    else
      JOptionPane.showMessageDialog(null, "Sorry, not found!");
  }//end search
}//end class