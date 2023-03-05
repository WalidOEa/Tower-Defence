package bugs;

//Imports
import building.Building;
import students.Team;

public class Bug implements Comparable<Bug> {

  /*
  Implemented Comparable<> interface to sort bugs of an array list in ascending current floor,
  descending current steps in the building class.
   */

  //Variables
  private final String name;
  private final int baseHp;
  private int baseSteps;
  private final int level;
  private int currentHp;
  private int currentSteps;
  private int currentFloor;

  //Constructor
  public Bug(String name, int baseHp, int baseSteps, int level) {
    this.name = name;
    this.baseHp = baseHp;
    this.baseSteps = baseSteps;
    this.level = level;
    this.currentHp = (int) Math.round(baseHp * (Math.pow(level, 1.5)));
    this.currentFloor = -1;
  }

  //Overloaded constructor
  public Bug(String name, int baseHp, int baseSteps, int level, int initialSteps) {
    this.name = name;
    this.baseHp = baseHp;
    this.baseSteps = baseSteps;
    this.level = level;
    this.currentSteps = initialSteps;
    this.currentHp = (int) Math.round(baseHp * (Math.pow(level, 1.5)));
    this.currentFloor = -1;
  }

  //Accessor methods
  public int getBaseSteps() {
    return baseSteps;
  }

  public int getLevel() {
    return level;
  }

  public int getCurrentHp() {
    return currentHp;
  }

  public int getCurrentSteps() {
    return currentSteps;
  }

  public int getCurrentFloor() {
    return currentFloor;
  }

  public String getName() {
    return name;
  }

  //Mutator methods
  public void setCurrentHp(int currentHp) {
    this.currentHp = currentHp;
  }

  public void setCurrentFloor(int currentFloor) {
    this.currentFloor = currentFloor;
  }

  //Methods
  public void move() {
    if (currentSteps > 0) { //Bug moves by 1 step
      currentSteps -= 1;
    } else if (currentSteps == 0){
      currentFloor += 1; //Bug moves onto next floor
      System.out.println("Bug: " + getName() + " has gone up another floor!");
      if ((baseSteps - 1) <= 0){
        currentSteps = baseSteps;
      } else {
        baseSteps -= 1;
        currentSteps = baseSteps;
      }
    }
  }

  public void damage(int amount) {
    if ((currentHp - amount) > 0) { //Bug is still 'alive'
      currentHp -= amount;
      System.out.println("\n");
      System.out.println(getName() + " has taken " + amount + " points of damage!");
    } else if ((currentHp - amount) <= 0) {
      currentHp = 0;
      Building.removeBug(this); //Bug has been removed, hence destroyed
      System.out.println("\n");
      System.out.println(getName() + " has been destroyed!");
      Team.knowledgePoints +=  level * 20;
    }
  }

  public int slowDown(int steps) {
    currentSteps += steps; //Increases bug steps
    System.out.println("\n");
    System.out.println(getName() + " has been slowed down!");
    return currentSteps;
  }

  @Override
  public int compareTo(Bug bugObj) { //Compares currentFloor then currentSteps
    int compare = Integer.valueOf(bugObj.currentFloor)
        .compareTo(currentFloor);
    if (compare == 0) {
      return Integer.valueOf(currentSteps).compareTo(bugObj.currentSteps);
    } else {
      return compare;
    }
  }
}
