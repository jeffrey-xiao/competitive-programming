#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD (ll)(1e9 + 7)
#define HASH 31
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 2000001
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef unsigned long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct Trie {
    Trie *next[26];
    int last = -1;
    Trie () {
        for (int i = 0; i < 26; i++)
            next[i] = nullptr;
    }
};

Trie *root = new Trie();
int N;
vector<int> sz;
vector<int> height;
vector<ll> hashes;
vector<ll> currentHash;
ll hashPow[SIZE];


void find (int j, string s) {
    Trie *curr = root;
    for (int i = 0; i < (int)s.size(); i++) {
        if (curr->next[s[i] - 'A'] == nullptr)
            return;
        curr = curr->next[s[i] - 'A'];
        if (curr->last != -1) {
            int k = curr->last;
            ll hash1 = hashes[k];
            ll sub = sz[j] - 1 - sz[k] < 0 ? 0 : currentHash[sz[j] - sz[k] - 1];
            ll hash2 = (hashes[j] - sub * hashPow[sz[k]] % MOD + MOD) % MOD;
            if (hash1 == hash2)
                height[j] = max(height[j], 1 + height[k]);
        }
    }
}

void insert (int j, string s) {
    Trie *curr = root;
    for (int i = 0; i < (int)s.size(); i++) {
        if (curr->next[s[i] - 'A'] == nullptr)
            curr->next[s[i] - 'A'] = new Trie();
        curr = curr->next[s[i] - 'A'];
    }
    curr->last = j;
}

int main () {
    ios_base::sync_with_stdio(false);
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    cin >> N;
    height.resize(N);

    hashPow[0] = 1;
    for (int i = 1; i < SIZE; i++)
        hashPow[i] = (hashPow[i - 1] * HASH) % MOD;

    for (int i = 0; i < N; i++) {
        string in;
        cin >> in;

        height[i] = 1;
        sz.push_back((int)in.size());
        currentHash.resize(in.size());

        for (int j = 0; j < (int)in.size(); j++) {
            if (j)
                currentHash[j] = (currentHash[j - 1] * HASH + in[j] - 'A') % MOD;
            else
                currentHash[j] = in[j] - 'A';
        }

        hashes.push_back(currentHash[(int)in.size() - 1]);
        find(i, in);
        insert(i, in);
    }

    int ans = 0;
    for (int i = N - 1; i >= 0; i--)
        ans = max(ans, height[i]);

    printf("%d\n", ans);
	return 0;
}
