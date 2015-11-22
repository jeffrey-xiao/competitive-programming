#include <bits/stdc++.h>
#include "inaho.h"

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

int id[SIZE];
int size[SIZE];
stack<pair<pi, int>> last;

int find (int x) {
    return x == id[x] ? x : (find(id[x]));
}

void Init (int N) {
    for (int i = 0; i < N; i++) {
        id[i] = i;
        size[i] = 1;
    }
}
void AddEdge(int U, int V) {
    int rootU = find(U-1);
    int rootV = find(V-1);
    if (rootU == rootV) {
        last.push(mp(mp(rootU, rootV), 0));
        return;
    }
    if (size[rootU] > size[rootV]) {
        size[rootU] += size[rootV];
        id[rootV] = rootU;
    } else {
        size[rootV] += size[rootU];
        id[rootU] = rootV;
    }
    last.push(mp(mp(rootU, rootV), 1));
}
void RemoveLastEdge () {
    if (!last.top().second) {
        last.pop();
        return;
    }
    pi lastEdge = last.top().first;
    last.pop();
    if (id[lastEdge.first] == lastEdge.first) {
        id[lastEdge.second] = lastEdge.second;
        size[lastEdge.first] -= size[lastEdge.second];
    } else {
        id[lastEdge.first] = lastEdge.first;
        size[lastEdge.second] -= size[lastEdge.first];
    }
}
int GetSize (int U) {
    return size[find(U-1)];
}
