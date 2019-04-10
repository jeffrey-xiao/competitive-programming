#include <bits/stdc++.h>
#define PI 3.1415926535
#define sides(a, b, c) double a = abs(C - B), b = abs(C - A), c = abs(A - B)

using namespace std;
typedef complex<double> point;

point intersection(point a1, point a2, point b1, point b2) {
  double u = (conj(b2 - b1) * (a1 - b1)).imag() / (conj(b2 - b1) * (a1 - a2)).imag();
  return a1 + u * (a2 - a1);
}

double angle(point a1, point a2, point b1, point b2) {
  double argd = arg(b2 - b1) - arg(a2 - a1);
  if (argd > 2 * PI) {
    return argd - 2 * PI;
  } else if (argd < 0) {
    return argd + 2 * PI;
  } else {
    return argd;
  }
}

point im(0.0, 1.0);

int main() {
  int P;
  cin >> P;
  for (int i = 0; i < P; ++i) {
    int K;
    double ax, ay, bx, by, cx, cy;
    cin >> K >> ax >> ay >> bx >> by >> cx >> cy;
    point A(ax, ay);
    point B(bx, by);
    point C(cx, cy);
    double low = -1e-9;
    double high = min(angle(A, B, B, C), min(angle(B, C, C, A), angle(C, A, A, B)));

    point P;
    while (fabs(high - low) > 1e-10) {
      double theta = (low + high) / 2;
      point itheta(0.0, theta);
      point A2 = A + (B - A) * exp(itheta);
      point B2 = B + (C - B) * exp(itheta);
      P = intersection(A, A2, B, B2);
      if (P.real() != P.real() || P.imag() != P.imag()) {
        high -= 1e-9;
        while (1)
          ;
      }
      double angle = arg(P - C) - arg(A - C);
      if (angle < 0)
        angle += 2 * PI;
      if (angle >= PI)
        angle -= 2 * PI;
      if (angle > theta) {
        low = theta;
      } else {
        high = theta;
      }
    }
    cout << K << " " << setprecision(10) << P.real() << " " << P.imag() << endl;
  }
}
