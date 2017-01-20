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

int Q;
unordered_map<int, pi> link;
int head = -1, tail = -1;
int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%d", &Q);

    for (int i = 0; i < Q; i++) {
        char c;
        int val;
        scanf(" %c%d", &c, &val);
        if (c == 'F') {
            if (head != -1)
                link[head] = {val, link[head].second};
            link[val] = {-1, head};
            head = val;
            if (tail == -1)
                tail = val;
        } else if (c == 'E') {
            if (tail != -1)
                link[tail] = {link[tail].first, val};
            link[val] = {tail, -1};
            tail = val;
            if (head == -1)
                head = val;
        } else if (c == 'R') {
            pi n = link[val];
            if (n.first != -1)
                link[n.first].second = n.second;
            if (n.second != -1)
                link[n.second].first = n.first;
            if (head == val)
                head = n.second;
            if (tail == val)
                tail = n.first;
            if (head == -1)
                head = tail;
            if (tail == -1)
                tail = head;
        }
    }

    while (head != -1) {
        printf("%d\n", head);
        head = link[head].second;
    }
	return 0;
}
