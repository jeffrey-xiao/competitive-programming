#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 1000100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct node {
    int next[26];
    int smart[26];
    int len, fail;
    long long num;
    node () {
        for (int i = 0; i < 26; i++) {
            next[i] = -1;
            smart[i] = -1;
        }
    }
};

struct state {
    int curr, prev;
    state () {}
    state (int curr, int prev) {
        this->curr = curr;
        this->prev = prev;
    }
};

int num, suff;
long long ans;
char s[SIZE];
int i = 0;
node tree[SIZE];
stack<long long> lastAdd;
stack<state> lastState;

void Init() {
    num = 2, suff = 1;
    tree[1].len = -1, tree[1].fail = 1;
    tree[2].len = 0, tree[2].fail = 1;
    for (int i = 0; i < 26; i++) {
        tree[1].smart[i] = 1;
        tree[2].smart[i] = 1;
    }
    lastState.push(state(1, 1));
}

long long Type (char letter) {
    s[i++] = letter;
    int curr = suff;
    if (i - tree[curr].len - 2 < 0 || s[i - tree[curr].len - 2] != letter)
        curr = tree[curr].smart[letter - 'a'];
    int next = tree[curr].next[letter - 'a'];
    if (next == -1) {
        next = ++num;
        tree[curr].next[letter - 'a'] = next;
        tree[next].len = tree[curr].len + 2;
        if (tree[next].len == 1) {
            tree[next].fail = 2;
            for (int i = 0; i < 26; i++)
                tree[next].smart[i] = 1;
        } else {
            tree[next].fail = tree[tree[curr].smart[letter - 'a']].next[letter - 'a'];
            for (int i = 0; i < 26; i++)
                tree[next].smart[i] = tree[tree[next].fail].smart[i];
        }
        tree[next].smart[s[i - tree[tree[next].fail].len - 1] - 'a'] = tree[next].fail;
        tree[next].num = tree[tree[next].fail].num + 1;
        //printf("%lld %d %d\n", tree[next].num, tree[next].len, tree[next].next[0]);
        ans += tree[next].num;
        suff = next;
        lastState.push(state(next, lastState.top().curr));
        lastAdd.push(tree[next].num);
    } else {
        ans += tree[next].num;
        suff = next;
        lastState.push(state(next, lastState.top().curr));
        lastAdd.push(tree[next].num);
    }
    return ans;
}

void Backspace () {

    if (lastState.top().curr == num) {
        int rem = lastState.top().prev;
        if (i - tree[rem].len - 2 < 0 || s[i - tree[rem].len - 2] != s[i-1])
            rem = tree[rem].smart[s[i-1] - 'a'];
        tree[rem].next[s[i-1] - 'a'] = -1;
        num--;

    }
    suff = lastState.top().prev;
    i--;
    ans -= lastAdd.top();
    lastAdd.pop();
    lastState.pop();

}
int main () {
    Init();
    printf("%lld\n", Type('j'));
    printf("%lld\n", Type('y'));
    printf("%lld\n", Type('f'));
    printf("%lld\n", Type('f'));
    printf("%lld\n", Type('y'));
    printf("%lld\n", Type('e'));
    printf("%lld\n", Type('y'));
    printf("%lld\n", Type('y'));
    return 0;
}
