#include <bits/stdc++.h>

using namespace std;

int r, c;
int board[16][16];

void press(int(&ch)[16][16], int x, int y){
    ch[x][y] ^= 1;
    if(x - 1 >= 0)
        ch[x-1][y] ^= 1;
    if(x + 1 < r)
        ch[x+1][y] ^= 1;
    if(y - 1 >= 0)
        ch[x][y-1] ^= 1;
    if(y + 1 < c)
        ch[x][y+1] ^= 1;
}

int main(){
    for(int t = 5; t > 0; t--){
        scanf("%d%d", &r, &c);
        for(int x = 0; x < r; x++){
            for(int y = 0; y < c; y++){
                scanf(" %c", &board[x][y]);
                board[x][y] -= '0';
            }
        }
        int minV = 1 << 30;
        for(int x = 0; x < 1 << c; x++){
            int curr[16][16];
            int counter = 0;
            memcpy(&curr, &board, sizeof(board));
            for(int y = 0; y < c; y++){
                if(x & 1 << y){
                    press(curr, 0, y);
                    counter++;
                }
            }
            for(int i = 1; i < r; i++){
                for(int j = 0; j < c; j++){
                    if(curr[i-1][j] == 1){
                        press(curr, i, j);
                        counter++;
                    }
                }
            }
            bool success = true;
            for(int j = 0; j < c; j++){
                if(curr[r-1][j] == 1){
                    success = false;
                    break;
                }
            }
            if(success)
                minV = min(minV, counter);
        }
        if(minV == 1 << 30)
            printf("-1\n");
        else
            printf("%d\n", minV);
    }
    return 0;
}
