#include <bits/stdc++.h>
#include "grader.h"

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

void Solve () {
    int r;
    int a = 1, b = 1, c = 1;
    while ((r = Theory(a, b, c)) != 0) {
        if (r == 1)
            a++;
        else if (r == 2)
            b++;
        else if (r == 3)
            c++;
    }
}
