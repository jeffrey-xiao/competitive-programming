package contest.ioi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class IOI_2012_Crayfish_Scrivener {

  static FasterScanner fs;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    fs = new FasterScanner();
    // pr = new PrintWriter(new FileWriter("out.txt"));

    int n = fs.nextInt();
    Node root = new Node('\u0000', 0, null);
    Node[] state = new Node[n + 1];
    state[0] = root;
    int currState = 0;
    for (int i = 0; i < n; i++) {
      Character in = fs.nextChar();
      if (in == 'T') {
        char c = fs.nextChar();
        state[currState + 1] = add(state[currState], c);
        currState++;
      } else if (in == 'U') {
        int u = fs.nextInt();
        state[currState + 1] = state[currState - u];
        currState++;
      } else {
        int depth = fs.nextInt();
        pr.println(getCharacter(state[currState], depth + 1));
      }
    }
    pr.close();
  }

  static void printWord (Node n) {
    if (n.c == '\u0000')
      return;
    printWord(n.par[0]);
    System.out.print(n.c);
  }

  static char getCharacter (Node n, int depth) {
    Node curr = n;
    while (true) {
      if (curr.depth == depth)
        return curr.c;
      for (int i = 19; i >= 0; i--)
        if (curr.par[i] != null && curr.par[i].depth >= depth) {
          curr = curr.par[i];
          break;
        }
    }
  }

  static Node add (Node n, char c) {
    if (n.child[c - 'a'] == null)
      n.child[c - 'a'] = new Node(c, n.depth + 1, n);
    return n.child[c - 'a'];
  }

  static class Node {
    char c;
    int depth;
    Node[] par;
    Node[] child;

    Node (char c, int depth, Node parent) {
      this.c = c;
      this.depth = depth;
      child = new Node[26];
      par = new Node[20];
      par[0] = parent;
      for (int i = 1; i < 20; i++)
        if (par[i - 1] != null)
          par[i] = par[i - 1].par[i - 1];
    }
  }

  static class FasterScanner {
    private InputStream mIs;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public FasterScanner () {
      this(System.in);
    }

    public FasterScanner (InputStream is) {
      mIs = is;
    }

    public int read () {
      if (numChars == -1)
        throw new InputMismatchException();
      if (curChar >= numChars) {
        curChar = 0;
        try {
          numChars = mIs.read(buf);
        } catch (IOException e) {
          throw new InputMismatchException();
        }
        if (numChars <= 0)
          return -1;
      }
      return buf[curChar++];
    }

    public String nextLine () {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = read();
      } while (!isEndOfLine(c));
      return res.toString();
    }

    public String nextString () {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      StringBuilder res = new StringBuilder();
      do {
        res.appendCodePoint(c);
        c = read();
      } while (!isSpaceChar(c));
      return res.toString();
    }

    public long nextLong () {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      long res = 0;
      do {
        if (c < '0' || c > '9')
          throw new InputMismatchException();
        res *= 10;
        res += c - '0';
        c = read();
      } while (!isSpaceChar(c));
      return res * sgn;
    }

    public char nextChar () {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      return (char)c;
    }

    public int nextInt () {
      int c = read();
      while (isSpaceChar(c))
        c = read();
      int sgn = 1;
      if (c == '-') {
        sgn = -1;
        c = read();
      }
      int res = 0;
      do {
        if (c < '0' || c > '9')
          throw new InputMismatchException();
        res *= 10;
        res += c - '0';
        c = read();
      } while (!isSpaceChar(c));
      return res * sgn;
    }

    public boolean isSpaceChar (int c) {
      return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }

    public boolean isEndOfLine (int c) {
      return c == '\n' || c == '\r' || c == -1;
    }

  }
}
