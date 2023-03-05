//Imports
import static students.Team.knowledgePoints;
import bugs.Bug;
import bugs.ConcurrentModificationBug;
import bugs.NoneTerminationBug;
import bugs.NullPointerBug;
import building.Building;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import students.AiStudent;
import students.CsStudent;
import students.CyberStudent;
import students.SeStudent;
import students.Student;
import students.Team;

public class Battle {

  //Variables
  Team team;
  Building building;
  static int counter = 1;
  int i = 1;

  //Constructors
  public Battle(Team team, Building building) {
    this.team = team;
    this.building = building;
  }

  //Methods
  public void manageTeam() {

    /* Strategy involves a 50/50 chance of recruiting or upgrading a student. This is only
    done if the knowledge points are available.
     */

      int randInt = new Random().nextInt(100);
      if (randInt <= 50) {
        recruit();
      } else {
        if (team.getStudents().isEmpty()) {
          recruit();
        } else if (knowledgePoints >= team.getNewStudentCost()) {
          if (team.getStudents().isEmpty()) {
            System.out.println("No students found to upgrade!");
          } else {
            int rand = new Random().nextInt(this.team.getStudents().size());
            this.team.upgrade(this.team.getStudents().get(rand));
            System.out.println("Student upgraded!");
          }
        }
      }
    }

  private void recruit() { //Recruits a new student
    if (knowledgePoints >= team.getNewStudentCost()) {
      team.recruitNewStudent();
      if (team.getStudents()
          .get(team.getStudents().size() - 1) instanceof CsStudent) { //Student placed at the end of student array list
        System.out.println("CS Student recruited!");
      } else if (team.getStudents()
          .get(team.getStudents().size() - 1) instanceof AiStudent) {
        System.out.println("AI Student recruited!");
      } else if (team.getStudents()
          .get(team.getStudents().size() - 1) instanceof CyberStudent) {
        System.out.println("Cyber student recruited!");
      } else if (team.getStudents()
          .get(team.getStudents().size() - 1) instanceof SeStudent) {
        System.out.println("SE Student recruited!");
      }
    }
  }

