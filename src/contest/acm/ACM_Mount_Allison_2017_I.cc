#include <bits/stdc++.h>

#define cmr const matrix &
#define fbo friend bool operator
#define fmo friend matrix operator

using namespace std;
typedef long long ll;
ll MOD = 1000000007;

struct matrix {
  int r, c;
  vector<vector<ll>> mat;

  matrix(int rows, int cols) {
    r = rows;
    c = cols;
    mat.resize(r, vector<ll>(c, 0));
  }

  matrix(const vector<vector<ll>> &m) {
    r = m.size();
    c = m[0].size();
    mat = m;
    mat.resize(r, vector<ll>(c));
  }

  ll &operator()(int r, int c) {
    return mat[r][c];
  }
  vector<ll> &operator[](int r) {
    return mat[r];
  }

  fmo *(cmr a, cmr b) {
    matrix res(a.r, b.c);
    for (int i = 0; i < a.r; i++)
      for (int j = 0; j < b.c; j++)
        for (int k = 0; k < a.c; k++)
          res.mat[i][j] += (a.mat[i][k] * b.mat[k][j]) % MOD;
    return res;
  }
};

matrix eye(int n) {
  matrix res(n, n);
  for (int i = 0; i < n; i++)
    res[i][i] = 1;
  return res;
}

matrix operator^(const matrix &a, unsigned int n) {
  if (n == 0)
    return eye(a.r);
  if (n % 2 == 0)
    return (a * a) ^ (n / 2);
  return a * (a ^ (n - 1));
}

int N;
int K;
matrix nxt{50, 50};

void solve() {
  nxt = matrix{50, 50};
  cin >> N >> K;
  for (int i = 0; i < K; ++i) {
    int l;
    cin >> l;
    nxt[l - 1][0] += 1;
  }
  for (int i = 0; i < 50; ++i) {
    nxt[i][i + 1] = 1;
  }
  int result = (nxt ^ N)[0][0];
  // check 10^9+9
  MOD = 1000000009;
  int check = (nxt ^ N)[0][0];
  MOD = 1000000007;
  if (result == 0 && check == 0) {
    cout << "IMPOSSIBLE" << endl;
  } else {
    cout << result << endl;
  }
}

int main() {
  int ll;
  cin >> ll;
  for (int i = 0; i < ll; ++i) {
    solve();
  }
}
