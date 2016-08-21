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

ll N, L, B, R;
ll lWhite, lRed, bWhite, bRed, rRed, rWhite;

unordered_map<ll, array<ll, 3>> memo;
unordered_set<string> vis;

bool valid (array<ll, 3> sz1, array<ll, 3> sz2, ll diff) {
    if (sz1[0] != sz2[0] && diff != 1 && (abs(sz1[0] - sz2[0])) % (diff - 1) != 0)
        return false;
    if (sz1[1] != sz2[1] && diff != 1 && (abs(sz1[1] - sz2[1])) % (diff - 1) != 0)
        return false;
    if (sz1[2] != sz2[2] && diff != 1 && (abs(sz1[2] - sz2[2])) % (diff - 1) != 0)
        return false;
    return true;
}

long getSize (ll a, ll b, ll x, ll y, ll beg, ll end) {
    return (a + b) * abs(b - a) + 1 / 2 + (x - beg) * a + (end - y) * b;
}

array<ll, 3> toSize (array<ll, 3> row) {
    return array<ll, 3>{row[0], row[1] - row[0], row[2] - row[1]};
}

string toString (array<ll, 3> row) {
    array<ll, 3> sz = toSize(row);
    string ret = "";

    if (sz[0] > 0)
        ret += "L";
    if (sz[1] > 0)
        ret += "B";
    if (sz[2] > 0)
        ret += "R";

    return ret;
}

ll dist (ll r1, ll c1, ll r2, ll c2) {
    return abs(r1 - r2) + abs(c1 - c2);
}

array<ll, 3> getRow (ll row) {
    if (memo.count(row))
        return memo[row];

    array<ll, 3> ret;

    // getting last L occurrence
    ll lo, hi, mid;

    lo = 1;
    hi = 3 * N;

    while (lo <= hi) {
        mid = (lo + hi) >> 1;
        ll distL = dist(L, 1, row, mid);
        ll distB = dist(2 * N, B, row, mid);
        ll distR = dist(R, 3 * N, row, mid);

        if (distL <= distB && distL <= distR)
            lo = mid + 1;
        else
            hi = mid - 1;
    }

    ret[0] = hi;

    // getting last B occurrence
    lo = hi + 1;
    hi = 3 * N;

    while (lo <= hi) {
        mid = (lo + hi) >> 1;
        ll distL = dist(L, 1, row, mid);
        ll distB = dist(2 * N, B, row, mid);
        ll distR = dist(R, 3 * N, row, mid);

        if (distL <= distB && distL <= distR || (distB <= distR && distB <= distL))
            lo = mid + 1;
        else
            hi = mid - 1;
    }
    ret[1] = hi;
    ret[2] = 3 * N;
    memo[row] = ret;
    return ret;
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    scanf("%lld%lld%lld%lld", &N, &L, &B, &R);

    // doing white
    long lo = 1, hi = N;
    while (lo <= hi) {
        vis.insert(toString(getRow(lo)));

        ll innerLo = lo;
        ll innerHi = hi;
        while (innerLo <= innerHi) {
            ll mid = (innerLo + innerHi) >> 1;
            if (vis.count(toString(getRow(mid))))
                innerLo = mid + 1;
            else
                innerHi = mid - 1;
        }

        ll beg = lo; 	  // first row of segment
        ll end = innerHi; // last row of segment

        lo = innerHi + 1;

        innerLo = beg;
        innerHi = end;
        while (innerLo <= innerHi) {
            ll l = innerLo;
            ll r = innerHi;
            array<ll, 3> firstSize = toSize(getRow(innerLo));

            while (l <= r) {
                ll mid = (l + r) >> 1;
                ll diff = mid - innerLo + 1;
                array<ll, 3> currSize = toSize(getRow(mid));

                if (valid(currSize, firstSize, diff))
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            // first row of segment with same size as l = innerLo
            // last row of segment with same size as l = r

            array<ll, 3> lastSize = toSize(getRow(r));
            lWhite += (firstSize[0] + lastSize[0]) * (r - innerLo + 1) / 2;
            bWhite += (firstSize[1] + lastSize[1]) * (r - innerLo + 1) / 2;
            rWhite += (firstSize[2] + lastSize[2]) * (r - innerLo + 1) / 2;

            innerLo = r + 1;
        }
    }
    vis.clear();
    memo.clear();
    // doing red
    hi = 2 * N;
    while (lo <= hi) {
        vis.insert(toString(getRow(lo)));

        ll innerLo = lo;
        ll innerHi = hi;
        while (innerLo <= innerHi) {
            ll mid = (innerLo + innerHi) >> 1;
            if (vis.count(toString(getRow(mid))))
                innerLo = mid + 1;
            else
                innerHi = mid - 1;
        }

        ll beg = lo; 	  // first row of segment
        ll end = innerHi; // last row of segment

        lo = innerHi + 1;

        innerLo = beg;
        innerHi = end;
        while (innerLo <= innerHi) {
            ll l = innerLo;
            ll r = innerHi;

            array<ll, 3> firstSize = toSize(getRow(innerLo));

            while (l <= r) {
                ll mid = (l + r) >> 1;
                ll diff = mid - innerLo + 1;
                array<ll, 3> currSize = toSize(getRow(mid));

                if (valid(currSize, firstSize, diff))
                    l = mid + 1;
                else
                    r = mid - 1;
            }
            // first row of segment with same size as l = innerLo
            // last row of segment with same size as l = r

            array<ll, 3> lastSize = toSize(getRow(r));
            lRed += (firstSize[0] + lastSize[0]) * (r - innerLo + 1) / 2;
            bRed += (firstSize[1] + lastSize[1]) * (r - innerLo + 1) / 2;
            rRed += (firstSize[2] + lastSize[2]) * (r - innerLo + 1) / 2;

            innerLo = r + 1;
        }
    }
    printf("%lld %lld %lld %lld %lld %lld\n", lWhite, lRed, bWhite, bRed, rWhite, rRed);

	return 0;
}
