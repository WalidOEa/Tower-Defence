package students;

//Imports
import bugs.Bug;
import building.Building;

//Implementing interface Student
public class AiStudent implements Student {

  //Variables
  private int level;
  private int delay = 1;

  //Constructor
  public AiStudent(int level) {
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

  public int upgradeCost() {
    return (int)(100 * Math.pow(2, level));
  }

  //Methods
  public int defence(Building building) {
    Bug[] bugArray = building.getAllBugs(); //Array represents all bugs in building
    int baseAtk = (int) Math.round(7 * Math.pow(level, 1.2));

    if (bugArray.length == 0) {
      System.out.println("No bugs currently in the building!");
    }
    else {
      try {
        if (delay != 7) {
          bugArray[0].damage(baseAtk);
          delay += 1;
          System.out.println("\n");
          System.out.println("AI student used normal attack!");
          System.out.println("\n");
        } else {
          try {
            for (int i = 0; i < 3; i++) {
              bugArray[i].damage(baseAtk);
            }
          } catch (ArrayIndexOutOfBoundsException e) {
            //There are less than 3 bugs remaining; just continue
          }
          System.out.println("\n");
          System.out.println("Ai student used the ability of machine learning!");
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
