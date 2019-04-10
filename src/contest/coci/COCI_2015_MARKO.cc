#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;

int getDigit(char c) {
  if (c <= 'c')
    return 2;
  if (c <= 'f')
    return 3;
  if (c <= 'i')
    return 4;
  if (c <= 'l')
    return 5;
  if (c <= 'o')
    return 6;
  if (c <= 's')
    return 7;
  if (c <= 'v')
    return 8;
  return 9;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  cin >> N;
  vector<string> dict;

  for (int i = 0; i < N; i++) {
    string in;
    cin >> in;
    for (int j = 0; j < (int)in.size(); j++) {
      in[j] = getDigit(in[j]) + '0';
    }
    dict.push_back(in);
  }

  string S;
  cin >> S;
  int ans = 0;

  for (int i = 0; i < N; i++)
    if (dict[i] == S)
      ans++;

  printf("%d\n", ans);
  return 0;
}
