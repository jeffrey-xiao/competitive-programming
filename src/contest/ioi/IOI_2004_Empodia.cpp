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

int N;
int hi[SIZE], lo[SIZE], val[SIZE];

vector<vector<int>> poss (SIZE * 2);
set<pi> intervals;

struct Event {
    int x, index, type;
    Event (int x, int type, int index) {
        this->x = x;
        this->type = type;
        this->index = index;
    }

    bool operator < (const Event& e) const {
        if (x == e.x && type == e.type)
            return e.index > index;
        if (x == e.x)
            return type > e.type;
        return x > e.x;
    }
};

void sweep (vector<int> indexes) {
    priority_queue<Event> pq;
    for (int i = 0; i < indexes.size(); i++) {
        pq.push(Event(lo[val[indexes[i]]] + 1, 1, indexes[i]));
        pq.push(Event(hi[val[indexes[i]]], -1, indexes[i]));
    }

    set<int> active;
    while (!pq.empty()) {
        Event curr = pq.top();
        pq.pop();

        if (curr.type == 1)
            active.insert(curr.index);
        else if (curr.type == -1) {
            active.erase(curr.index);
            auto lower = active.upper_bound(curr.index);
            if (lower != active.begin() && (*(--lower)) > lo[val[curr.index]]) {
                pi add = mp((*lower), curr.index);
                auto prev = intervals.upper_bound(add);
                auto next = intervals.lower_bound(add);
                bool intersectPrev = true;
                bool intersectNext = true;

                if (prev != intervals.begin()) {
                    prev--;
                    if (max(add.first, (*prev).first) < min(add.second, (*prev).second)) {
                        if (add.second - add.first <= (*prev).second - (*prev).first) {
                            intervals.erase(prev);
                            intersectPrev = false;
                        }
                    } else {
                        intersectPrev = false;
                    }
                } else {
                    intersectPrev = false;
                }

                if (next != intervals.end()) {
                    if (max(add.first, (*next).first) < min(add.second, (*next).second)) {
                        if (add.second - add.first <= (*next).second - (*next).first) {
                            intervals.erase(next);
                            intersectNext = false;
                        }
                    } else {
                        intersectNext = false;
                    }
                } else {
                    intersectNext = false;
                }
                if (!intersectPrev && !intersectNext)
                    intervals.insert(add);
            }
        }
    }
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d", &N);

    for (int i = 0; i < N; i++) {
        scanf("%d", &val[i]);
        poss[val[i] - i + N - 1].pb(i);
    }

    // value, index
    stack<pi> s;

    for (int i = N - 1; i >= 0; i--) {
        while (!s.empty() && val[i] < s.top().first)
            s.pop();
        if (s.empty())
            hi[val[i]] = N;
        else
            hi[val[i]] = s.top().second;
        s.push(mp(val[i], i));
    }

    while (!s.empty())
        s.pop();

    for (int i = 0; i < N; i++) {
        while (!s.empty() && val[i] > s.top().first)
            s.pop();
        if (s.empty())
            lo[val[i]] = -1;
        else
            lo[val[i]] = s.top().second;
        s.push(mp(val[i], i));
    }

    for (int i = 0; i < 2 * N; i++)
        sweep(poss[i]);

    printf("%d\n", intervals.size());
    for (pi i : intervals)
        printf("%d %d\n", i.first + 1, i.second + 1);

	return 0;
}
