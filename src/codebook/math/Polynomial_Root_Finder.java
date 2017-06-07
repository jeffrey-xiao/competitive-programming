package codebook.math;

import java.util.ArrayList;

public class Polynomial_Root_Finder {

  static final double EPS = 1e-9;
  static final double INF = 1e30;

  static class Monomial {
    double coefficient, exponent;

    Monomial (double coefficient, double exponent) {
      this.coefficient = coefficient;
      this.exponent = exponent;
    }

    double evaluate (double x) {
      return coefficient * Math.pow(x, exponent);
    }

    public String toString () {
      return String.format("%fx^%f", coefficient, exponent);
    }
  }

  static double evaluate (ArrayList<Monomial> polynomial, double x) {
    double ret = 0;
    for (Monomial m : polynomial)
      ret += m.evaluate(x);
    return ret;
  }

  static double findRoot (ArrayList<Monomial> polynomial, double x1, double x2) {
    double y1 = evaluate(polynomial, x1), y2 = evaluate(polynomial, x2);
    boolean neg1 = y1 < 0, neg2 = y2 < 0;

    if (Math.abs(y1) <= EPS)
      return x1;
    if (Math.abs(y2) <= EPS)
      return x2;

    if (neg1 == neg2)
      return Double.NaN;

    while (x2 - x1 > EPS) {
      double x = (x2 + x1) / 2;
      if ((evaluate(polynomial, x) < 0) == neg1)
        x1 = x;
      else
        x2 = x;
    }
    return x1;
  }

  static ArrayList<Double> findAllRoots (ArrayList<Monomial> polynomial) {
    ArrayList<Monomial> diff = new ArrayList<Monomial>();
    ArrayList<Double> ret = new ArrayList<Double>();

    for (Monomial m : polynomial)
      if (m.exponent > 0)
        diff.add(new Monomial(m.coefficient * m.exponent, m.exponent - 1));

    if (diff.isEmpty())
      return ret;

    ArrayList<Double> diffRoots = findAllRoots(diff);
    diffRoots.add(0, -INF);
    diffRoots.add(INF);

    for (int i = 0; i < diffRoots.size() - 1; i++) {
      Double root = findRoot(polynomial, diffRoots.get(i), diffRoots.get(i + 1));
      if (Double.isNaN(root))
        continue;
      if (ret.isEmpty() || Math.abs(ret.get(ret.size() - 1) - root) > EPS)
        ret.add(root);
    }
    return ret;
  }

  public static void main (String[] args) {
    ArrayList<Monomial> polynomial = new ArrayList<Monomial>();
    polynomial.add(new Monomial(2, 3));
    polynomial.add(new Monomial(-54, 0));

    // [2.999999999962339]
    System.out.println(findAllRoots(polynomial).toString());

    polynomial = new ArrayList<Monomial>();
    polynomial.add(new Monomial(3, 2));
    polynomial.add(new Monomial(4, 1));
    polynomial.add(new Monomial(-20, 0));

    // [-3.3333333336180133, 1.9999999997299984]
    System.out.println(findAllRoots(polynomial).toString());
  }
}
