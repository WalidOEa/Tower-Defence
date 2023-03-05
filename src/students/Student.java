package students;

//Imports
import building.Building;

public interface Student {

  //Setter
  void setLevel(int level);

  //Getter
  int getLevel();

  int getDelay();

  //Setter
  void setDelay(int delay);

  //Methods
  int upgradeCost();

  int defence(Building building);

}