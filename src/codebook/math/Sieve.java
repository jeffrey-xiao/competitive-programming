package codebook.math;

public class Sieve {

  static int[] numOfDivisors(int n) {
    int[] ret = new int[n + 1];
    for (int i = 1; i <= n; i++)
      for (int j = i; j <= n; j += i)
        ret[j]++;
    return ret;
  }

  static int[] sumOfDivisors(int n) {
    int[] ret = new int[n + 1];
    for (int i = 1; i <= n; i++)
      for (int j = i; j <= n; j += i)
        ret[j] += i;
    return ret;
  }

  static int[] eulerTotient(int n) {
    int[] ret = new int[n + 1];
    for (int i = 1; i <= n; i++)
      ret[i] = i;
    for (int i = 2; i <= n; i++)
      if (ret[i] == i)
        for (int j = i; j <= n; j += i)
          ret[j] -= ret[j] / i;
    return ret;
  }

  static int[] biggestPrimeDivisor(int n) {
    int[] ret = new int[n + 1];
    for (int i = 1; i <= n; i++)
      if (ret[i] == i)
        for (int j = i; j <= n; j += i)
          ret[i] = i;
    return ret;
  }
}
