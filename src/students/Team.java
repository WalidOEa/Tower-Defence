package students;

//Imports
import building.Building;
import java.util.ArrayList;
import java.util.Random;

public class Team {

  //Variables
  public static int knowledgePoints;
  private int newStudentCost = 100;
  private int upgradeStudent = 25;
  ArrayList<Student> studentArrayList = new ArrayList<>();

  //Constructor
  public Team(int knowledgePoints) {
    Team.knowledgePoints = knowledgePoints;
  }

  //Accessors
  public static int getKnowledgePoints() {
    return knowledgePoints;
  }

  public ArrayList<Student> getStudents() {
    return studentArrayList;
  }

  public int getNewStudentCost() {
    return newStudentCost;
  }

  public int getUpgradeStudent() {
    return upgradeStudent;
  }

  //Mutator
  public void setNewStudentCost(int newStudentCost) { this.newStudentCost = newStudentCost; }

  //Methods
  //Used an arraylist instead of an array, more convenient in adding new students
  public ArrayList<Student> addStudents(Student student) {
    studentArrayList.add(student);
    return studentArrayList; //Arraylist contains students defending the building
  }

  public void studentsAct(Building building) {
    for (Student student : studentArrayList) {
      student.defence(building);
    }
  }

  public int recruitNewStudent() throws ArithmeticException {
    try {
      if (newStudentCost > knowledgePoints) {
        throw new ArithmeticException();
      } else {
        knowledgePoints -= newStudentCost;
        newStudentCost += 10;
        int randInt = new Random().nextInt(100); //25% chance
        if (randInt <= 25) {
          Student student = new SeStudent(1);
          addStudents(student);
        } else if (randInt <= 50) {
          Student student = new AiStudent(1);
          addStudents(student);
        } else if (randInt <= 75) {
          Student student = new CsStudent(1);
          addStudents(student);
        } else {
          Student student = new CyberStudent(1);
          addStudents(student);
        }
        return 1;
      }
    } catch (ArithmeticException e) {
      System.out.println("Not enough knowledge points!");
      return -1;
    }
  }

  public void upgrade(Student student) throws ArithmeticException {
    try {
      if (upgradeStudent + student.upgradeCost() > knowledgePoints) {
        throw new ArithmeticException();
      } else {
        student.setLevel(student.getLevel() + 1);
        knowledgePoints -= (upgradeStudent + student.upgradeCost());
      }
    } catch (ArithmeticException e) {
      System.out.println("Not enough knowledge points!");
    }
  }
}