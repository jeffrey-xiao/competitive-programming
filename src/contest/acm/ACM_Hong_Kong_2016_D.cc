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

struct Activity {
  int start, finish;

  Activity(int start, int finish) {
    this->start = start;
    this->finish = finish;
  }

  bool operator<(const Activity &a) const {
    return finish < a.finish;
  }
};

multiset<int> classrooms;
int N, K;
int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d%d", &N, &K);
  vector<Activity> a;

  for (int i = 0; i < N; i++) {
    int start, finish;
    scanf("%d%d", &start, &finish);
    a.pb(Activity(start, finish));
  }

  for (int i = 0; i < K; i++)
    classrooms.insert(0);

  sort(a.begin(), a.end());
  int ans = 0;
  for (int i = 0; i < N; i++) {
    auto it = classrooms.lower_bound(a[i].start);

    if (it != classrooms.begin()) {
      ans++;
      classrooms.erase(--it);
      classrooms.insert(a[i].finish);
    }
  }

  printf("%d\n", ans);
  return 0;
}
