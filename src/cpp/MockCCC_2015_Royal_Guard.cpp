#include <bits/stdc++.h>


using namespace std;

#define pb push_back
#define mp make_pair
typedef pair<int, int> pi;

int N, M;
bool lowerX (pi first, pi second) {
    if (first.first == second.first)
        return first.second < second.second;
    return first.first < second.first;
}
bool lowerY (pi first, pi second) {
    if (first.second == second.second)
        return first.first < second.first;
    return first.second < second.second;
}
vector<pi> xs;
vector<pi> ys;
int main () {
    scanf("%d", &N);
    for (int i = 0; i < N; i++) {
        int x, y;
        scanf("%d%d", &x, &y);
        xs.pb(mp(x, y));
        ys.pb(mp(x, y));
    }
    sort(xs.begin(), xs.end(), lowerX);
    sort(ys.begin(), ys.end(), lowerY);
    int sx, sy;
    scanf("%d", &M);
    scanf("%d%d", &sx, &sy);
    long long res = 0;
    for (int i = 0; i < M-1; i++) {
        int nx, ny;
        scanf("%d%d", &nx, &ny);
        if (sx == nx) {
            if (ny > sy) {
                res += upper_bound(xs.begin(), xs.end(), mp(nx, ny), lowerX) - lower_bound(xs.begin(), xs.end(), mp(sx, sy), lowerX);
            } else {
                res += upper_bound(xs.begin(), xs.end(), mp(sx, sy), lowerX) - lower_bound(xs.begin(), xs.end(), mp(nx, ny), lowerX);
            }
        } else {
            if (nx > sx) {
                res += upper_bound(ys.begin(), ys.end(), mp(nx, ny), lowerY) - lower_bound(ys.begin(), ys.end(), mp(sx, sy), lowerY);
            } else {
                res += upper_bound(ys.begin(), ys.end(), mp(sx, sy), lowerY) - lower_bound(ys.begin(), ys.end(), mp(nx, ny), lowerY);
            }
        }
        sx = nx;
        sy = ny;
    }
    printf("%lld\n", res);
}
