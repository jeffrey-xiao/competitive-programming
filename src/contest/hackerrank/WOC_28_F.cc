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

int G, ans;
string sorted;

struct Interval {
  int sz;
  int canSub;
  Interval *left, *right;
  Interval(int sz) {
    this->sz = sz;
    this->canSub = -1;
    this->left = nullptr;
    this->right = nullptr;
  }
};

static int compute(string w) {
  Interval *prev = nullptr;
  vector<Interval *> intervals[26];

  for (int i = 0; i < (int)w.size();) {
    char c = w[i++];
    Interval *curr = new Interval(1);
    while (i < (int)w.size() && w[i] == c) {
      curr->sz++;
      i++;
    }
    curr->left = prev;
    if (prev != nullptr)
      prev->right = curr;
    intervals[c - 'a'].push_back(curr);
    prev = curr;
  }

  int currAns = 0;
  int active = -2;
  for (int i = 0; i < 26; i++) {
    if (intervals[i].size() == 0)
      continue;
    int canSub = 0;
    for (int j = intervals[i].size() - 2; j >= 0; j--) {
      Interval *curr = intervals[i][j];
      if (curr->right == intervals[i][j + 1]) {
        curr->sz += curr->right->sz;
        curr->right = curr->right->right;
        delete *(intervals[i].begin() + j + 1);
        intervals[i].erase(intervals[i].begin() + j + 1);
      }
    }

    currAns += intervals[i].size();
    for (int j = 0; j < (int)intervals[i].size(); j++) {
      Interval *curr = intervals[i][j];
      if (curr->canSub == active)
        canSub++;
    }

    if (canSub > 0) {
      currAns--;
    }

    for (int j = 0; j < (int)intervals[i].size(); j++) {
      Interval *curr = intervals[i][j];
      if (canSub >= 2 || canSub == 0) {
        if (curr->right != nullptr) {
          curr->right->canSub = i;
        }
      } else if ((canSub == 1 && curr->canSub != active) || intervals[i].size() == 1) {
        if (curr->right != nullptr) {
          curr->right->canSub = i;
        }
      }

      if (curr->left != nullptr)
        curr->left->right = curr->right;
      if (curr->right != nullptr)
        curr->right->left = curr->left;
    }
    active = i;
  }

  for (int i = 0; i < 26; i++)
    for (int j = 0; j < intervals[i].size(); j++)
      delete *(intervals[i].begin() + j);

  return currAns;
}

int solve(string s) {
  if (s == "")
    return 0;
  string ss = s + s;

  vector<string> candidates;

  for (int i = (int)ss.size() - 1; i >= (int)s.size(); i--)
    candidates.push_back(ss.substr(i - s.size() + 1, s.size()));

  for (string w : candidates)
    ans = min(ans, compute(w));
  return ans;
}

string preprocess(string s) {
  int index = 0;
  int best = 0;
  while (best < (int)s.size() && s[best] == sorted[index]) {
    index++;
    best++;
  }
  return s.substr(best);
}

int main() {
  ios_base::sync_with_stdio(false);
  cin.tie(NULL);
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  cin >> G;

  for (int g = 1; g <= G; g++) {
    string s;
    cin >> s;
    sorted = s;
    sort(sorted.begin(), sorted.end());

    ans = 1 << 30;
    s = preprocess(s);
    printf("%d\n", solve(s));
  }

  return 0;
}
