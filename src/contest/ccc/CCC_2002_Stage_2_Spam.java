package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CCC_2002_Stage_2_Spam {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));
    int s = readInt();
    int n = readInt();
    int c = readInt();
    HashMap<String, Integer> spam = new HashMap<String, Integer>();
    HashMap<String, Integer> nonspam = new HashMap<String, Integer>();
    HashMap<String, Integer> message = new HashMap<String, Integer>();
    while (s > 0 || n > 0 || c > 0) {
      String next = readLine();
      while (!next.equals("ENDMESSAGE")) {
        for (int i = 0; i <= next.length() - 3; i++) {
          if (s > 0)
            add(spam, next.substring(i, i + 3));
          else if (n > 0)
            add(nonspam, next.substring(i, i + 3));
          else
            add(message, next.substring(i, i + 3));
        }
        next = readLine();
      }

      if (s > 0)
        s--;
      else if (n > 0)
        n--;
      else {
        double spamMeasure = getSim(message, spam);
        double nonspamMeasure = getSim(message, nonspam);
        spamMeasure = Math.round(spamMeasure * 1000000.0);
        if (spamMeasure % 10 == 5)
          spamMeasure -= 5;
        nonspamMeasure = Math.round(nonspamMeasure * 1000000.0);
        if (nonspamMeasure % 10 == 5)
          nonspamMeasure -= 5;
        System.out.printf("%.5f %.5f\n", spamMeasure / 1000000.0, nonspamMeasure / 1000000.0);
        if (spamMeasure > nonspamMeasure)
          System.out.println("spam");
        else
          System.out.println("non-spam");
        message.clear();
        c--;
      }
    }
  }

  static double getSim(HashMap<String, Integer> m1, HashMap<String, Integer> m2) {
    double cos = 0;
    double a = 0;
    double b = 0;
    for (Map.Entry<String, Integer> e : m1.entrySet()) {
      if (m2.get(e.getKey()) != null)
        cos += m2.get(e.getKey()) * e.getValue();
      a += e.getValue() * e.getValue();
    }
    for (Map.Entry<String, Integer> e : m2.entrySet()) {
      b += e.getValue() * e.getValue();
    }
    return cos / Math.sqrt(a * b);
  }

  static void add(HashMap<String, Integer> hm, String s) {
    if (!hm.containsKey(s))
      hm.put(s, 0);
    hm.put(s, hm.get(s) + 1);
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