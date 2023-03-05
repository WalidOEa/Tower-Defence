//Imports
import bugs.ConcurrentModificationBug;
import bugs.NoneTerminationBug;
import bugs.NullPointerBug;
import building.Building;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import students.AiStudent;
import students.CsStudent;
import students.CyberStudent;
import students.SeStudent;
import students.Team;

public class EcsBuildingDefence {

  //Variables
  private static boolean flag = true;
  private static boolean flagReader = false;
  static String filename;
  static int i;
  static int lineNo;

  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);

    System.out.println("1 to start the simulation, 2 to load a save, 3 to quit");
    int input = scanner.nextInt();
    try {
      if (input == 1) {
        Building building = new Building(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        Team team = new Team(Integer.parseInt(args[3]));
        Battle battle = new Battle(team,
            building); //Creates new instances using arguments passed through command line

        try {
          filename = args[2];
          BufferedReader reader = new BufferedReader(new FileReader(args[2]));
          String currentLine;

          //Read lines in file
          while ((currentLine = reader.readLine()) != null) {
            String[] waveArray = parse(currentLine); //[Bug, Bug, Bug, Bug, ...]
            flag = false;
            for (String wave : waveArray) {
              String[] waveParseArray = parse(wave); //[Name, Type, Level, Steps]
              if (waveParseArray[1].equals("ConcurrentModificationBug")) {
                Building.addBug(new ConcurrentModificationBug(waveParseArray[0],
                    Integer.parseInt(waveParseArray[2]), Integer.parseInt(waveParseArray[3])));
              } else if (waveParseArray[1].equals("NullPointerBug")) {
                Building.addBug(
                    new NullPointerBug(waveParseArray[0], Integer.parseInt(waveParseArray[2]),
                        Integer.parseInt(waveParseArray[3])));
              } else if (waveParseArray[1].equals("NoneTerminationBug")) {
                Building.addBug(
                    new NoneTerminationBug(waveParseArray[0], Integer.parseInt(waveParseArray[2]),
                        Integer.parseInt(waveParseArray[3])));
              }
            }
            System.out.println("\n");
            System.out.println("A new wave of bugs have entered the building!");
            for (int i = 1; i < 8 * battle.building.getTopFloor();
                i++) { //Loops battle.building.getTopFloor()
              System.out.println("========================== " +
                  "TURN: " + i +
                  " ==========================");
              System.out.println("""





                  """);
              battle.step();
            }
            flag = true; //Switch parse functions
          }
          reader.close();
        } catch (FileNotFoundException Exception) {
          //File not found, this is fine, just continue
        }
      } else if (input == 2) {
          BufferedReader saveFileReader = new BufferedReader(new FileReader("save.txt"));
          String[] line = parse(saveFileReader.readLine());

          //Building
          Building building = new Building(Integer.parseInt(line[0]), Integer.parseInt(line[1]));

          line = parse(saveFileReader.readLine());

          //Team
          Team team = new Team(Integer.parseInt(line[0]));
          team.setNewStudentCost(Integer.parseInt(line[1]));

          line = parse(saveFileReader.readLine());
          //Student
          for (String student : line) {
            String[] parse = student.split(",");
            if (parse[0].equals("AiStudent")) {
              AiStudent Student = new AiStudent(Integer.parseInt(parse[1]));
              Student.setDelay(Integer.parseInt(parse[2]));
              team.addStudents(Student);
            } else if (parse[0].equals("CyberStudent")) {
              CyberStudent Student = new CyberStudent(Integer.parseInt(parse[1]));
              Student.setDelay(Integer.parseInt(parse[2]));
              team.addStudents(Student);
            } else if (parse[0].equals("SeStudent")) {
              SeStudent Student = new SeStudent(Integer.parseInt(parse[1]));
              Student.setDelay(Integer.parseInt(parse[2]));
              team.addStudents(Student);
            } else if (parse[0].equals("CsStudent")) {
              CsStudent Student = new CsStudent(Integer.parseInt(parse[1]));
              Student.setDelay(Integer.parseInt(parse[2]));
              team.addStudents(Student);
            }
          }

          line = parse(saveFileReader.readLine());

          //Bugs
          for (String bug : line) {
            String[] parse = bug.split(",");
            if (parse[0].equals("NoneTerminationBug")) {
              NoneTerminationBug bugObj = new NoneTerminationBug(parse[1],
                  Integer.parseInt(parse[2]),
                  Integer.parseInt(parse[4]));
              bugObj.setCurrentFloor(Integer.parseInt(parse[5]));
              bugObj.setCurrentHp(Integer.parseInt(parse[3]));
              building.addBug(bugObj);
            } else if (parse[0].equals("NullPointerBug")) {
              NullPointerBug bugObj = new NullPointerBug(parse[1], Integer.parseInt(parse[2]),
                  Integer.parseInt(parse[4]));
              bugObj.setCurrentFloor(Integer.parseInt(parse[5]));
              bugObj.setCurrentHp(Integer.parseInt(parse[3]));
              building.addBug(bugObj);
            } else if (parse[0].equals("ConcurrentModificationBug")) {
              ConcurrentModificationBug bugObj = new ConcurrentModificationBug(parse[1],
                  Integer.parseInt(parse[2]),
                  Integer.parseInt(parse[4]));
              bugObj.setCurrentFloor(Integer.parseInt(parse[5]));
              bugObj.setCurrentHp(Integer.parseInt(parse[3]));
              building.addBug(bugObj);
            }
          }

          //Battle
          Battle battle = new Battle(team, building);

          line = parse(saveFileReader.readLine());

          //Text file
          BufferedReader reader = new BufferedReader(new FileReader(line[0]));

          for (int i = 0; i < Integer.parseInt(line[1]); i++) {
            reader.readLine(); //Skip lines already read
          }

          String currentLine;
          while ((currentLine = reader.readLine()) != null) {
            String[] wave = parse(currentLine);
            flag = false;
            for (String bug : wave) {
              String[] bugObj = parse(bug);
              if (bugObj[1].equals("NullPointerBug")) {
                building.addBug(new NullPointerBug(bugObj[0], Integer.parseInt(bugObj[2]),
                    Integer.parseInt(bugObj[3])));
              } else if (bugObj[1].equals("ConcurrentModificationBug")) {
                building.addBug(
                    new ConcurrentModificationBug(bugObj[0], Integer.parseInt(bugObj[2]),
                        Integer.parseInt(bugObj[3])));
              } else if (bugObj[1].equals("NoneTerminationBug")) {
                building.addBug(new NoneTerminationBug(bugObj[0], Integer.parseInt(bugObj[2]),
                    Integer.parseInt(bugObj[3])));
              }

              int counter = Integer.parseInt(line[2]);
              System.out.println("");
              System.out.println("A new wave of bugs have entered the building!");
              for (int i = counter; i < 8 * battle.building.getTopFloor(); i++) {
                System.out.println("========================== " +
                    "TURN: " + i +
                    " ==========================");
                System.out.println("" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n");
                battle.step();
              }
            }
            flag = true;
          }
        } else {
          System.exit(0);
        }
      } catch (FileNotFoundException Exception) {
        //File not found, this is fine, just continue
      }
    }

  //Parses string
  public static String[] parse(String currentLine) {
    String[] parseArray;
    if (flag) {
      parseArray = currentLine.split(";");
    } else {
      parseArray = currentLine.split(",|[\\(||\\)]");
    }
    return parseArray;
  }
}
