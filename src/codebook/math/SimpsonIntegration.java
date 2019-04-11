package codebook.math;

import java.util.function.DoubleFunction;

public class SimpsonIntegration {
  public static double integrate(DoubleFunction<Double> f, double a, double b) {
    double eps = 1e-15;
    double m = (a + b) / 2;
    double am = simpsonIntegration(f, a, m);
    double mb = simpsonIntegration(f, m, b);
    double ab = simpsonIntegration(f, a, b);
    if (Math.abs(am + mb - ab) < eps)
      return ab;
    return integrate(f, a, m) + integrate(f, m, b);
  }

  static double simpsonIntegration(DoubleFunction<Double> f, double a, double b) {
    return (f.apply(a) + 4 * f.apply((a + b) / 2) + f.apply(b)) * (b - a) / 6;
  }

  // Usage example
  public static void main(String[] args) {
    System.out.println(integrate(new DoubleFunction<Double>() {
      @Override
      public Double apply(double x) {
        return Math.sin(x);
      }
    }, 0, Math.PI / 2));

    System.out.println(integrate(new DoubleFunction<Double>() {
      @Override
      public Double apply(double x) {
        return Math.pow(x, 4) - 4 * Math.pow(x, 3) + 12 * Math.pow(x, 2) - 11 * x + 36;
      }
    }, 5, 15));
  }
}