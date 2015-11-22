#include <bits/stdc++.h>

using namespace std;

#define r(x) x<<1|1
#define l(x) x<<1
#define m(x, y) (x+y)/2
#define INF 2<<30

struct node{
    int left, right;
    int min, max;
    bool propagate;
};
node tree[6000005];
int n, k;

void pushUp(int n){
    //update current node with information from children
    tree[n].min = min(tree[l(n)].min, tree[r(n)].min);
    tree[n].max = max(tree[l(n)].max, tree[r(n)].max);
}

void pushDown(int n){
    //update children with information from current node
    tree[l(n)].min = tree[r(n)].min = tree[n].min;
    tree[l(n)].max = tree[r(n)].max = tree[n].min;
    tree[l(n)].propagate = true;
    tree[r(n)].propagate = true;
    tree[n].propagate = false;
}

void build(int l, int r, int n){
    tree[n].left = l;
    tree[n].right = r;
    tree[n].propagate = false;
    tree[n].min = tree[n].max = 0;
    if(l == r){
        return;
    }
    int mid = m(l, r);
    build(l, mid, l(n));
    build(mid+1, r, r(n));
}

void update(int l, int r, int n, bool isRemove, int limit){
    if(tree[n].min == tree[n].max && tree[n].left == l && tree[n].right == r){
        if(isRemove){
            tree[n].min = min(tree[n].min, limit);
            tree[n].max = tree[n].min;
        }else{
            tree[n].max = max(tree[n].max, limit);
            tree[n].min = tree[n].max;
        }
        tree[n].propagate = true;
        return;
    }
    int mid = m(tree[n].left, tree[n].right);

    if(tree[n].propagate)
        pushDown(n);

    if(r <= mid)
        update(l, r, l(n), isRemove, limit);
    else if(l > mid && mid != 0)
        update(l, r, r(n), isRemove, limit);
    else if(mid != 0)
        update(l, mid, l(n), isRemove, limit),
        update(mid+1, r, r(n), isRemove, limit);
    pushUp(n);
}

void query(int n){
    if(tree[n].min == tree[n].max){
        for(int x = tree[n].left; x <= tree[n].right; x++)
            printf("%d\n",tree[n].min);
        return;
    }
    query(l(n));
    query(r(n));
}

int main(){
    scanf("%d%d", &n, &k);
    build(1, n, 1);
    for(int x = 0; x < k; x++){
        int id, a, b, c;

        scanf("%d%d%d%d", &id, &a, &b, &c);
        ++a;
        ++b;
        update(a, b, 1, id == 2, c);
    }
    query(1);
    return 0;
}
