#include <bits/stdc++.h>
#define SIZE 200002
using namespace std;

int n;
bool mp[SIZE];
int h[SIZE];
vector<vector<int>> adj(SIZE);

int main () {
	scanf("%d", &n);
	memset(mp, false, sizeof mp);
	for (int i = 0; i < 5; i++) {
		int j;
		scanf("%d", &j);
		for (; j > 0; j--) {
			int a, b;
			scanf("%d%d", &a, &b);
			a--, b--;
			bool exists = false;
			for (int k : adj[a]) {
				if (k == b)
					exists = true;
			}
			if (exists)
				continue;
			adj[a].push_back(b);
			adj[b].push_back(a);
		}
	}
	for (int i = 0; i < n; i++) 
		h[i] = i;
    for (int i = 0; i < n; i++) {
        swap(h[i], h[i+rand()%(n-i)]);
    }
	bool change = true;
	while (change) {
		change = false;
		for (int x = 0; x < n; x++) {
			int i = h[x];
			int cnt = 0;
			for (int j : adj[i]) {
				if (mp[i] == mp[j])
					cnt++;
			}
			if (cnt > 2) {
				change = true;
				mp[i] = !mp[i];
			}
		}
	}
	for (int i = 0; i < n; i++)
		printf("%c", mp[i] ? 'A' : 'B');
	printf("\n");
}