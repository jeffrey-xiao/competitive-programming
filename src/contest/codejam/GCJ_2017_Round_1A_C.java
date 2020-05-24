package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2017_Round_1A_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  static long hd, ad, hk, ak, b, d;
  static long bestAttackTurns, turns, currHd, currAk;
  static long MAX_VALUE = (long)1e18;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      hd = readInt();
      ad = readInt();
      hk = readInt();
      ak = readInt();
      b = readInt();
      d = readInt();

      // Calculate optimal number of buffs.
      bestAttackTurns = Integer.MAX_VALUE;
      for (int i = 0;; i++) {
        long currAttackTurns = i + (hk + ad + i * b - 1) / (ad + i * b);
        if (currAttackTurns <= bestAttackTurns) {
          bestAttackTurns = currAttackTurns;
        } else {
          break;
        }
      }

      if (ak - d >= hd) {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
        continue;
      }

      long minTurns = solveFast();
      if (minTurns == MAX_VALUE) {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
      } else {
        out.printf("Case #%d: %d%n", t, minTurns);
      }
    }

    out.close();
  }

  static long solveFast() {
    // Compute important number of times to debuff.
    ArrayList<Long> debuffThreshold = new ArrayList<>();
    long prevThreshold = -1;
    for (int i = 2;; i++) {
      long currThreshold = (hd + i - 1) / i - 1;
      if (currThreshold != prevThreshold) {
        if (currThreshold < ak) {
          debuffThreshold.add(ak - currThreshold);
        }
      } else {
        for (currThreshold--; currThreshold >= 0; currThreshold--) {
          if (currThreshold < ak) {
            debuffThreshold.add(ak - currThreshold);
          }
        }
        break;
      }
      prevThreshold = currThreshold;
    }

    turns = 0;
    currHd = hd;
    currAk = ak;
    long minTurns = killKnight(currHd, currAk, bestAttackTurns);
    if (d != 0) {
      long currDebuffs = 0;
      for (int i = 0; i < debuffThreshold.size(); i++) {
        if (currDebuffs >= debuffThreshold.get(i)) {
          continue;
        }

        // Debuff just before next important number quickly. Then manually debuff once.
        long numDebuffs = (debuffThreshold.get(i) - currDebuffs + d - 1) / d - 1;
        debuffKnight(numDebuffs);
        debuffKnightOnce();
        currDebuffs += (numDebuffs + 1) * d;
        minTurns = Math.min(minTurns, turns + killKnight(currHd, currAk, bestAttackTurns));
      }
    }
    return minTurns;
  }

  static boolean debuffKnight(long numDebuffs) {
    // Impossible if already dead.
    if (currHd <= 0) {
      return false;
    }
    // Exhaust our current hp.
    while (currAk - d < currHd && numDebuffs > 0) {
      currAk = Math.max(0, currAk - d);
      currHd -= currAk;
      numDebuffs--;
      turns++;
    }
    if (numDebuffs == 0) {
      return true;
    }
    // Finishing the rest of the debuffs.
    turns += numDebuffs;
    // All the remaining debuffs should exhaust our hp in the same number of hits.
    long currTurns = (hd + currAk - 1) / currAk - 2;
    if (currTurns == 0) {
      return false;
    }
    turns += numDebuffs / currTurns;
    currAk -= numDebuffs * d;
    long remainder = numDebuffs % currTurns;
    if (remainder == 0) {
      remainder = currTurns;
    } else {
      turns++;
    }
    currHd = hd - (currAk + currAk + remainder * d) * (remainder + 1) / 2;
    return true;
  }

  static boolean debuffKnightOnce() {
    if (currAk - d >= currHd) {
      currHd = hd - currAk;
      turns++;
    }
    currAk = Math.max(0, currAk - d);
    currHd -= currAk;
    turns++;
    if (currHd < 0) {
      return false;
    }
    return true;
  }

  static long killKnight(long currHd, long currAk, long bestAttackTurns) {
    // Impossible if already dead.
    if (currHd <= 0) {
      return MAX_VALUE;
    }
    // Can always attack.
    if (currAk == 0) {
      return bestAttackTurns;
    }
    long currTurns = (currHd + currAk - 1) / currAk;
    // Can finish before they kill us.
    if (currTurns >= bestAttackTurns) {
      return bestAttackTurns;
    }
    long turns = bestAttackTurns;
    bestAttackTurns -= currTurns - 1;
    currTurns = (hd - 1) / currAk;
    turns++;
    if (currTurns <= 1) {
      return MAX_VALUE;
    }
    turns += (bestAttackTurns - 2) / (currTurns - 1);
    return turns;
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
