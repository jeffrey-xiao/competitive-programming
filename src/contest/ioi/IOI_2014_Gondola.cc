#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1 << 30
#define MOD 1000000009
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

ll modpow(ll n, ll m) {
  if (m == 0)
    return 1ll;
  if (m == 1)
    return n;
  if (m % 2)
    return n * modpow(n * n % MOD, m / 2) % MOD;
  return modpow(n * n % MOD, m / 2);
}

struct Entry {
  int original, replacement;

  Entry(int original, int replacement) {
    this->original = original;
    this->replacement = replacement;
  }

  bool operator<(const Entry &e) const {
    return replacement < e.replacement;
  }
};

int valid(int n, int inputSeq[]) {
  unordered_set<int> hs;
  int r = 0;
  for (int x = 0; x < n; x++) {
    if (inputSeq[x] <= n) {
      r = x;
      break;
    }
  }
  hs.insert(inputSeq[r]);

  for (int x = 0; x < n; x++) {
    if (inputSeq[x % n] <= n && inputSeq[x % n] % n != (inputSeq[r] - r + x) % n)
      return 0;
    hs.insert(inputSeq[x]);
  }
  if ((int)hs.size() == n)
    return 1;
  return 0;
}

int countReplacement(int n, int seq[]) {
  if (valid(n, seq) == 0)
    return 0;
  bool fixed = false;
  vector<int> replaced;
  ll total = 1;
  int maxValue = 0;

  for (int i = 0; i < n; i++) {
    if (seq[i] <= n) {
      fixed = true;
      maxValue = max(maxValue, seq[i]);
    } else {
      replaced.push_back(seq[i]);
    }
  }

  sort(replaced.begin(), replaced.end());
  int hidden = 0;
  int prevVal = n;

  for (int i = 0; i < (int)replaced.size(); i++) {
    hidden = replaced[i] - prevVal - 1;
    prevVal = replaced[i];
    total = (total * modpow((ll)replaced.size() - i, hidden)) % MOD;
  }

  if (!fixed)
    return total * n % MOD;
  return total;
}

int replacement(int n, int gondolaSeq[], int replacementSeq[]) {
  int maxValue = 0;
  int original = 0;
  unordered_set<int> hs;
  vector<int> missing;
  vector<Entry> entryList;
  bool fillMissing = false;

  for (int i = 0; i < n; i++) {
    if (gondolaSeq[i] <= n)
      original = i;
    maxValue = max(maxValue, gondolaSeq[i]);
    hs.insert(gondolaSeq[i]);
  }

  for (int i = n + 1; i <= maxValue; i++) {
    if (!hs.count(i))
      missing.push_back(i);
  }

  sort(missing.begin(), missing.end());

  for (int i = 0; i < n; i++) {
    int og = (gondolaSeq[original] - 1 + (i - original + n) % n) % n + 1;
    if (og != gondolaSeq[i]) {
      if (!fillMissing && (int)missing.size() > 0 &&
          gondolaSeq[i] > missing[(int)missing.size() - 1]) {
        for (int j = 0; j < (int)missing.size(); j++) {
          entryList.push_back(j == 0 ? Entry(og, missing[j]) : Entry(missing[j - 1], missing[j]));
        }
        entryList.push_back(Entry(missing[(int)missing.size() - 1], gondolaSeq[i]));
        fillMissing = true;
      } else {
        entryList.push_back(Entry(og, gondolaSeq[i]));
      }
    }
  }
  sort(entryList.begin(), entryList.end());
  for (int i = 0; i < (int)entryList.size(); i++)
    replacementSeq[i] = entryList[i].original;
  return (int)entryList.size();
}

int main() {
  // freopen("in.txt", "r", stdin);
  // freopen("out.txt", "w", stdout);

  int subtask;
  scanf("%d", &subtask);

  if (subtask <= 3) {
    int n;
    scanf("%d", &n);
    int seq[n];
    for (int i = 0; i < n; i++)
      scanf("%d", &seq[i]);
    printf("%d\n", valid(n, seq));
  } else if (subtask <= 6) {
    int n;
    scanf("%d", &n);
    int seq[n], replaced[250000];
    for (int i = 0; i < n; i++)
      scanf("%d", &seq[i]);
    int l = replacement(n, seq, replaced);
    printf("%d ", l);
    for (int i = 0; i < l; i++)
      printf("%d ", replaced[i]);
    printf("\n");
  } else {
    int n;
    scanf("%d", &n);
    int seq[n];
    for (int i = 0; i < n; i++)
      scanf("%d", &seq[i]);

    printf("%d\n", countReplacement(n, seq));
  }

  return 0;
}