  public void save() {
    ArrayList<Student> studentArrayList = this.team.getStudents();
    Bug[] bugArray = this.building.getAllBugs();

    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt"));
      writer.write(this.building.getTopFloor() + ";" + this.building.getConstructionPoints() + "\n");
      writer.write(this.team.getKnowledgePoints() + ";" + this.team.getNewStudentCost() + "\n");
      for (Student student : studentArrayList) {
        if (student instanceof CsStudent) {
          writer.write("CsStudent" + "," + student.getLevel() + "," + student.getDelay() + ";");
        } else if (student instanceof CyberStudent) {
          writer.write("CyberStudent" + "," + student.getLevel() + "," + student.getDelay() + ";");
        } else if (student instanceof SeStudent) {
          writer.write("SeStudent" + "," + student.getLevel() + "," + student.getDelay() + ";");
        } else if (student instanceof AiStudent) {
          writer.write("AiStudent" + "," + student.getLevel() + "," + student.getDelay() + ";");
        }
      }
      writer.write("\n");
      for (Bug bug : bugArray) {
        if (bug instanceof ConcurrentModificationBug) {
          writer.write("ConcurrentModificationBug" + "," + bug.getName() + "," +  bug.getLevel() + "," +
              bug.getCurrentHp() +  "," + bug.getCurrentSteps() + "," +
              bug.getCurrentFloor() + ";");
        } else if (bug instanceof NullPointerBug) {
          writer.write("NullPointerBug" + "," + bug.getName() + "," + bug.getLevel() + "," +
              bug.getCurrentHp() +  "," + bug.getCurrentSteps() + "," +
              bug.getCurrentFloor() + ";");
        } else if (bug instanceof NoneTerminationBug) {
          writer.write("NoneTerminationBug" + "," + bug.getName() + "," +  bug.getLevel() + "," +
              bug.getCurrentHp() +  "," + bug.getCurrentSteps() + "," +
              bug.getCurrentFloor() + ";");
        }
      }
      writer.write("\n");
      writer.write(EcsBuildingDefence.filename + ";" + EcsBuildingDefence.lineNo + ";" + Integer.toString(counter));
      writer.close();
      i++;

    } catch (IOException e) {
      System.out.println("An error occurred!");
      e.printStackTrace();
    }
  }

  public void step() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("1 to continue, 2 to save, 3 to quit");
    int input = scanner.nextInt();
    if (input == 1) {
      try {
        System.out.println("=====================Manage Team=====================");
        this.manageTeam();
        System.out.println("\n");
        Thread.sleep(700);
        System.out.println("The bugs are moving!");
        this.building.bugsMove();
        System.out.println("\n");
        Thread.sleep(700);
        System.out.println("The students are now defending the building!");
        this.team.studentsAct(building);
        System.out.println("\n");
        Thread.sleep(700);
        System.out.println("=====================Students And Team Status=====================");
        System.out.println("The team contains: ");
        Thread.sleep(500);
        for (Student student : this.team.getStudents()) {
          if (student instanceof CsStudent) {
            System.out.println(
                "CS Student | Level : " + student.getLevel() + " | Delay: " + student.getDelay());
            System.out.println("""
                 O\s
                /|\\
                / \\""");
          } else if (student instanceof AiStudent) {
            System.out.println(
                "AI Student | Level : " + student.getLevel() + " | Delay: " + student.getDelay());
            System.out.println("""
                 O\s
                /|\\
                / \\""");
          } else if (student instanceof CyberStudent) {
            System.out.println(
                "Cyber student | Level : " + student.getLevel() + " | Delay: "
                    + student.getDelay());
            System.out.println("""
                 O\s
                /|\\
                / \\""");
          } else if (student instanceof SeStudent) {
            System.out.println(
                "SE Student | Level : " + student.getLevel() + " | Delay: " + student.getDelay());
            System.out.println("""
                 O\s
                /|\\
                / \\""");
          }
          Thread.sleep(500);
        }
        System.out.println("\n");
        Thread.sleep(1000);
        System.out.println("\n");
        System.out.println("Team's knowledge points: " + knowledgePoints);
        Thread.sleep(1000);
        System.out.println("\n");
        System.out.println("Team's recruiting costs: " + team.getNewStudentCost());
        Thread.sleep(1000);
        System.out.println("\n");
        Thread.sleep(5000);
        System.out.println("" +
            "" +
            "");

        System.out.println("=====================Building status=====================");
        System.out.println("Bugs in the building: ");
        for (Bug bug : Building.getAllBugs()) {
          if (bug instanceof NoneTerminationBug) {
            System.out.println(
                bug.getName() + ": None termination bug | Health: " + bug.getCurrentHp() +
                    "| Current floor: " + bug.getCurrentFloor() + " | Current steps: " + bug.getCurrentSteps());
            System.out.println("\n");
          } else if (bug instanceof NullPointerBug) {
            System.out.println(
                bug.getName() + ": Null pointer bug | Health: " + bug.getCurrentHp() +
                    "| Current floor: " + bug.getCurrentFloor() + " | Current steps: " + bug.getCurrentSteps());
            System.out.println("\n");
          } else if (bug instanceof ConcurrentModificationBug) {
            System.out.println(
                bug.getName() + ": Concurrent modification bug | Health: " + bug.getCurrentHp() +
                    "| Current floor: " + bug.getCurrentFloor() + " | Current steps: " + bug.getCurrentSteps());
            System.out.println("\n");
          }
          Thread.sleep(500);
        }
        System.out.println("""
                                                                                   \s
                                                         ((((c,               ,7))))
                                                        (((((((              ))))))))
                                                         (((((((            ))))))))
                                                          ((((((@@@@@@@@@@@))))))))
                                                           @@@@@@@@@@@@@@@@)))))))
                                                        @@@@@@@@@@@@@@@@@@))))))@@@@
                                                       @@/,:::,\\/,:::,\\@@@@@@@@@@@@@@
                                                       @@|:::::||:::::|@@@@@@@@@@@@@@@
                                                       @@\\':::'/\\':::'/@@@@@@@@@@@@@@
                                                        @@@@@@@@@@@@@@@@@@@@@@@@@@@
                                                          @@@@@@@@@@@@@@@@@@@@@@\\
                                                            /    \\        (     \\
                                                           (      )        \\     \\ 
                                                            \\    /          \\
               _________________________
              /////////////|\\\\\\\\\\\\\\\\\\\\\\\\\\
             '.-------------------------.'
              |                         |
              | [] [] [] [] [] [] [] [] |
              |                         |
            _.-.        _ _ _ _         |
            >   )] [] []||_||||[] [] [] |,'`\\
            `.,'________||___||_________|\\  <
             ||  /  _<> _     _    (_)<>\\ ||
             '' /<>(_),:/     \\:. <>'  <>\\||
             __::::::::/ _ _ _ \\:::::::::::_
            __________           ___________
               ,.::. /           \\  _________
               `''''/             \\ \\:'-'-'-'-
                   ||             || \\\\""".indent(3));
        //https://ascii.co.uk/art/bug
        //https://ascii.co.uk/art/building
        Thread.sleep(1000);
        System.out.println("\n");
        System.out.println("Construction points: " + this.building.getConstructionPoints());
        System.out.println("\n");
        Thread.sleep(6000);

        /*
        Prints out all information regarding the current state of the simulation
         */

      } catch (InterruptedException exception) {
        Thread.currentThread().interrupt();
      }
    } else if (input == 2) {
      save();
      System.out.println("Save successful!");
    } else {
      System.exit(0);
    }
  }
}