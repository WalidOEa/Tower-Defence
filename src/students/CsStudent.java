package students;

//Imports
import bugs.Bug;
import building.Building;

public class CsStudent implements Student {

  //Variables
  private int level;
  private int delay = 1;

  //Constructors
  public CsStudent(int level) {
    this.level = level;
  }

  //Setter
  public void setLevel(int level) {
    this.level = level;
  }

  //Getter
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
    int baseAtk = (int) Math.round(6 * Math.pow(level, 1.2));
    if (bugArray.length == 0) {
      System.out.println("No bugs currently in the building!");
    }
    else {
      try {
        if (delay != 6) {
          bugArray[0].damage(baseAtk);
          delay += 1;
          System.out.println("\n");
          System.out.println("Cs student used normal attack!");
          System.out.println("\n");
        } else {
          bugArray[0].damage(baseAtk * 4);
          System.out.println("\n");
          System.out.println("Cs student used the ability of pair programming!");
          System.out.println("\n");
          delay = 1;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("No bugs currently!");
      }
    }
    return 0;
  }
}