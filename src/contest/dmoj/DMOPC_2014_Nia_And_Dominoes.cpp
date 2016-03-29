#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int A, B, M;
string N;

pi f (pi s) {
    return mp((s.first * A + s.second * B) % M, s.first);
}

pi getCycle (pi x) {
    int power = 1;
    int length = 1;
    pi tortoise = x;
    pi hare = f(x);

    while (tortoise != hare) {
        if (power == length) {
            tortoise = hare;
            power *= 2;
            length = 0;
        }
        hare = f(hare);
        length++;
    }

    hare = x;
    for (int i = 0; i < length; i++)
        hare = f(hare);

    int start = 0;
    tortoise = x;

    while (tortoise != hare) {
        tortoise = f(tortoise);
        hare = f(hare);
        start++;
    }
    return {start, length};
}


int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d%d", &A, &B, &M);
    pi c = getCycle({1, 0});

    int res = 0;
    int actual = 0;
    bool useCycle = false;
    while (1) {
        char ch = getchar();
        if (ch == '\n')
            continue;
        if (ch == EOF)
            break;
        res = (res * 10 + ch - '0') % c.second;
        actual = (actual * 10 + ch - '0');
        if (actual >= c.first)
            useCycle = true;
    }
    pi ans = {1, 0};

    if (!useCycle) {
        for (int i = 0; i < actual; i++)
            ans = f(ans);
    } else {
        res = (res - c.first) % c.second + c.first;

        for (int i = 0; i < res; i++)
            ans = f(ans);
    }
    printf("%d\n", ans.first);
    return 0;
}
