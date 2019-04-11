package contest.ccc;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

class CCC_2011_J4 {
  public static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    ArrayList<Point> positions = new ArrayList<Point>();
    // inputs
    int previousIndex = 0;
    // addAll adds items in backwards order
    positions.add(new Point(0, -1));
    positions.add(new Point(0, -3));
    positions.add(new Point(3, -3));
    positions.add(new Point(3, -5));
    positions.add(new Point(5, -5));
    positions.add(new Point(5, -3));
    positions.add(new Point(7, -3));
    positions.add(new Point(7, -7));
    positions.add(new Point(-1, -7));
    positions.add(new Point(-1, -5));
    ArrayList<Point> totalPositions = new ArrayList<Point>();
    totalPositions.addAll(positions);
    for (int x = 0; x < positions.size(); x++) {
      if (positions.get(x).getX() > positions.get(previousIndex).getX()) {
        for (int i = (int) positions.get(previousIndex).getX() + 1; i < positions.get(x).getX(); i++) {
          totalPositions.add(new Point(i, (int) positions.get(x).getY()));
        }
        previousIndex++;
      } else if (positions.get(x).getX() < positions.get(previousIndex).getX()) {
        for (int j = (int) positions.get(previousIndex).getX() - 1; j > positions.get(x).getX(); j--) {
          totalPositions.add(new Point(j, (int) positions.get(x).getY()));
        }
        previousIndex++;
      } else if (positions.get(x).getY() > positions.get(previousIndex).getY()) {
        for (int j = (int) positions.get(previousIndex).getY() + 1; j > positions.get(x).getY(); j++) {
          totalPositions.add(new Point((int) positions.get(x).getX(), j));
        }
        previousIndex++;
      } else if (positions.get(x).getY() < positions.get(previousIndex).getY()) {
        for (int j = (int) positions.get(previousIndex).getY() - 1; j > positions.get(x).getY(); j--) {
          totalPositions.add(new Point((int) positions.get(x).getX(), j));
        }
        previousIndex++;
      }
    }

    String input = scan.nextLine();
    int counter = 1;
    previousIndex = 9;
    String[] temp1 = {" ", " "};
    while (!temp1[0].equals("q")) {
      temp1 = input.split(" ");
      try {
        if (temp1[0].equals("d")) {
          positions.add(new Point((int) positions.get(previousIndex).getX(), (int) positions.get(previousIndex).getY() - Integer.parseInt(temp1[1])));
          totalPositions.add(new Point((int) positions.get(previousIndex).getX(), (int) positions.get(previousIndex).getY() - Integer.parseInt(temp1[1])));
          previousIndex++;
        } else if (temp1[0].equals("r")) {
          positions.add(new Point((int) positions.get(previousIndex).getX() + Integer.parseInt(temp1[1]), (int) positions.get(previousIndex).getY()));
          totalPositions.add(new Point((int) positions.get(previousIndex).getX() + Integer.parseInt(temp1[1]), (int) positions.get(previousIndex).getY()));
          previousIndex++;
        } else if (temp1[0].equals("l")) {
          positions.add(new Point((int) positions.get(previousIndex).getX() - Integer.parseInt(temp1[1]), (int) positions.get(previousIndex).getY()));
          totalPositions.add(new Point((int) positions.get(previousIndex).getX() - Integer.parseInt(temp1[1]), (int) positions.get(previousIndex).getY()));
          previousIndex++;
        } else if (temp1[0].equals("u")) {
          positions.add(new Point((int) positions.get(previousIndex).getX(), (int) positions.get(previousIndex).getY() + Integer.parseInt(temp1[1])));
          totalPositions.add(new Point((int) positions.get(previousIndex).getX(), (int) positions.get(previousIndex).getY() + Integer.parseInt(temp1[1])));
          previousIndex++;
        }
      } catch (Exception ex) {
      }
      for (int x = counter + 9; x < positions.size(); x++) {

        // X positions
        if (positions.get(x).getX() > positions.get(x - 1).getX()) {
          for (int i = (int) positions.get(x - 1).getX() + 1; i < positions.get(x).getX(); i++) {
            totalPositions.add(new Point(i, (int) positions.get(x).getY()));
          }
        } else if (positions.get(x).getX() < positions.get(x - 1).getX()) {
          for (int j = (int) positions.get(x - 1).getX() - 1; j > positions.get(x).getX(); j--) {
            totalPositions.add(new Point(j, (int) positions.get(x).getY()));
          }
          // Y positions
        } else if (positions.get(x).getY() > positions.get(x - 1).getY()) {

          for (int j = (int) positions.get(x - 1).getY() + 1; j > positions.get(x).getY(); j++) {
            totalPositions.add(new Point((int) positions.get(x).getX(), j));
          }
        } else if (positions.get(x).getY() < positions.get(x - 1).getY()) {
          for (int j = (int) positions.get(x - 1).getY() - 1; j > positions.get(x).getY(); j--) {
            totalPositions.add(new Point((int) positions.get(x).getX(), j));
          }
        }
        if (checkDanger(totalPositions)) {
          System.out.println((int) positions.get(x).getX() + " " + (int) positions.get(x).getY() + " DANGER");
          System.exit(0);
        } else {
          System.out.println((int) positions.get(x).getX() + " " + (int) positions.get(x).getY() + " safe");
        }

      }
      counter++;
      if (temp1[0].equals("q"))
        break;
      input = scan.nextLine();
    }
    // input done
  }

  public static boolean checkDanger(ArrayList<Point> a) {
    for (int b = 0; b < a.size(); b++) {
      for (int c = 0; c < a.size(); c++) {
        if (b != c) {
          if (a.get(b).equals(a.get(c))) {
            return true;
          }
        }
      }
    }
    return false;
  }
}