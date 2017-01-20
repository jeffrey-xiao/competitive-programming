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

int N, M, X, Y, O, E;
map<pair<int, int>, int> vis;
map<int, set<pair<int, int>>> xCoords, yCoords;
queue<pair<pair<int, int>, int>> q;
int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d%d%d", &N, &M, &X, &Y);
    scanf("%d", &O);

    for (int i = 0; i < O; i++) {
        int x, y;
        scanf("%d%d", &x, &y);
        xCoords[x].insert({y, 0});
        yCoords[y].insert({x, 0});
    }

    scanf("%d", &E);

    for (int i = 0; i < E; i++) {
        int x, y;
        scanf("%d%d", &x, &y);
        xCoords[x].insert({y, 1});
        yCoords[y].insert({x, 1});
    }

    q.push({{X, Y}, 0});
    vis[{X, Y}] = 0;
    while (!q.empty()) {
        auto curr = q.front();
        q.pop();
        auto it = xCoords[curr.first.first].upper_bound({curr.first.second, 0});
        if (it != xCoords[curr.first.first].end()) {
            if (it->second == 1) {
                printf("%d\n", curr.second);
                return 0;
            }
            if (!vis.count({curr.first.first, it->first})) {
                vis[{curr.first.first, it->first}] = curr.second + 1;
                q.push({{curr.first.first, it->first}, curr.second + 1});
            }
        }
        it = yCoords[curr.first.second].upper_bound({curr.first.first, 0});
        if (it != yCoords[curr.first.second].end()) {
            if (it->second == 1) {
                printf("%d\n", curr.second);
                return 0;
            }
            if (!vis.count({it->first, curr.first.second})) {
                vis[{it->first, curr.first.second}] = curr.second + 1;
                q.push({{it->first, curr.first.second}, curr.second + 1});
            }
        }
        it = xCoords[curr.first.first].lower_bound({curr.first.second, 0});
        if (it != xCoords[curr.first.first].begin()) {
            it--;
            if (it->second == 1) {
                printf("%d\n", curr.second);
                return 0;
            }
            if (!vis.count({curr.first.first, it->first})) {
                vis[{curr.first.first, it->first}] = curr.second + 1;
                q.push({{curr.first.first, it->first}, curr.second + 1});
            }
        }
        it = yCoords[curr.first.second].lower_bound({curr.first.first, 0});
        if (it != yCoords[curr.first.second].begin()) {
            it--;
            if (it->second == 1) {
                printf("%d\n", curr.second);
                return 0;
            }
            if (!vis.count({it->first, curr.first.second})) {
                vis[{it->first, curr.first.second}] = curr.second + 1;
                q.push({{it->first, curr.first.second}, curr.second + 1});
            }
        }
    }
    printf("-1\n");
	return 0;
}
