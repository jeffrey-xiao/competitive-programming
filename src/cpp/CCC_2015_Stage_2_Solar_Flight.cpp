#include <bits/stdc++.h>

using namespace std;

#define SIZE 2001
#define EPS 0.000001
typedef long long ll;

struct query {
    int index, plane, position;
    query (int index_, int plane_, int position_) {
        index = index_;
        plane = plane_;
        position = position_;
    }
};

struct collision {
    double startPos, endPos;
    int inc, dec;
    ll val;
    collision (double pos, ll val_) {
        startPos = pos;
        val = val_;
    }
    collision (double pos, int inc_, int dec_) {
        startPos = pos;
        inc = inc_;
        dec = dec_;
    }
};

class cmp_query {
    public:
        bool operator() (const query& q1, const query& q2) const {
            return q1.position < q2.position;
        }
};
class cmp_collision {
    public:
        bool operator() (const collision& c1, const collision& c2) const {
            return c1.startPos > c2.startPos;
        }
};

int x, k, n, q;
int a[SIZE], b[SIZE];
ll c[SIZE];
vector<deque<collision>> maxState (SIZE);
ll curr[SIZE];
priority_queue<collision, vector<collision>, cmp_collision> pq;

void add (collision c, int plane) {
    while (!maxState[plane].empty() && (maxState[plane].back().val <= c.val || abs(maxState[plane].back().startPos - c.startPos) <= EPS))
        maxState[plane].pop_back();
    maxState[plane].push_back(c);
}

int main () {
    scanf("%d%d%d%d", &x, &k, &n, &q);
    for (int i = 0; i < n; i++) {
        maxState[i].push_back(collision(0, 0));
        scanf("%d%d%lld", &a[i], &b[i], &c[i]);
    }
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (a[i] > a[j])
                pq.push(collision(0.0, j, i));
            else
                pq.push(collision(0.0, i, j));
            double s1 = (double)(b[i] - a[i])/x;
            double s2 = (double)(b[j] - a[j])/x;
            double cx = (double)(a[j] - a[i])/(s1 - s2);
            if (0 <= cx && cx <= x) {
                if (b[i] > b[j])
                    pq.push(collision(cx, j, i));
                else
                    pq.push(collision(cx, i, j));
            }
        }
    }
    vector<query> queries;
    for (int i = 0; i < q; i++) {
        int plane, pos;
        scanf("%d%d", &plane, &pos);
        queries.push_back(query(i, plane-1, pos));
    }
    sort(queries.begin(), queries.end(), cmp_query());
    ll ans[q];
    for (query qu : queries) {
        while (!pq.empty() && pq.top().startPos <= qu.position + k) {
            collision next = pq.top();
            pq.pop();
            curr[next.inc] += c[next.dec];
            maxState[next.inc].back().endPos = next.startPos;
            add(collision(next.startPos, curr[next.inc]), next.inc);
            if (next.startPos != 0) {
                curr[next.dec] -= c[next.inc];
                maxState[next.dec].back().endPos = next.startPos;
                add(collision(next.startPos, curr[next.dec]), next.dec);
            }
        }
        //for (collision co : maxState[qu.plane]) {
        //    printf("(%f, %d) ", co.startPos, co.val);
        //}
        while (maxState[qu.plane].size() > 1 && maxState[qu.plane][0].endPos <= qu.position)
            maxState[qu.plane].pop_front();
        ans[qu.index] = maxState[qu.plane].front().val;
    }
    for (int i = 0; i < q; i++)
        printf("%lld\n", ans[i]);
    return 0;
}
