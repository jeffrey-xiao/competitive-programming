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

int T;
string in;

void subtract (int i) {
    if (in[i] <= in[i - 1]) {
        in[i - 1] = in[i - 1] - in[i] + '0';
    } else {
        in[i - 1] = in[i - 1] - in[i] + '0' + 10;
        int j = i - 2;
        for (; in[j] == '0'; j--)
            in[j] = '9';
        in[j]--;
    }
    in = in.substr(0, in.length() - 1);
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(T);

    for (int t = 0; t < T; t++) {
        cin >> in;
        string orig = in;
        cout << in << "\n";
        while ((in[0] != '0' && in.length() > 2) || (in[0] == '0' && in.length() > 3)) {
            subtract(in.length() - 1);
            bool print = false;
            for (int i = 0; i < in.length(); i++) {
                if (in[i] != '0' || print) {
                    cout << in[i];
                    print = true;
                }
            }
            cout << "\n";
        }
        cout << "The number " << orig;
        if (in[0] != '0' && ((in[0] - '0') * 10 + in[1] - '0') % 11 == 0)
            cout << " is divisible by 11.\n\n";
        else if (in[0] == '0' && ((in[1] - '0') * 10 + in[2] - '0') % 11 == 0)
            cout << " is divisible by 11.\n\n";
        else
            cout << " is not divisible by 11.\n\n";
    }

	return 0;
}
