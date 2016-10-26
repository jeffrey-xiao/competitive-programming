package contest.acm;
import java.util.*;
import java.io.*;

public class ACM_NA_East_Central_2015_D {

    static BufferedReader br;
    static PrintWriter out;
    static StringTokenizer st;

    static int R, C;
    static int[][] dist;
    static int max;
    
    public static void main (String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(new OutputStreamWriter(System.out));
        //br = new BufferedReader(new FileReader("in.txt"));
        //out = new PrintWriter(new FileWriter("out.txt"));
    
        R = readInt();
        C = readInt();
        
        dist = new int[R][C];
        ArrayDeque<Point> q = new ArrayDeque<Point>();
        for (int i = 0; i < R; i++) {
            Arrays.fill(dist[i], 1 << 30);
            char[] in = readLine().toCharArray();

            for (int j = 0; j < C; j++) {
                if (in[j] == '.') {
                    q.offerFirst(new Point(i, j));
                    dist[i][j] = 0;
                } else if (in[j] == 'T' && (i == 0 || i == R - 1 || j == 0 || j == C - 1)){
                    q.offerLast(new Point(i, j));
                    dist[i][j] = 1;
                }
            }
        }
        while (!q.isEmpty()) {
            Point curr = q.poll();
            
            for (int mr = -1; mr <= 1; mr++)
                for (int mc = -1; mc <= 1; mc++) {
                    if (Math.abs(mr) + Math.abs(mc) != 1)
                        continue;
                    int r = curr.r + mr;
                    int c = curr.c + mc;
                    
                    if (r < 0 || r >= R || c < 0 || c >= C || dist[r][c] != 1 << 30)
                        continue;
                    
                    dist[r][c] = dist[curr.r][curr.c] + 1;
                    max = Math.max(max, Integer.toString(dist[r][c]).length());
                    q.offer(new Point(r, c));
                }
        }
        for (int i = 0; i < R; i++, out.println())
            for (int j = 0; j < C; j++) {
                String s = "" +(dist[i][j] == 0 ? "." : dist[i][j]);
                out.print(fill(s));
            }
        out.close();
    }

    static String fill (String s) {
        while (s.length() <= max)
            s = "." + s;
        return s;
    }
    
    static class Point {
        int r, c;
        Point (int r, int c) {
            this.r = r;
            this.c = c;
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