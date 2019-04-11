package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CCC_2013_J5_S3 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int favTeam;
  static ArrayList<Game> gamesNeeded;
  static int count;

  public static void main(String[] args) throws IOException {
    gamesNeeded = new ArrayList<Game>();
    for (int x = 0; x < 4; x++) {
      for (int y = x + 1; y < 4; y++) {
        gamesNeeded.add(new Game(x, y));
      }
    }
    favTeam = readInt() - 1;
    int numOfGames = readInt();
    int[] scores = new int[4];
    for (int x = 0; x < numOfGames; x++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      int scoreA = readInt();
      int scoreB = readInt();
      if (scoreA > scoreB)
        scores[a] += 3;
      else if (scoreB > scoreA)
        scores[b] += 3;
      else {
        scores[a]++;
        scores[b]++;
      }
      gamesNeeded.remove(gamesNeeded.indexOf(new Game(a, b)));
    }
    compute(Arrays.copyOf(scores, scores.length), 0);
    System.out.println(count);
  }

  private static void compute(int[] scores, int i) {
    Game g = gamesNeeded.get(i);
    if (i == gamesNeeded.size() - 1) {
      scores[g.team1] += 3;
      check(scores);
      scores[g.team1] -= 3;

      scores[g.team2] += 3;
      check(scores);
      scores[g.team2] -= 3;

      scores[g.team1]++;
      scores[g.team2]++;
      check(scores);
      scores[g.team1]--;
      scores[g.team2]--;
    } else {
      scores[g.team1] += 3;
      compute(Arrays.copyOf(scores, scores.length), i + 1);
      scores[g.team1] -= 3;

      scores[g.team2] += 3;
      compute(Arrays.copyOf(scores, scores.length), i + 1);
      scores[g.team2] -= 3;

      scores[g.team1]++;
      scores[g.team2]++;
      compute(Arrays.copyOf(scores, scores.length), i + 1);
      scores[g.team1]--;
      scores[g.team2]--;
    }
  }

  private static void check(int[] scores) {
    int scoreOfFav = scores[favTeam];
    for (int x = 0; x < scores.length; x++)
      if (x != favTeam)
        if (scoreOfFav <= scores[x])
          return;
    count++;
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong() throws IOException {
    return Long.parseLong(next());
  }

  static int readInt() throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Game {
    int team1;
    int team2;

    Game(int team1, int team2) {
      this.team1 = team1;
      this.team2 = team2;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Game) {
        Game g = (Game)o;
        return team1 == g.team1 && team2 == g.team2;
      }
      return false;
    }
  }
}
