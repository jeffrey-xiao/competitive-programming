#include "towns.h"
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include "graderlib.c"

#include <bits/stdc++.h>
#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 110
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int dist[SIZE][SIZE];

int id[SIZE], sz[SIZE];
int total = 0;

int getDist (int i, int j) {
    if (i == j)
        return 0;
    if (dist[i][j] == -1) {
        total++;
        return dist[i][j] = dist[j][i] = getDistance(i, j);
    }
    return dist[i][j];
}

int find (int x) {
    return x == id[x] ? x : (id[x] = find(id[x]));
}

void merge (int x, int y) {
    x = find(x);
    y = find(y);
    if (x == y)
        return;
    if (sz[x] < sz[y])
        swap(x, y);
    id[y] = x;
    sz[x] += sz[y];
}

bool isMajority (int dist, int N, int A) {
    for (int i = 0; i < N; i++) {
        id[i] = i;
        sz[i] = 1;
    }

    vector<int> remaining;
    set<int> eliminated;

    for (int i = 0; i < N; i++) {
        int lcaToI = (getDist(i, 0) + getDist(i, A) - getDist(0, A)) / 2;
        int lcaToA = getDist(i, A) - lcaToI;
        if (lcaToA != dist)
            continue;
        remaining.pb(i);
    }

    while (remaining.size() > 1) {
        vector<int> newRemaining;
        for (int i = 0; i < remaining.size(); i += 2) {
            if (i + 1 >= remaining.size()) {
                eliminated.insert(remaining[i]);
                continue;
            } else {
                int x = remaining[i];
                int y = remaining[i + 1];
                int lcaToX = (getDist(x, 0) + getDist(x, A) - getDist(0, A)) / 2;
                int lcaToA = getDist(x, A) - lcaToX;
                int xToY = getDist(x, A) + getDist(y, A) - lcaToA * 2;

                if (xToY == getDist(x, y)) {
                    eliminated.insert(x);
                    eliminated.insert(y);
                } else {
                    merge(x, y);
                    newRemaining.pb(find(x));
                }
            }
        }
        remaining = newRemaining;
    }

    int candidate = remaining[0];
    int cnt = sz[find(candidate)];
    for (int x : eliminated) {
        int lcaToX = (getDist(x, 0) + getDist(x, A) - getDist(0, A)) / 2;
        int lcaToA = getDist(x, A) - lcaToX;
        int xToCandidate = getDist(x, A) + getDist(candidate, A) - lcaToA * 2;
        if (xToCandidate != getDist(x, candidate))
            cnt += sz[find(x)];
    }

    //printf("%d %d\n", total, 7 * N / 2);
    if (N / 2 >= cnt)
        return false;
    return true;
}

int hubDistance (int N, int sub) {
    total = 0;
    for (int i = 0; i < N; i++)
        for (int j = 0; j < N; j++)
            dist[i][j] = -1;

    // first point in diameter
    int A = -1;
    for (int i = 1; i < N; i++)
        if (A == -1 || getDist(0, A) < getDist(0, i))
            A = i;

    // second point in diameter
    int B = -1;

    for (int i = 0; i < N; i++)
        if (i != B && (B == -1 || getDist(A, B) < getDist(A, i)))
            B = i;

    map<int, int> countA;
    map<int, int> maxA;

    bool poss = false;
    int minDist = 1 << 30;
    int index = 0;
    int totalBefore = 0;
    int totalAfter = 0;

    // determining nodes on path 0-A
    for (int i = 0; i < N; i++) {
        if (i == 0 || i == A)
            continue;

        int lcaToI = (getDist(i, 0) + getDist(i, A) - getDist(0, A)) / 2;
        int lcaToA = getDist(i, A) - lcaToI;
        int lcaTo0 = getDist(i, 0) - lcaToI;

        countA[lcaToA]++;
        int maxDistA = max(lcaToA, getDist(A, B) - lcaToA);
        int maxDist0 = max(lcaTo0, getDist(A, 0) - lcaTo0);
        minDist = min(minDist, max(maxDistA, maxDist0));
        maxA[lcaToA] = max(maxA[lcaToA], max(lcaToI, max(lcaToA, lcaTo0)));
    }

    countA[(*countA.begin()).first]++;
    countA[(*countA.rbegin()).first]++;

    index = 0;
    for (pi p : countA)
        totalAfter += p.second;

    for (pi p : countA) {
        totalBefore += p.second;
        totalAfter -= p.second;

        // found hub
        if (minDist == max(p.first, getDist(A, B) - p.first)) {
            if (max(totalBefore - p.second, totalAfter) <= N / 2 && !isMajority(p.first, N, A)) {
                poss = true;
            }
        }
        index++;
    }

    if (poss)
        return minDist;
    return -minDist;
}

int main() {
    FILE *f;
    f = freopen("towns.in","r",stdin);
    assert(f != NULL);
    //f = freopen("towns.out","w",stdout);
    assert(f != NULL);
    int ncase, R, N;
    int subtask;
    int ret;
    ret = scanf("%d%d",&subtask,&ncase);
    assert(ret == 2);
    for (int i = 0; i < ncase; i++) {
        ret = scanf("%d",&N);
	assert(ret == 1);
        _ini_query(N,subtask);
        R=hubDistance(N,subtask);
        printf("%d\n",R);
    }
    return 0;
}
