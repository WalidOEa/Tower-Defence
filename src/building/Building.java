package building;

//Imports
import bugs.Bug;
import bugs.ConcurrentModificationBug;
import bugs.NoneTerminationBug;
import bugs.NullPointerBug;
import java.util.ArrayList;
import java.util.Arrays;

public class Building {

  /*
  This creates a building object and is responsible for holding bugs in the building and
  outside the building.
   */

  //Variables
  private final int topFloor;
  private int constructionPoints;
  static ArrayList<Bug> bugArrayList = new ArrayList<>();

  //Constructors
  public Building(int topFloor, int constructionPoints) {
    this.topFloor = topFloor;
    this.constructionPoints = constructionPoints;
  }

  //Accessors
  public int getTopFloor() {
    return topFloor;
  }

  public int getConstructionPoints() {
    return constructionPoints;
  }

  //Methods
  //Arraylist contains all bugs; inside and outside the building, bug[] contains bugs within the building
  public static Bug[] getAllBugs() {
    ArrayList<Bug> bufferArrayList = new ArrayList<>();
    for (Bug bugObj : bugArrayList) {
      if (bugObj.getCurrentFloor() == 0) {
        bufferArrayList.add(bugObj);
      }
    }
    Bug[] Bug = new Bug[bufferArrayList.size()]; //Cast to an array of size buffer array list
    Bug = bufferArrayList.toArray(Bug);
    Arrays.sort(Bug); //Sort bugs in array by currentFloor then currentSteps
    return Bug;
  }

  /*
  These methods are static as no object instance will be created before method call
   */
  public static int addBug(Bug bug) {
    if (bugArrayList.contains(bug)) {
      return -1;
    } else {
      bugArrayList.add(bug);
      return bugArrayList.size();
    }
  }

  public static void removeBug(Bug bug) {
    bugArrayList.remove(bug);
  }

  public void bugsMove() {
    boolean flagMove = true;
    while (constructionPoints > 0) {
      if (flagMove) {
        for (Bug bugObj : bugArrayList) {
          if (bugObj.getCurrentFloor() != topFloor) {
            bugObj.move();
          } else if (bugObj.getCurrentFloor() == topFloor) {
            if (bugObj instanceof ConcurrentModificationBug) {
              constructionPoints -= 2;
              removeBug(bugObj);
            } else if (bugObj instanceof NoneTerminationBug) {
              constructionPoints -= 4;
              removeBug(bugObj);
            } else if (bugObj instanceof NullPointerBug) {
              constructionPoints -= 1;
              removeBug(bugObj);
            }
          }
        }
        flagMove = false;
      } else {
        break;
      }
    }
    if (constructionPoints == 0) {
      System.out.println("""
            _____                         ____                \s
           / ____|                       / __ \\               \s
          | |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __\s
          | | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__|
          | |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |  \s
           \\_____|\\__,_|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|  \s""");
      System.out.println("The bugs destroyed the ECS building!");
      System.exit(0);
    }
  }
}