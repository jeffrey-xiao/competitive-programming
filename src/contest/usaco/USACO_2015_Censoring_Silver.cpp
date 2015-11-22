#include <bits/stdc++.h>

using namespace std;

#define SIZE 1000001

int prefix[SIZE];
int dp[SIZE];

void computePrefix (string t) {
    prefix[0] = 0;
    int len = 0;
    for (int i = 1; i < t.size(); i++) {
        int j = prefix[i-1];
        while (j != 0 && t[i] != t[j])
            j = prefix[j-1];
        if (t[i] == t[j])
            j++;
        prefix[i] = j;
    }
}
int main () {
    ofstream out ("censor.out");
    ifstream in ("censor.in");
    string S, T;
    in >> S >> T;
    computePrefix(T);
    int sz = T.size();
    int start = 0;
    string R = "";
    int j = 0;
    dp[0] = 0;
    for (int i = 0; i < S.size(); i++) {
        R += S[i];
        while (j != 0 && S[i] != T[j])
            j = prefix[j-1];
        if (S[i] == T[j]) {
            j++;
            if (j == T.size()) {
                j = dp[R.size() - T.size() - 1];
                R.resize(R.size() - T.size());
            }
        }
        dp[R.size()-1] = j;
   }
    out << R << endl;
    out.close();
    return 0;
}
