#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define SIZE 500100
#define l(x) x << 1
#define r(x) x << 1|1
#define m(x, y) (x + y)/2

using namespace std;

typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

struct Node {
    int left, right, cnt;
    Node () {}
    Node (int cnt) {
        this->cnt = cnt;
    }
    Node (int left, int right, int cnt) {
        this->left = left;
        this->right = right;
        this->cnt = cnt;
    }
};

int N;
pi student[SIZE];
vector<int> a, b;

int seg[SIZE], used;
Node nodes[10000000];

int nodeIndex;

int update (int prev, int l, int r, int val, int inc) {
    if (l <= val && val <= r) {
        if (l == r) {
            nodes[nodeIndex].cnt = nodes[prev].cnt + inc;
            return nodeIndex++;
        }
        int mid = (l + r) >> 1;
        int curr = nodeIndex++;
        nodes[curr].cnt = nodes[prev].cnt + inc;
        nodes[curr].left = update(nodes[prev].left, l, mid, val, inc);
        nodes[curr].right = update(nodes[prev].right, mid + 1, r, val, inc);
        return curr;
    }
    return prev;
}

int getSum (int curr, int used, int l, int r, int ql, int qr) {
    if (l == ql && r == qr)
        return nodes[curr].cnt - nodes[used].cnt;

    int mid = (l + r) >> 1;
    if (qr <= mid)
        return getSum(nodes[curr].left, nodes[used].left, l, mid, ql, qr);
    else if (ql > mid)
        return getSum(nodes[curr].right, nodes[used].right, mid + 1, r, ql, qr);
    return getSum(nodes[curr].left, nodes[used].left, l, mid, ql, mid) + getSum(nodes[curr].right, nodes[used].right, mid + 1, r, mid + 1, qr);
}

pi query (int curr, int used, int K, int l, int r, int ql, int qr) {
    int mid = (l + r) >> 1;
    if (K == 0)
        return mp(0, used);

    if (l == ql && r == qr) {
        if (l == r) {
            if (nodes[curr].cnt - nodes[used].cnt <= K) {
                return mp(nodes[curr].cnt - nodes[used].cnt, curr);
            }
            nodes[nodeIndex].cnt = K + nodes[used].cnt;
            nodes[nodeIndex].left = -1;
            nodes[nodeIndex].right = -1;
            return mp(K, nodeIndex++);
        }

        if (nodes[curr].cnt - nodes[used].cnt <= K)
            return mp(nodes[curr].cnt - nodes[used].cnt, curr);
        else {
            int leftSz = nodes[nodes[curr].left].cnt - nodes[nodes[used].left].cnt;
            if (leftSz < K) {
                pi right = query(nodes[curr].right, nodes[used].right, K - leftSz, mid + 1, r, mid + 1, qr);
                nodes[nodeIndex].left = nodes[curr].left;
                nodes[nodeIndex].right = right.second;
                nodes[nodeIndex].cnt = nodes[nodes[curr].left].cnt + nodes[right.second].cnt;
                return mp(leftSz + right.first, nodeIndex++);
            } else {
                pi left = query(nodes[curr].left, nodes[used].left, K, l, mid, ql, mid);
                nodes[nodeIndex].left = left.second;
                nodes[nodeIndex].right = nodes[used].right;
                nodes[nodeIndex].cnt = nodes[left.second].cnt + nodes[nodes[used].right].cnt;
                return mp(K, nodeIndex++);
            }
        }
    }

    if (qr <= mid) {
        pi s = query(nodes[curr].left, nodes[used].left, K, l, mid, ql, qr);
        nodes[nodeIndex].left = s.second;
        nodes[nodeIndex].right = nodes[used].right;
        nodes[nodeIndex].cnt = nodes[s.second].cnt + nodes[nodes[used].right].cnt;
        return mp(s.first, nodeIndex++);
    } else if (ql > mid) {
        pi s = query(nodes[curr].right, nodes[used].right, K, mid + 1, r, ql, qr);
        nodes[nodeIndex].left = nodes[used].left;
        nodes[nodeIndex].right = s.second;
        nodes[nodeIndex].cnt = nodes[nodes[used].left].cnt + nodes[s.second].cnt;
        return mp(s.first, nodeIndex++);
    } else {
        pi left = query(nodes[curr].left, nodes[used].left, K, l, mid, ql, mid);
        pi right = query(nodes[curr].right, nodes[used].right, K - left.first, mid + 1, r, mid + 1, qr);
        nodes[nodeIndex].left = left.second;
        nodes[nodeIndex].right = right.second;
        nodes[nodeIndex].cnt = nodes[left.second].cnt + nodes[right.second].cnt;
        return mp(left.first + right.first, nodeIndex++);
    }
}

void init (int n, int A[], int B[]) {
    N = n;
    a.pb(0);
    b.pb(0);

    for (int i = 0; i < N; i++) {
        a.pb(A[i]);
        a.pb(B[i]);
        student[i] = mp(A[i], B[i]);
    }

    sort(a.begin(), a.end());
    a.erase(unique(a.begin(), a.end()), a.end());

    sort(student, student + N);

    nodes[nodeIndex].left = nodes[nodeIndex].right = nodeIndex;
    seg[0] = nodeIndex++;

    nodes[nodeIndex].left = nodes[nodeIndex].right = nodeIndex;
    used = nodeIndex++;

    int index = 0;

    for (int i = 1; i < (int)a.size(); i++) {
        int prev = seg[i - 1];
        while (index < N && student[index].first <= a[i]) {
            int lo = upper_bound(a.begin(), a.end(), student[index].second) - 1 - a.begin();
            prev = update(prev, 0, a.size() - 1, lo, 1);
            index++;
        }
        seg[i] = prev;
    }
}

bool can (int M, int K[]) {
    int currIndex = nodeIndex;
    nodes[nodeIndex].left = nodes[nodeIndex].right = nodeIndex;
    nodes[nodeIndex].cnt = 0;
    used = nodeIndex++;

    int sz = M;
    int val[sz];

    for (int j = 0; j < sz; j++)
        val[j] = K[j];

    sort(val, val + sz);

    bool valid = true;
    for (int j = 0; j < sz; j++) {
        int curr =  upper_bound(a.begin(), a.end(), val[j]) - 1 - a.begin();
        int next = lower_bound(a.begin(), a.end(), val[j]) - a.begin();

        if (next > (int)a.size() - 1) {
            valid = false;
            break;
        }

        int sum = getSum(seg[curr], used, 0, a.size() - 1, next, a.size() - 1);

        if (sum < val[j]) {
            valid = false;
            break;
        }

        used = query(seg[curr], used, val[j], 0, a.size() - 1, next, a.size() - 1).second;
    }
    nodeIndex = currIndex;
    if (valid) {
        return 1;
    } else {
        return 0;
    }
}

int main () {
    int n, m;
    scanf("%d", &n);
    int A[n], B[n];

    for (int i = 0; i < n; i++)
        scanf("%d%d", &A[i], &B[i]);

    init(n, A, B);

    scanf("%d", &m);

    for (int i = 0; i < m; i++) {
        int cnt;
        scanf("%d", &cnt);
        int K[cnt];
        for (int i = 0; i < cnt; i++)
            scanf("%d", &K[i]);
        printf("%d\n", can(cnt, K));
    }
}
