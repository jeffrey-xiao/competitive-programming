package contest.woburn;

//checks the number of objects in a room, 1-9 is occupied space and 0 is empty space
import java.util.Scanner;

public class Woburn_Challenge_1999_Mission_Impossible_2 {
  static Scanner scan = new Scanner(System.in);

  public static void main (String[] args) {
    int a = scan.nextInt();
    int b = scan.nextInt();
    while (a != -1) {
      scan.nextLine();
      char[][] room = new char[b][];
      for (int x = 0; x < b; x++)
        room[x] = scan.nextLine().toCharArray();
      int count = 0;
      for (int x = 0; x < room.length; x++)
        for (int y = 0; y < room[x].length; y++)
          if (room[x][y] != '0') {
            count++;
            removeObject(room, x, y);
          }
      System.out.println(count);
      a = scan.nextInt();
      b = scan.nextInt();
    }
  }

  private static void removeObject (char[][] room, int x, int y) {
    if (x < 0 || y < 0 || x >= room.length || y >= room[x].length || room[x][y] == '0')
      return;
    room[x][y] = '0';

    removeObject(room, x + 1, y);
    removeObject(room, x - 1, y);
    removeObject(room, x, y + 1);
    removeObject(room, x, y - 1);

  }

}
