package codebook.math;

public class RealRootFinder {

  static final double EPS = 1e-9;

  interface Function {
    public abstract double eval (double x);
  }

  // Finds a root of function f with derivative d based on initial guess x0 
  public static double newton (double x0, Function f, Function d) {
    double prev = x0, next = x0;
    do {
      prev = next;
      next = prev - f.eval(prev) / d.eval(prev);
    } while (Math.abs(next - prev) > EPS);
    return next;
  }

  // Finds a root of function f(x) = 0 by rearranging it into
  // g(x) = x and repeatedly plugging x into g(x)
  // Does not work if |f'(x0)| >= 1
  public static double fixedPointIteration (double x0, Function f) {
    double prev = x0, next = x0;
    do {
      prev = next;
      next = f.eval(prev);
    } while (Math.abs(next - prev) > EPS);
    return next;
  }

  public static void main (String[] args) {
    Function f = (double x) -> (x * x * x * x - 15 * x + 2);
    Function d = (double x) -> (4 * x * x * x - 15);
    Function g = (double x) -> ((x * x * x * x + 2) / 15);

    // 0.13335441662206313
    System.out.println(newton(1, f, d));
    // 0.13335441662208475
    System.out.println(fixedPointIteration(1, g));
  }
}
