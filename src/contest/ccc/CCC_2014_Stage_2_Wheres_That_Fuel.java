package contest.ccc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class CCC_2014_Stage_2_Wheres_That_Fuel {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    ArrayList<Integer[]> planets = new ArrayList<Integer[]>();
    int length = scan.nextInt();
    int startingPos = scan.nextInt();
    int startingGas = 0;
    for (int x = 0; x < length; x++) {
      int temp = scan.nextInt();
      int temp1 = scan.nextInt();
      if (x + 1 == startingPos) {
        startingGas = temp;
        continue;
      }
      if (temp < temp1)
        continue;
      planets.add(new Integer[]{temp - temp1, temp1});
    }
    Collections.sort(planets, new Comparator<Object>() {
      @Override
      public int compare(Object arg0, Object arg1) {
        if (((Integer[]) arg0)[1].intValue() < ((Integer[]) arg1)[1].intValue())
          return 1;
        else if (((Integer[]) arg0)[1].intValue() > ((Integer[]) arg1)[1].intValue())
          return -1;
        return 0;
      }
    });
    int numOfPlanets = 1;
    for (int x = planets.size() - 1; x >= 0; x--) {
      if (planets.get(x)[0] >= 0 && startingGas >= planets.get(x)[1]) {
        numOfPlanets++;
        startingGas += planets.get(x)[0];
      }

    }
    System.out.println(startingGas + "\n" + numOfPlanets);
  }
}
