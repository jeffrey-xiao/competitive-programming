package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class CCC_1999_Stage_2_Sum_of_Products {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    for (int qq = 0; qq < n; qq++) {
      StringBuilder in = new StringBuilder(readLine());
      for (int i = in.length() - 1; i >= 1; i--) {
        if ((in.charAt(i - 1) == ')' && in.charAt(i) == '(') || (Character.isAlphabetic(in.charAt(i - 1)) && in.charAt(i) == '(') || (in.charAt(i - 1) == ')' && Character.isAlphabetic(in.charAt(i))) || (Character.isAlphabetic(in.charAt(i - 1)) && Character.isAlphabetic(in.charAt(i))))
          in.insert(i, '*');
      }
      LinkedList<Term> st = new LinkedList<Term>();
      LinkedList<Character> op = new LinkedList<Character>();
      for (int i = 0; i < in.length(); i++) {
        char c = in.charAt(i);
        if (c == '(')
          op.add('(');
        else if (c == ')') {
          while (op.getLast() != '(')
            operate(st, op.removeLast());
          op.removeLast();
        } else if (isOperator(c)) {
          while (!op.isEmpty() && getPriority(c) <= getPriority(op.getLast()))
            operate(st, op.removeLast());
          op.add(c);
        } else {
          Term add = new Term();
          add.var.add("" + c);
          st.add(add);
        }
      }
      while (!op.isEmpty())
        operate(st, op.removeLast());
      for (int i = 0; i < st.getFirst().var.size(); i++) {
        char[] c = st.getFirst().var.get(i).toCharArray();
        Arrays.sort(c);
        st.getFirst().var.set(i, new String(c));
      }
      Collections.sort(st.getFirst().var);
      for (int i = 0; i < st.getFirst().var.size(); i++) {
        out.print(st.getFirst().var.get(i) + (i < st.getFirst().var.size() - 1 ? "+" : ""));
      }
      out.println();
    }

    out.close();
  }

  private static boolean isOperator (char c) {
    return c == '+' || c == '*';
  }

  private static void operate (LinkedList<Term> st, char op) {
    Term r = st.removeLast();
    Term l = st.removeLast();
    Term add = new Term();
    if (op == '+') {
      add.var.addAll(l.var);
      add.var.addAll(r.var);
      st.add(add);
    } else {
      for (String s1 : l.var) {
        for (String s2 : r.var) {
          add.var.add(s1 + s2);
        }
      }
      st.add(add);
    }

  }

  private static int getPriority (char op) {
    if (op == '+')
      return 1;
    else if (op == '*')
      return 2;
    else
      return -1;
  }

  static class Term {
    ArrayList<String> var;

    Term () {
      var = new ArrayList<String>();
    }
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
