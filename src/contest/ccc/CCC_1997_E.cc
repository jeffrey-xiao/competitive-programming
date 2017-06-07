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
string divisor, divident;
int quotient[81];

// >=
bool greaterorequal (string a, string b) {
    if (a.length() > b.length())
        return true;
    else if (a.length() < b.length())
        return false;
    for (int i = 0; i < b.length(); i++) {
        if (a[a.length() - b.length() + i] < b[i])
            return false;
        if (b[i] < a[a.length() - b.length() + i])
            return true;
    }
    return true;
}

int getSuccessfulIndex (string a, string b) {
    bool valid = true;
    for (int i = 0; i < b.length(); i++) {
        if (a[i] < b[i]) {
            return 1;
        } else if (a[i] > b[i]) {
            return 0;
        }
    }
    return 0;
}

string subtract (string a, string b, int index) {
    int carry = 0;
    int nextcarry = 0;
    for (int i = 0; i < b.length(); i++) {
        if (a[index - i] - carry < b[b.length() - i - 1]) {
            a[index - i] = a[index - i] - carry - b[b.length() - i - 1] + '0' + 10;
            nextcarry = 1;
        } else {
            a[index - i] = a[index - i] - carry - b[b.length() - i - 1] + '0';
            nextcarry = 0;
        }
        carry = nextcarry;
    }
    for (int i = index - b.length(); i >= 0; i--) {
        if (carry == 1 && a[i] == '0') {
            a[i] = '9';
        } else {
            a[i] -= carry;
        }
    }
    while (a[0] == '0')
        a = a.substr(1, a.length());
    return a;
}

int main () {
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    rint(T);

    for (int i = 0; i < T; i++) {
        cin >> divisor;
        cin >> divident;

        memset(quotient, 0, sizeof quotient);
        while (greaterorequal(divisor, divident)) {
            int index = getSuccessfulIndex(divisor, divident) + divident.length() - 1;
            quotient[divisor.length() - index - 1]++;
            divisor = subtract(divisor, divident, index);
        }
        bool print = false;
        for (int i = 80; i >= 0; i--) {
            if (print || quotient[i] != 0) {
                cout << quotient[i];
                print = true;
            }
        }
        if (!print)
            cout << "0";
        cout << "\n";

        if (divisor == "")
            cout << "0" << endl;
        else
            cout << divisor << "\n";
        cout << "\n";
    }

	return 0;
}
