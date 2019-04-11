package contest.ccc;

import java.util.Scanner;

class CCC_2006_J5 {
  public static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    String input = scan.nextLine();
    String[] inputarr = input.split(" ");
    char config = inputarr[0].charAt(0);
    Othello_Board board = new Othello_Board(config);
    for (int x = 2; x < (Integer.parseInt(inputarr[1]) * 2) + 2; x = x + 2)
      board.move(Integer.parseInt(inputarr[x]), Integer.parseInt(inputarr[x + 1]));
    board.countPieces();
    System.out.println(board.getnumOfBlack() + " " + board.getnumOfWhite());
  }

  static class Othello_Board {
    private int[][] array = {{0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}};
    ;
    private char config;
    private int numOfBlack;
    private int numOfWhite;
    private boolean blackToMove;
    private int[] movex = {-1, -1, -1, 0, 0, 1, 1, 1};
    private int[] movey = {-1, 0, 1, -1, 1, -1, 0, 1};

    Othello_Board(char config) {
      blackToMove = true;
      this.config = config;
      setConfig();
    }

    private void setConfig() {
      if (config == 'a') {
        array[3][3] = 2;
        array[3][4] = 1;
        array[4][3] = 1;
        array[4][4] = 2;
      } else if (config == 'b') {
        for (int x = 0; x < 8; x++) {
          array[x][x] = 1;
          int whiteCounter = 7 - x;
          array[x][whiteCounter] = 2;
        }
      } else {
        for (int x = 0; x < 8; x++) {
          array[x][2] = 1;
          array[x][3] = 1;
          array[x][4] = 2;
          array[x][5] = 2;
        }
      }
    }

    public void move(int x, int y) {
      x--;
      y--;
      int pieceNum = blackToMove ? 2 : 1;
      int sameNum = !blackToMove ? 2 : 1;
      for (int a = 0; a < 8; a++) {
        int ymove = 0;
        int xmove = 0;
        while (x + movex[a] + xmove >= 0 && x + movex[a] + xmove <= 7 && y + movey[a] + ymove >= 0 && y + movey[a] + ymove <= 7 && array[x + movex[a] + xmove][y + movey[a] + ymove] == pieceNum) {
          ymove += movey[a];
          xmove += movex[a];
        }

        if ((x + movex[a] + xmove) < 0 || (x + movex[a] + xmove) > 7 || (y + movey[a] + ymove) < 0 || (y + movey[a] + ymove) > 7) {
          continue;
        }
        if (array[x + movex[a] + xmove][y + movey[a] + ymove] != 0 && array[x + movex[a] + xmove][y + movey[a] + ymove] != pieceNum) {
          while (ymove != 0 || xmove != 0) {
            if (ymove != 0)
              ymove -= movey[a];
            if (xmove != 0)
              xmove -= movex[a];
            array[x + movex[a] + xmove][y + movey[a] + ymove] = sameNum;
          }
          array[x][y] = sameNum;
        }
      }
      blackToMove = blackToMove ? false : true;
      pieceNum = blackToMove ? 2 : 1;
      sameNum = !blackToMove ? 2 : 1;
    }

    public void countPieces() {
      numOfBlack = 0;
      numOfWhite = 0;
      for (int x = 0; x < 8; x++) {
        for (int y = 0; y < 8; y++) {
          if (array[x][y] == 1) {
            numOfBlack++;
          } else if (array[x][y] == 2) {
            numOfWhite++;
          }
        }
      }
    }

    public int getnumOfBlack() {
      return numOfBlack;
    }

    public void setnumOfBlack(int numOfBlack) {
      this.numOfBlack = numOfBlack;
    }

    public int getnumOfWhite() {
      return numOfWhite;
    }

    public void setnumOfWhite(int numOfWhite) {
      this.numOfWhite = numOfWhite;
    }

    public void printArray() {
      for (int x = 0; x < 8; x++) {
        for (int y = 0; y < 8; y++) {
          System.out.print(array[x][y]);
        }
        System.out.println();
      }
    }
  }
}