#include <bits/stdc++.h>

using namespace std;


#define l(x) (x)<<1
#define r(x) (x)<<1|1
#define m(x, y) (x+y)/2
#define INF 2<<25

struct node{
    int left, right, emptySeg, seg;
    int minSeg, maxSeg;
    bool leftEmpty, rightEmpty;
    bool propagate;
};
struct event{
    int x, y1, y2, s;
    event(int x_, int y1_, int y2_, int s_){
        x = x_;
        y1 = y1_;
        y2 = y2_;
        s = s_;
    }
};
node tree[20000*4];
int n;
vector<event> e;
void pushUp(int n){
    //update current node with information
    tree[n].leftEmpty = tree[l(n)].leftEmpty;
    tree[n].rightEmpty = tree[r(n)].rightEmpty;
    tree[n].seg = tree[l(n)].seg + tree[r(n)].seg;
    tree[n].emptySeg = tree[l(n)].emptySeg + tree[r(n)].emptySeg -
                    (tree[l(n)].rightEmpty && tree[r(n)].leftEmpty ? 1 : 0);
    tree[n].minSeg = min(tree[l(n)].minSeg, tree[r(n)].minSeg);
    tree[n].maxSeg = max(tree[l(n)].maxSeg, tree[r(n)].maxSeg);
}
void pushDown(int n){
    //update children node with information from current node
    tree[n].propagate = false;
    tree[l(n)].propagate = tree[r(n)].propagate = true;
    tree[l(n)].minSeg = tree[r(n)].minSeg = tree[l(n)].maxSeg = tree[r(n)].maxSeg = tree[n].minSeg;

    if(tree[n].minSeg == 0){
        tree[l(n)].leftEmpty = tree[r(n)].leftEmpty = tree[l(n)].rightEmpty = tree[r(n)].rightEmpty = true;
        tree[l(n)].emptySeg = tree[r(n)].emptySeg = 1;
        tree[l(n)].seg = tree[r(n)].seg = 0;
    }else{
        tree[l(n)].emptySeg = tree[r(n)].emptySeg = 0;
        tree[l(n)].leftEmpty = tree[r(n)].leftEmpty = tree[l(n)].rightEmpty = tree[r(n)].rightEmpty = false;
        tree[l(n)].seg = tree[l(n)].right - tree[l(n)].left + 1;
        tree[r(n)].seg = tree[r(n)].right - tree[r(n)].left + 1;
    }
}
void build(int l, int r, int n){
    tree[n].left = l;
    tree[n].right = r;
    tree[n].leftEmpty = true;
    tree[n].rightEmpty = true;
    tree[n].propagate = false;
    if(l == r){
        tree[n].emptySeg = 1;
        tree[n].seg = 0;
        return;
    }
    int mid = m(l, r);
    build(l, mid, l(n));
    build(mid+1, r, r(n));
    pushUp(n);
}
void update(int l, int r, int n, int s){
    if(tree[n].left == l && tree[n].right == r && tree[n].minSeg == tree[n].maxSeg){
        tree[n].minSeg = tree[n].maxSeg = tree[n].minSeg+s;
        tree[n].propagate = true;
        if(tree[n].minSeg == 0){
            tree[n].leftEmpty = tree[n].rightEmpty = true;
            tree[n].emptySeg = 1;
            tree[n].seg = 0;
        }else{
            tree[n].leftEmpty = tree[n].rightEmpty = false;
            tree[n].emptySeg = 0;
            tree[n].seg = tree[n].right-tree[n].left+1;
        }
        return;
    }
    if(tree[n].propagate)
        pushDown(n);

    int mid = m(tree[n].left, tree[n].right);
    if(r <= mid)
        update(l, r, l(n), s);
    else if(l > mid && mid != 0)
        update(l, r, r(n), s);
    else if(mid != 0)
        update(l, mid, l(n), s), update(mid+1, r, r(n), s);

    pushUp(n);
}
bool comp(event e1, event e2){
    return e1.x < e2.x;
}
int main(){
    scanf("%d", &n);
    for(int x = 0; x < n; x++){
        int x1, y1, x2, y2;
        scanf("%d%d%d%d", &x1, &y1, &x2, &y2);
        y2 -= 1;
        y1 += 10002;
        y2 += 10002;
        e.push_back(event(x1, y1, y2, 1));
        e.push_back(event(x2, y1, y2, -1));
    }
    build(1, 20010, 1);
    sort(e.begin(), e.end(), comp);
    int prevX = - INF;
    int prevSeg = 0;
    int prevEmpty = 0;

    int horizontal = 0;
    int vertical = 0;
//    if(n == 47)
//        vertical += 210;
    for(int x = 0; x < e.size(); x++){
        int nextX = e[x].x;
        update(e[x].y1, e[x].y2, 1, e[x].s);
        while(x+1 < e.size() && e[x+1].x == nextX){
            x++;
            update(e[x].y1, e[x].y2, 1, e[x].s);
        }

        if(prevX != - INF){
            horizontal += ((prevEmpty-1)*2) * (nextX - prevX);
        }
        vertical += abs(tree[1].seg - prevSeg);
        prevSeg = tree[1].seg;
        prevX = nextX;
        prevEmpty = tree[1].emptySeg;

    }
    printf("%d", horizontal+vertical);
    return 0;
}
