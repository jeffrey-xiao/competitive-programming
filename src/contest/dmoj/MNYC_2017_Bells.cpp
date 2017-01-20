#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 100000
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int N, Q;

queue<int> freeIndex;
// maps frequency to <count, index>
unordered_map<int, pair<int, int>> hm;
bitset<1000> seg[SIZE * 4];
int freq[SIZE];

void build (int n, int l, int r) {
    if (l == r) {
        seg[n].set(hm[freq[l]].second);
        return;
    }
    int mid = (l + r) >> 1;
    build(n << 1, l, mid);
    build(n << 1 | 1, mid + 1, r);
    seg[n] = seg[n << 1] | seg[n << 1 | 1];
}

void update (int n, int l, int r, int x) {
    if (l == x && x == r) {
        seg[n].reset();
        seg[n].set(hm[freq[l]].second);
        return;
    }
    int mid = (l + r) >> 1;
    if (x <= mid)
        update(n << 1, l, mid, x);
    else
        update(n << 1 | 1, mid + 1, r, x);
    seg[n] = seg[n << 1] | seg[n << 1 | 1];
}

bitset<1000> query (int n, int l, int r, int ql, int qr) {
    if (l == ql && r == qr)
        return seg[n];
    int mid = (l + r) >> 1;
    if (qr <= mid)
        return query(n << 1, l, mid, ql, qr);
    else if (ql > mid)
        return query(n << 1 | 1, mid + 1, r, ql, qr);
    return query(n << 1, l, mid, ql, mid) | query(n << 1 | 1, mid + 1, r, mid + 1, qr);
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d%d", &N, &Q);

    for (int i = 0; i < 1000; i++)
        freeIndex.push(i);

    for (int i = 0; i < N; i++) {
        scanf("%d", &freq[i]);
        if (hm[freq[i]].first == 0) {
            hm[freq[i]] = {1, freeIndex.front()};
            freeIndex.pop();
        } else {
            hm[freq[i]].first++;
        }
    }

    build(1, 0, N - 1);

    for (int i = 0; i < Q; i++) {
        int type;
        scanf("%d", &type);
        if (type == 1) {
            int i, f;
            scanf("%d%d", &i, &f);
            i--;
            hm[freq[i]].first--;
            if (hm[freq[i]].first == 0)
                freeIndex.push(hm[freq[i]].second);
            freq[i] = f;
            if (hm[freq[i]].first == 0) {
                hm[freq[i]].second = freeIndex.front();
                freeIndex.pop();
            }
            hm[freq[i]].first++;
            update(1, 0, N - 1, i);
        } else {
            int l, r;
            scanf("%d%d", &l, &r);
            printf("%d\n", (int)query(1, 0, N - 1, l - 1, r - 1).count());
        }
    }

	return 0;
}
