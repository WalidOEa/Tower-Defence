package bugs;

public class NullPointerBug extends Bug {

  /*
  Class inherits from Bug
   */

  public NullPointerBug(String name, int level, int initialSteps) {
    super(name, 10, 2, level, initialSteps);
  }
}
