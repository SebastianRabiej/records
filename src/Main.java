public class Main {
  public static void main(String[] args) {
    records_first_step();
    switch_second_step();

    last_step();
  }

  private static void last_step() {
    //how java 21 is amazing -> połączenie dwóch porpzednich kroków
    var point1 = new Point2(2, 10);
    var point2 = new Point2(0, 10);
    var point3 = new Point2(-10, 10);
    System.out.println(pointsSwitch(point1));
    System.out.println(pointsSwitch(point2));
    System.out.println(pointsSwitch(point3));
  }

  record Point2(int x, int y) {} //TODO rename

  static String pointsSwitch(Point2 point) {
    return switch (point) {
      case Point2(int x, int _) when x>0 -> "x is higher than 0";
      case Point2(int x, int _) when x == 0  -> "x is equal than 0";
      case Point2(int x, int y) -> String.format("x: %d, y: %d", x, y);
      default        -> point.toString();
    };
  }

  private static void switch_second_step() {
    //show code of both methods, how they are different
    formatter(23);
    formatterPatternSwitch(23);
  }

  static String formatter(Object obj) {
    String formatted = "unknown";
    if (obj instanceof Integer i) {
      formatted = String.format("int %d", i);
    } else if (obj instanceof Long l) {
      formatted = String.format("long %d", l);
    } else if (obj instanceof Double d) {
      formatted = String.format("double %f", d);
    } else if (obj instanceof String s) {
      formatted = String.format("String %s", s);
    }
    return formatted;
  }

  static String formatterPatternSwitch(Object obj) {
    return switch (obj) {
      case Integer i -> String.format("int %d", i);
      case Long l    -> String.format("long %d", l);
      case Double d  -> String.format("double %f", d);
      case String s  -> String.format("String %s", s);
      default        -> obj.toString();
    };
  }

  private static void records_first_step() {
    //Before Java 21
    record Point(int x, int y) {}

    var point = new Point(2, 3);
    if (point instanceof Point p) {
      int x = p.x();
      int y = p.y();
      System.out.println("x: " + x);
      System.out.println("y: " + y);
    }

    //As of Java 21
    if (point instanceof Point(int x, int y)) {
      System.out.println("x: " + x);
      System.out.println("y: " + y);
    }
  }
}
