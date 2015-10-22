#include <bits/stdc++.h>

using namespace std;

int main () {      //A B C D E F G H I
    int mm[9][9] = {{1,1,0,1,1,0,0,0,0},
                    {1,1,1,0,0,0,0,0,0},
                    {0,1,1,0,1,1,0,0,0},
                    {1,0,0,1,0,0,1,0,0},
                    {0,1,0,1,1,1,0,1,0},
                    {0,0,1,0,0,1,0,0,1},
                    {0,0,0,1,1,0,1,1,0},
                    {0,0,0,0,0,0,1,1,1},
                    {0,0,0,0,1,1,0,1,1}};
    int curr[9];
    for (int j = 0; j < 12; j++) {
        printf("%d\n", j);
        for (int k = 0; k < 12; k++) {
            for (int l = 0; l < 12; l++) {
                for (int m = 0; m < 12; m++) {
                    for (int n = 0; n < 12; n++) {
                        for (int o = 0; o < 12; o++) {
                            for (int p = 0; p < 12; p++) {
                                for (int q = 0; q < 12; q++) {
                                    for (int r = 0; r < 12; r++) {
                                        int sum = 0;
                                        for (int y = 0; y < 9; y++) {
                                            curr[y] = mm[0][y] * j + mm[1][y] * k + mm[2][y] * l + mm[3][y] * m + mm[4][y] * n + mm[5][y] * o + mm[6][y] * p + mm[7][y] * q + mm[8][y] * r;
                                            curr[y] %= 12;
                                            sum += curr[y];
                                        }
                                        if (sum == 1) {
                                            for (int y = 0; y < 9; y++)
                                                printf("%d ", curr[y]);
                                            printf("\nCODE: %d %d %d %d %d %d %d %d %d\n", j, k, l, m, n, o, p, q, r);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
