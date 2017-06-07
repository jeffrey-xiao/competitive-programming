#include <bits/stdc++.h>
#include <regex>

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

map<vector<string>, string> recipes;
int N, M;

vector<string> split(const string &s, char delim) {
    vector<string> elems;
    stringstream ss(s);
    string val;
    while(getline(ss, val, delim)) {
        elems.push_back(val);
    }
    return elems;
}

string remove (string s, char c) {
    string ret = "";
    for (int i = 0; i < (int)s.size(); i++)
        if (s[i] != c)
            ret += s[i];
    return ret;
}

int main () {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
	// freopen("in.txt", "r", stdin);
	// freopen("out.txt", "w", stdout);

    cin >> N;

    cin.ignore();
    for (int i = 0; i < N; i++) {
        string in;
        getline(cin, in);
        in = remove(in, ' ');
        in = remove(in, '(');
        in = remove(in, ')');
        vector<string> tokens = split(in, '=');
        vector<string> ingredients = split(tokens[0], '+');
        sort(ingredients.begin(), ingredients.end());
        recipes[ingredients] = tokens[1];
    }

    cin >> M;
    cin.ignore();
	for (int i = 0; i < M; i++) {
		string input;
		getline(cin, input);
		input = remove(input, '+');
		vector<string> in = split(input, ' ');
		stack<string> s;

		for (int j = 0; j < (int)in.size(); j++) {
			if (in[j] == "")
				continue;
			if (in[j] != ")")
				s.push(in[j]);
			else {
				vector<string> recipe;
				while (s.top() != "(") {
					recipe.push_back(s.top());
					s.pop();
				}
				s.pop();
				sort(recipe.begin(), recipe.end());
				s.push(recipes[recipe]);
			}
		}
		cout << s.top() << endl;
	}

	return 0;
}
