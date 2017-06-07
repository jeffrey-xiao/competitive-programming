package codebook.math;

import java.util.ArrayList;

public class Euclid {

  static int mod (int a, int b) {
    return ((a % b) + b) % b;
  }

  static int gcd (int a, int b) {
    return b == 0 ? a : (gcd(b, a % b));
  }

  static int lcm (int a, int b) {
    return a / gcd(a, b) * b;
  }

  // returns d = gcd(a, b); finds x, y such that d = ax * by
  public static int[] euclid (int a, int b) {
    int x = 1, y = 0, x1 = 0, y1 = 1, t;
    while (b != 0) {
      int q = a / b;
      t = x;
      x = x1;
      x1 = t - q * x1;
      t = y;
      y = y1;
      y1 = t - q * y1;
      t = b;
      b = a - q * b;
      a = t;
    }
    return a > 0 ? new int[] {a, x, y} : new int[] {-a, -x, -y};
  }

  public static int[] euclid2 (int a, int b) {
    if (b == 0)
      return a > 0 ? new int[] {a, 1, 0} : new int[] {-a, -1, 0};
    int[] r = euclid2(b, a % b);
    return new int[] {r[0], r[2], r[1] - a / b * r[2]};
  }

  // finds all solutions to ax = b mod n
  static ArrayList<Integer> linearEquationSolver (int a, int b, int n) {
    ArrayList<Integer> ret = new ArrayList<Integer>();
    int[] res = euclid(a, n);
    int d = res[0], x = res[1];

    if (b % d == 0) {
      x = mod(x * (b / d), n);
      for (int i = 0; i < d; i++)
        ret.add(mod(x + i * (n / d), n));
    }

    return ret;
  }

  // Chinese remainder theorem (special case): find z such that
  // z % m1 = r1, z % m2 = r2.  Here, z is unique modulo M = lcm(m1, m2).
  // Return (z, M).  On failure, M = -1.
  static int[] chineseRemainderTheorem (int m1, int r1, int m2, int r2) {
    int[] res = euclid(m1, m2);
    int g = res[0], s = res[1], t = res[2];
    if (r1 % g != r2 % g)
      return new int[] {0, -1};
    return new int[] {mod(s * r2 * m1 + t * r1 * m2, m1 * m2) / g, m1 * m2 / g};
  }

  // Chinese remainder theorem: find z such that
  // z % m[i] = r[i] for all i.  Note that the solution is
  // unique modulo M = lcm_i (m[i]).  Return (z, M). On 
  // failure, M = -1. Note that we do not require the a[i]'s
  // to be relatively prime.
  static int[] chineseRemainderTheorem (int[] m, int[] r) {
    int[] ret = new int[] {r[0], m[0]};
    for (int i = 1; i < m.length; i++) {
      ret = chineseRemainderTheorem(ret[1], ret[0], m[i], r[i]);
      if (ret[1] == -1)
        break;
    }
    return ret;
  }

  // computes x and y such that ax + by = c; on failure, x = y = -1 << 30
  static int[] linearDiophantine (int a, int b, int c) {
    int d = gcd(a, b);
    int x, y;

    if (c % d != 0) {
      x = y = -1 << 30;
    } else {
      a /= d;
      b /= d;
      c /= d;
      int[] ret = euclid(a, b);
      x = ret[1] * c;
      y = ret[2] * c;
    }

    return new int[] {x, y};
  }

  // precondition: m > 0 && gcd(a, m) = 1
  public static int modInverse (int a, int m) {
    a = mod(a, m);
    return a == 0 ? 0 : mod((1 - modInverse(m % a, a) * m) / a, m);
  }

  // precondition: m > 0 && gcd(a, m) = 1
  public static int modInverse2 (int a, int m) {
    return (euclid(a, m)[1] % m + m) % m;
  }

  // precondition: p is prime
  public static int[] generateInverse (int p) {
    int[] res = new int[p];
    res[1] = 1;
    for (int i = 2; i < p; ++i)
      res[i] = (p - (p / i) * res[p % i] % p) % p;
    return res;
  }

  // solve x = a[i] (mod p[i]), where gcd(p[i], p[j]) == 1
  public static int simpleRestore (int[] a, int[] p) {
    int res = a[0];
    int m = 1;
    for (int i = 1; i < a.length; i++) {
      m *= p[i - 1];
      while (res % p[i] != a[i])
        res += m;
    }
    return res;
  }

  public static int garnerRestore (int[] a, int[] p) {
    int[] x = new int[a.length];
    for (int i = 0; i < x.length; ++i) {
      x[i] = a[i];
      for (int j = 0; j < i; ++j) {
        x[i] = (int)modInverse(p[j], p[i]) * (x[i] - x[j]);
        x[i] = (x[i] % p[i] + p[i]) % p[i];
      }
    }
    int res = x[0];
    int m = 1;
    for (int i = 1; i < a.length; i++) {
      m *= p[i - 1];
      res += x[i] * m;
    }
    return res;
  }
}
