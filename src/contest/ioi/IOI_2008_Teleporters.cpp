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

int n, m;
int to[SIZE*2], start[SIZE], last[SIZE], sorted[SIZE*2];
bool v[SIZE*2];

int bfs (int i) {
    int cnt = 0;
    v[i] = true;
    while (to[i] != -1 && !v[to[i]]) {
        cnt++;
        i = to[i];
        v[i] = true;
    }
    return cnt + 1;
}

int toIndex (int i) {
    int lo = 0;
    int hi = 2*n-1;
    while (lo <= hi) {
        int mid = (lo + hi) >> 1;
        if (sorted[mid] < i) {
            lo = mid + 1;
        } else if (sorted[mid] > i) {
            hi = mid - 1;
        } else {
            return mid;
        }
    }
    return -1;
}

int main () {
    rint(n), rint(m);

    for (int i = 0; i < n; i++) {
        rint(start[i]), rint(last[i]);
        sorted[i*2] = start[i];
        sorted[i*2+1] = last[i];
    }
    sort(sorted, sorted + 2*n);
    for (int i = 0; i < n; i++) {
        to[toIndex(start[i])] = toIndex(last[i])+1;
        to[toIndex(last[i])] = toIndex(start[i]) + 1;
    }
    to[2*n] = -1;
    priority_queue<int> pq;
    int ans = bfs(0) - 1;
    for (int i = 0; i <= 2*n; i++)
        if (!v[i])
            pq.push(bfs(i));
    for (int i = 0; i < m; i++) {
        if (!pq.empty()) {
            ans += 2 + pq.top();
            pq.pop();
        } else {
            ans += (m - i) / 2 * 2 * 2 + (m - i) % 2;
            break;
        }
    }
    printf("%d\n", ans);
    return 0;
}
