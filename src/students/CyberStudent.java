package students;

//Imports
import bugs.Bug;
import building.Building;
import java.util.Random;

public class CyberStudent implements Student {

  //Variables
  private int level;
  private int delay = 1;

  //Constructor
  public CyberStudent(int level) {
    this.level = level;
  }

  //Setters
  public void setLevel(int level) {
    this.level = level;
  }

  //Getters
  public int getLevel() {
    return level;
  }

  public int getDelay() {
    return delay;
  }

  //Mutator
  public void setDelay(int delay) {
    this.delay = delay;
  }

  //Methods
  public int upgradeCost() {
    return (int)(100 * Math.pow(2, level));
  }

  public int defence(Building building) {
    Bug[] bugArray = building.getAllBugs();
    int baseAtk = (int) Math.round(7 * Math.pow(level, 1.2));

    if (bugArray.length == 0) {
      System.out.println("No bugs currently in the building!");
    }
    else {
      try {
        if (delay != 8) {
          bugArray[0].damage(baseAtk);
          delay += 1;
          System.out.println("\n");
          System.out.println("Cyber student used normal attack!");
          System.out.println("\n");
        } else {
          System.out.println("\n");
          System.out.println("Cyber student is setting up an antivirus!");
          System.out.println("\n");
          int randInt = new Random().nextInt(100);
          if ((level + 20) >= 50) {
            if (randInt <= 50) {
              Building.removeBug(bugArray[0]);
              Team.knowledgePoints += bugArray[0].getLevel() * 20;
              System.out.println("\n");
              System.out.println("Bug instantly removed!");
              System.out.println("\n");
            } else {
              bugArray[0].damage(baseAtk * 2);
              System.out.println("\n");
              System.out.println("Bug damaged for twice the amount!");
              System.out.println("\n");
            }
          } else {
            if (randInt <= (level + 20)) {
              Building.removeBug(bugArray[0]);
              System.out.println("\n");
              System.out.println("Bug instantly removed!");
              System.out.println("\n");
            } else {
              bugArray[0].damage(baseAtk * 2);
              System.out.println("\n");
              System.out.println("Bug damaged for twice the amount!");
              System.out.println("\n");
            }
          }
          delay = 1;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("No bugs currently!");
      }
    }
    return 0;
  }
}