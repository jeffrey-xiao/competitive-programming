package contest.ecoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class ECOO_2002_Stack_Print {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    for (int t = 5; t > 0; t--) {
      int n = readInt();
      Job[] jobs = new Job[n];
      for (int x = 0; x < n; x++)
        jobs[x] = new Job(next(), readInt(), next());

      int[] times = new int[3];
      times[0] = 0;
      times[1] = 0;
      times[2] = 0;
      Stack<Job> process = new Stack<Job>();
      int count = 0;
      int finished = 0;
      while (count < n || !process.isEmpty()) {
        if (process.isEmpty()) {
          process.push(jobs[count]);
          count++;
        }

        Job curr = process.pop();
        if (!lessThan(curr, times)) {
          times[0] = curr.hours;
          times[1] = curr.minutes;
          times[2] = curr.seconds;
        }

        times[2] += curr.taken;
        adjust(times);

        finished++;

        int hours = times[0] % 24;

        if (finished == 5)
          System.out.printf("job  5 completed at %02d:%02d:%02d for %s\n", hours, times[1], times[2], curr.name);
        else if (finished == n)
          System.out.printf("job %d completed at %02d:%02d:%02d for %s\n", n, hours, times[1], times[2], curr.name);

        while (count < n && lessThan(jobs[count], times)) {
          process.push(jobs[count]);
          count++;
        }
      }
      System.out.println();
    }
  }

  private static boolean lessThan(Job job, int[] times) {
    if (job.hours > times[0])
      return false;
    else if (job.hours < times[0])
      return true;
    else {
      if (job.minutes > times[1])
        return false;
      else if (job.minutes < times[1])
        return true;
      else {
        if (job.seconds >= times[2])
          return false;
      }
    }
    return true;
  }

  private static void adjust(int[] times) {
    if (times[2] >= 60) {
      times[1] += times[2] / 60;
      times[2] %= 60;
    }
    if (times[1] >= 60) {
      times[0] += times[1] / 60;
      times[1] %= 60;
    }
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

  static class Job {
    int hours;
    int minutes;
    int seconds;
    int taken;
    String name;

    Job(String time, int taken, String name) {
      hours = Integer.parseInt(time.substring(0, 2));
      minutes = Integer.parseInt(time.substring(2, 4));
      seconds = Integer.parseInt(time.substring(4, 6));
      this.taken = taken;
      this.name = name;
    }
  }
}
