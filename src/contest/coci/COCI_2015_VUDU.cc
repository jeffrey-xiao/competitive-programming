#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define MAX_N 1000000
#define l(x) x << 1
#define r(x) x << 1 | 1
#define m(x, y) (x + y) / 2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N;
ll ans, P;
ll val[MAX_N];
pair<ll, ll> sorted[MAX_N], aux[MAX_N];

// returns the sum in the range
ll solve(int l, int r, bool isLeft) {
  if (l == r) {
    if (val[l] >= 0)
      ans++;
    sorted[l] = {val[l], val[l]};
    return val[l];
  }

  // [l, mid], [mid + 1, r]
  int mid = (l + r) >> 1;
  ll sum1 = solve(l, mid, 1);
  ll sum2 = solve(mid + 1, r, 0);

  // computing the possible subsequences
  int index = mid;
  for (int i = mid + 1; i <= r; i++) {
    while (index >= l && sorted[index].first >= -sorted[i].first)
      index--;
    ans += mid - index;
  }

  // adjusting the prefix sums based on which part it is on
  if (isLeft) {
    for (int i = l; i <= mid; i++)
      sorted[i].first += sum2;
    for (int i = mid + 1; i <= r; i++)
      sorted[i].first = sum2 - sorted[i].first + sorted[i].second;
    sort(sorted + mid + 1, sorted + r + 1);
  } else {
    for (int i = l; i <= mid; i++)
      sorted[i].first = sum1 - sorted[i].first + sorted[i].second;
    for (int i = mid + 1; i <= r; i++)
      sorted[i].first += sum1;
    sort(sorted + l, sorted + mid + 1);
  }

  // merging the two sections
  for (int i = l; i <= r; i++)
    aux[i] = sorted[i];
  int i = l, j = mid + 1;
  for (int x = l; x <= r; x++) {
    if (i == mid + 1 || (j <= r && aux[i].first >= aux[j].first))
      sorted[x] = aux[j++];
    else
      sorted[x] = aux[i++];
  }
  return sum1 + sum2;
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d", &N);

  for (int i = 0; i < N; i++)
    scanf("%lld", &val[i]);

  scanf("%lld", &P);

  for (int i = 0; i < N; i++)
    val[i] -= P;

  solve(0, N - 1, 0);
  printf("%lld\n", ans);
  return 0;
}
