#include <bits/stdc++.h>

using namespace std;

#define l(x) (x)<<1
#define r(x) (x)<<1|1
#define m(x, y) (x+y)/2

struct node{
    int left, right;
    vector<vector<short> > p;
    node(): p(4, vector<short>(4)){}
};
struct command{
    int r, c, k;
    command(){}
    command(int r_, int c_, int k_){
        r = r_;
        c = c_;
        k = k_;
    }
};
node tree[300005];
command commands[100005];
int n, q;
void getCommand(command c, vector<vector<short> >& newP) {
    short p[4][4];
    short cnt = 0;
    for(int x = 0; x < 4; x++)
        for(int y = 0; y < 4; y++, cnt++){
            p[x][y] = cnt;
            newP[x][y] = cnt;
        }
    for(int x = c.c+1; x < c.c+c.k; x++)
        newP[c.r][x] = p[c.r][x-1];
    for(int x = c.r+1; x < c.r+c.k; x++)
        newP[x][c.c+c.k-1] = p[x-1][c.c+c.k-1];
    for(int x = c.c+c.k-2; x >= c.c; x--)
        newP[c.r+c.k-1][x] = p[c.r+c.k-1][x+1];
    for(int x = c.r+c.k-2; x >= c.r; x--)
        newP[x][c.c] = p[x+1][c.c];
}

void build(int l, int r, int n){
    tree[n].left = l;
    tree[n].right = r;
    if(l == r){
        getCommand(commands[l], tree[n].p);
        return;
    }
    int mid = m(l, r);
    build(l, mid, l(n));
    build(mid+1, r, r(n));
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            short curr = tree[r(n)].p[i][j];
            tree[n].p[i][j] = tree[l(n)].p[curr/4][curr%4];
        }
    }
}

void update(int x, int n, command c){
    if(x == tree[n].left && x == tree[n].right){
        getCommand(c, tree[n].p);
        return;
    }
    int mid = m(tree[n].left, tree[n].right);
    if(x <= mid)
        update(x, l(n), c);
    else
        update(x, r(n), c);
    for(int i = 0; i < 4; i++){
        for(int j = 0; j < 4; j++){
            short curr = tree[r(n)].p[i][j];
            tree[n].p[i][j] = tree[l(n)].p[curr/4][curr%4];
        }
    }
}
int main(){
    scanf("%d%d", &n, &q);
    for(int x = 1; x <= n; x++){
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        commands[x] = command(a-1, b-1, c);
    }
    build(1, n, 1);
    for(int x = 0; x < q; x++){
        int i, a, b, c;
        scanf("%d%d%d%d", &i, &a, &b, &c);
        command com = command(a-1, b-1, c);
        update(i, 1, com);
        vector<vector<short> > newP = tree[1].p;
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 4; y++){
                printf("%d ", newP[x][y]+1);
            }
            printf("\n");
        }
    }
    return 0;
}
