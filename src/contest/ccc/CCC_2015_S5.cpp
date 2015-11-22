// This problem has a very elegant dynamic programming solution

// We can make the observation that the the pies of group M can be inserted into the pies of group N

// If we sort the M pies in increasing order, we can notice that we will choose the low sugar content pies
// to "skip" and the high sugar content pies to "take


// Now let us define our DP state to be dp[i][j][k][b] where i represents the position of the
// current pie from N you are on, j-k represents the interval of M you can take/skip, and b represents
// if you eat a pie or not

// This leads us to two different scenarios

// Case 1: You are able to eat a pie
//  - Eat the current pie from N and increment i
//  - Eat the pie with greatest sugar content (pie j) and decrement j
//  - Skip the current pie from N and increment i
//  - Skip the pie with least sugar content (pie i) and increment i
// Case 2: You cannot eat a pie
//  - Skip the current pie from N and increment i
//  - Skip the pie with least sugar content (pie i) and increment i


#include <bits/stdc++.h>

using namespace std;


int n, m;
int N[3001], M[101];
int dp[3002][102][102][2];

int compute (int i, int j1, int j2, int b) {
    if (dp[i][j1][j2][b] != -1)
        return dp[i][j1][j2][b];
    int& ref = dp[i][j1][j2][b];
    if (i == n + 1 && j1 > j2)
        return (ref = 0);
    if (b == 0) {
        // if you can eat a pie, either take from the current position in N
        // or the last pie in the interval [j1, j2]
        if (i != n+1)
            ref = max(ref, N[i] + compute(i+1, j1, j2, 1));
        if (j1 <= j2)
            ref = max(ref, M[j2] + compute(i, j1, j2-1, 1));
    }
    // if you cannot eat a pie, either skip from the current position in N
    // or the first pie in the interval [j1, j2]
    if (i != n+1)
        ref = max(ref, compute(i+1, j1, j2, 0));
    if (j1 <= j2)
        ref = max(ref, compute(i, j1+1, j2, 0));
    return ref;
}
int main (){
    scanf("%d", &n);
    for (int i = 1; i <= n; i++)
        scanf("%d", &N[i]);
    scanf("%d", &m);
    for (int i = 1; i <= m; i++)
        scanf("%d", &M[i]);
    sort(M, M + m+1);
    for (int i = 0; i < 3001; i++)
        for (int j = 0; j < 101; j++)
            for (int k = 0; k < 101; k++)
                for (int l = 0; l < 2; l++)
                    dp[i][j][k][l] = -1;
    printf("%d\n", compute(1, 1, m, 0));
    return 0;
}
