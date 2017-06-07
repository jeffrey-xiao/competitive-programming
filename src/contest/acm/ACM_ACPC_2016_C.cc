#include <bits/stdc++.h>
using namespace std;
char forest[20][20];
bool vis[20 * 20 * 36];
int movex[4] = {0, 0, -1, 1};
int movey[4] = {-1, 1, 0, 0};
int N;
int side[6][6] = {
    {0, 3, 5, 2, 4, 0},
    {4, 0, 1, 6, 0, 3},
    {2, 6, 0, 0, 1, 5},
    {5, 1, 0, 0, 6, 2},
    {3, 0, 6, 1, 0, 4},
    {0, 4, 2, 5, 3, 0}
};

int toState (int x, int y, int b, int w) {
    return w + 6 * (b + 6 * (y + 20 * x));
}

void reachable(int x, int y, int b, int w) {
    queue<int> q;
    memset(vis, 0, sizeof vis);
    q.push(toState(x, y, b, w));
    vis[toState(x, y, b, w)] = true;
    while (!q.empty()) {
        int state = q.front();
        q.pop();
        int w = state % 6;
        int b = state / 6 % 6;
        y = state / 6 / 6 % 20;
        x = state / 6 / 6 / 20;
        for (int k = 0; k < 4; k++) {
            int nx = x + movex[k];
            int ny = y + movey[k];
            if (nx < 0 || ny < 0 || nx >= N || ny >= N)
                continue;
            int nb, nw;
            if (k == 0) {
                nb = w;
                nw = 5 - b;
            } else if (k == 1) {
                nb = 5 - w;
                nw = b;
            } else if (k == 2) {
                nw = w;
                nb = side[b][w] - 1;
            } else if (k == 3) {
                nw = w;
                nb = 5 - side[b][w] + 1;
            }
            if (forest[nx][ny] == '*' || vis[toState(nx, ny, nb, nw)])
                continue;
            vis[toState(nx, ny, nb, nw)] = true;
            q.push(toState(nx, ny, nb, nw));
        }
    }
}

int main() {
    int T;
    cin >> T;
    for (int i = 0; i < T; i++) {
        cin >> N;
        int ix, iy, fx, fy;
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < N; k++) {
                cin >> forest[j][k];
                if (forest[j][k] == 'S') {
                    ix = j;
                    iy = k;
                } else if (forest[j][k] == 'H') {
                    fx = j;
                    fy = k;
                }
            }
        }
        bool valid = false;
        reachable(ix, iy, 6 - 1, 5 - 1);
        for (int j = 0; j < 6; j++) {
            valid |= vis[toState(fx, fy, 5 - 1, j)];
        }
        if (valid)
            puts("Yes");
        else
            puts("No");
    }
    return 0;
}
