#include <bits/stdc++.h>
#include "grader.h"

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 50
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

char val[SIZE + 1];

void play () {
    for (int i = 1; i <= SIZE; i++)
        val[i] = faceup(i);

    for (int i = 1; i <= SIZE; i++)
        for (int j = i + 1; j <= SIZE; j++)
            if (val[i] == val[j]) {
                faceup(i);
                faceup(j);
            }
}
