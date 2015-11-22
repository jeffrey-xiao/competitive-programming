#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 100005
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct node {
    int l, r, val, minVal, gcf, cnt;
} tree[3*SIZE];

int n, m;
int a[SIZE];

int gcf (int a, int b) {
    return b == 0 ? a : gcf(b, a%b);
}

void pushUp (int i) {
    tree[i].minVal = min(tree[l(i)].minVal, tree[r(i)].minVal);
    tree[i].gcf = gcf(tree[l(i)].gcf, tree[r(i)].gcf);
    tree[i].cnt = 0;
    if (tree[i].gcf == tree[l(i)].gcf) {
        tree[i].cnt += tree[l(i)].cnt;
    }
    if (tree[i].gcf == tree[r(i)].gcf) {
        tree[i].cnt += tree[r(i)].cnt;
    }
}
void build (int l, int r, int i) {
    tree[i].l = l;
    tree[i].r = r;
    if (l == r) {
        tree[i].minVal = tree[i].gcf = a[l];
        tree[i].cnt = 1;
        return;
    }
    int mid = m(l, r);
    build(l, mid, l(i));
    build(mid+1, r, r(i));
    pushUp(i);
}
void update (int x, int v, int i) {
    if (tree[i].l == x && tree[i].r == x) {
        tree[i].minVal = tree[i].gcf = v;
        tree[i].cnt = 1;
        return;
    }
    int mid = m(tree[i].l, tree[i].r);
    if (x <= mid)
        update(x, v, l(i));
    else
        update(x, v, r(i));
    pushUp(i);
}
int getMin (int l, int r, int i) {
    if (tree[i].l == l && tree[i].r == r) {
        return tree[i].minVal;
    }
    int mid = m(tree[i].l, tree[i].r);
    if (r <= mid)
        return getMin(l, r, l(i));
    else if (l > mid)
        return getMin(l, r, r(i));
    return min(getMin(l, mid, l(i)), getMin(mid+1, r, r(i)));
}
pi getGCF (int l, int r, int i) {
    if (tree[i].l == l && tree[i].r == r) {
        return mp(tree[i].gcf, tree[i].cnt);
    }
    int mid = m(tree[i].l, tree[i].r);
    if (r <= mid)
        return getGCF(l, r, l(i));
    else if (l > mid)
        return getGCF(l, r, r(i));
    pi first = getGCF(l, mid, l(i));
    pi second = getGCF(mid+1, r, r(i));
    int GCF = gcf(first.first, second.first);
    int cnt = 0;
    if (GCF == first.first)
        cnt += first.second;
    if (GCF == second.first)
        cnt += second.second;
    return mp(GCF, cnt);
}
int main () {
    scanf("%d%d", &n, &m);
    for (int i = 1; i <= n; i++)
        scanf("%d", &a[i]);
    build(1, n, 1);
    for (int i = 0; i < m; i++) {
        char c;
        int a, b;
        scanf(" %c%d%d", &c, &a, &b);
        if (c == 'C') {
            update(a, b, 1);
        } else if (c == 'M') {
            printf("%d\n", getMin(a, b, 1));
        } else if (c == 'G') {
            printf("%d\n", getGCF(a, b, 1).first);
        } else {
            printf("%d\n", getGCF(a, b, 1).second);
        }
    }
}
