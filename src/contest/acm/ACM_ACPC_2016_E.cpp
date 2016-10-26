#include <bits/stdc++.h>
using namespace std;
int main () {
    int T;
    cin >> T;
    for (int t = 0; t < T; t++) {
        int r, c;
        cin >> r >> c;
        char g[r][c];
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                cin >> g[r - i - 1][c - j - 1];
        cout << "Test " << (t + 1) << endl;
        for (int i = 0; i < r; i++, cout << endl)
            for (int j = 0; j < c; j++)
                cout << g[i][j];

    }
}
