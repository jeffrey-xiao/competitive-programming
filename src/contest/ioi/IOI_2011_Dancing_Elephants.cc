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

struct State {
  int index, camerasNeeded, highestPosition;
  State(int index, int camerasNeeded, int highestPosition) {
    this->index = index;
    this->camerasNeeded = camerasNeeded;
    this->highestPosition = highestPosition;
  }
};

int N, L, M;
int pos[SIZE], bucket[SIZE];

int S, bucketCnt;
vector<vector<State>> b;

void rebuildBucket(int i) {
  int index = b[i].size();
  for (int j = b[i].size() - 1; j >= 0; j--) {
    while (index - 1 > j && pos[b[i][index - 1].index] - pos[b[i][j].index] > L)
      index--;
    if (index == b[i].size()) {
      b[i][j].camerasNeeded = 1;
      b[i][j].highestPosition = pos[b[i][j].index] + L;
    } else {
      b[i][j].camerasNeeded = 1 + b[i][index].camerasNeeded;
      b[i][j].highestPosition = b[i][index].highestPosition;
    }
  }
}

void rebuild() {
  for (int i = 0; i < bucketCnt; i++) {
    rebuildBucket(i);
  }
}

void remake() {
  vector<int> curr;
  for (int i = 0; i < bucketCnt; i++)
    for (int j = 0; j < b[i].size(); j++)
      curr.pb(b[i][j].index);

  for (int i = 0; i < bucketCnt; i++)
    b[i].clear();

  for (int i = 0; i < curr.size(); i++) {
    b[i / S].pb(State(curr[i], 0, 0));
    bucket[curr[i]] = i / S;
  }

  rebuild();
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  scanf("%d%d%d", &N, &L, &M);

  S = (int)sqrt(N);
  bucketCnt = (N - 1) / S + 1;

  for (int i = 0; i < bucketCnt; i++)
    b.pb(vector<State>());

  for (int i = 0; i < N; i++) {
    scanf("%d", &pos[i]);
    bucket[i] = i / S;
    b[i / S].pb(State(i, 0, 0));
  }

  rebuild();

  int rebuildCount = 0;
  for (int i = 0; i < M; i++) {
    int index, newPos;
    scanf("%d%d", &index, &newPos);
    pos[index] = newPos;

    // removing from bucket
    for (int j = 0; j < b[bucket[index]].size(); j++) {
      if (b[bucket[index]][j].index == index) {
        b[bucket[index]].erase(b[bucket[index]].begin() + j);
        break;
      }
    }

    // adding to bucket
    int oldBucket = bucket[index];
    int newBucket = 0;

    for (; newBucket < bucketCnt; newBucket++) {
      if (b[newBucket].empty())
        continue;
      if (pos[b[newBucket][b[newBucket].size() - 1].index] >= newPos)
        break;
    }

    if (newBucket == bucketCnt)
      newBucket--;

    bucket[index] = newBucket;
    bool added = false;
    for (int j = 0; j < b[newBucket].size(); j++) {
      if (pos[b[newBucket][j].index] >= newPos) {
        b[newBucket].insert(b[newBucket].begin() + j, State(index, 0, 0));
        added = true;
        break;
      }
    }

    if (!added)
      b[newBucket].pb(State(index, 0, 0));

    // rebuilding removed bucket and new bucket
    rebuildBucket(oldBucket);
    rebuildBucket(newBucket);

    int ans = 0;
    for (int j = 0, currPos = -1; j < bucketCnt; j++) {
      if (b[j].empty())
        continue;
      if (currPos == -1) {
        ans += b[j][0].camerasNeeded;
        currPos = b[j][0].highestPosition;
      } else {
        int lo = 0;
        int hi = b[j].size() - 1;

        while (lo <= hi) {
          int mid = (lo + hi) >> 1;
          if (pos[b[j][mid].index] <= currPos)
            lo = mid + 1;
          else
            hi = mid - 1;
        }
        if (lo < b[j].size()) {
          ans += b[j][lo].camerasNeeded;
          currPos = b[j][lo].highestPosition;
        }
      }
    }

    if (rebuildCount == bucketCnt) {
      rebuildCount = 0;
      remake();
    }
    rebuildCount++;
    printf("%d\n", ans);
  }

  return 0;
}
