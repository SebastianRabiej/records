public class Main {
  public static void main(String[] args) {
    records_first_step();
    switch_second_step();

    last_step();
  }

  private static void last_step() {
    //how java 21 is amazing -> połączenie dwóch porpzednich kroków
    var point1 = new Point(2, 10);
    var point2 = new Point(0, 10);
    var point3 = new Point(-10, 10);
    System.out.println(pointsSwitch(point1));
    System.out.println(pointsSwitch(point2));
    System.out.println(pointsSwitch(point3));
  }

  record Point(int x, int y) {} //TODO rename
  record Line(int x, int y, int length) {} //TODO rename

  static String pointsSwitch(Point point) {
    return switch (point) {
      case Point(int x, int _) when x>0 -> "x is higher than 0";
      case Point(int x, int y) -> "x:" + x + "y:" + y;
      default        -> "Something went wrong";
    };
  }

  private static void switch_second_step() {
    //show code of both methods, how they are different
    System.out.println(formatter(new Point(2, 10)));
    System.out.println(formatterPatternSwitch(new Point(2, 10)));
  }

  static String formatter(Object obj) {
    if (obj instanceof Point p) {
      return "I'm a point!";
    } else if (obj instanceof Line l) {
      return "I have length: " + l.length();
    } else {
      return "I'm' nothing";
    }
  }

  static String formatterPatternSwitch(Object obj) {
    return switch (obj) {
      case Point p -> "I'm a point!";
      case Line l  -> "I have length: " + l.length();
      default      -> "I'm' nothing";
    };
  }

  private static void records_first_step() {
    //Before Java 21
    record Point2(int x, int y) {}

    var point = new Point2(2, 3);
    if (point instanceof Point2 p) {
      int x = p.x();
      int y = p.y();
      System.out.println("x: " + x);
      System.out.println("y: " + y);
    }

    //As of Java 21
    if (point instanceof Point2(int x, int y)) {
      System.out.println("x: " + x);
      System.out.println("y: " + y);
    }
  }
}
