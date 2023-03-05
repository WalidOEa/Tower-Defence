package students;

import bugs.Bug;
import building.Building;

public class SeStudent implements Student {

  //Variables
  private int level;
  private int delay = 1;

  //Constructor
  public SeStudent(int level) {
    this.level = level;
  }

  //Setter
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
    int baseAtk = (int) Math.round(5 * Math.pow(level, 1.2));

    if (bugArray.length == 0) {
      System.out.println("No bugs currently in the building!");
    }
    else {
      if (delay != 6) {
        bugArray[0].damage(baseAtk);
        System.out.println("\n");
        System.out.println("Se student used normal attack!");
        System.out.println("\n");
        delay++;
      } else {
        try {
          for (int i = 0; i < 5; i++) {
            bugArray[i].slowDown(2);
            System.out.println("\n");
            System.out.println("Bugs slowed down!");
            System.out.println("\n");
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          //There are less than 5 bugs remaining; just continue
        }
        delay = 1;
      }
    }
    return 0;
  }
}