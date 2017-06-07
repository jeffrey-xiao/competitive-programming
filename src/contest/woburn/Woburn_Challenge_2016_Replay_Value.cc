#include <bits/stdc++.h>

using namespace std;

#define MAX_N 500000

typedef long long LL;

int N;
int D[MAX_N];
bool exclude[MAX_N];
vector<int> adj[MAX_N];

int getOutgoing (int u, int par) {
	int ret = 1;
	for (int v : adj[u])
		if (v != par && !exclude[v] && D[u] >= D[v])
			ret += getOutgoing(v, u);
	return ret;
}

int getIncoming (int u, int par) {
	int ret = 1;
	for (int v : adj[u])
		if (v != par && !exclude[v] && D[u] <= D[v])
			ret += getIncoming(v, u);
	return ret;
}

int getCentroid (int u, int par, int treeSize) {
	int n = treeSize;
	int sz = 1;
	bool valid = true;
	for (int v : adj[u]) {
		if (v == par || exclude[v])
			continue;
		int ret = getCentroid(v, u, treeSize);
		if (ret >= 0)
			return ret;
		valid &= -ret <= n / 2;
		sz += -ret;
	}
	valid &= n - sz <= n / 2;
	return valid ? u : -sz;
}
int getSize (int u, int par) {
	int sz = 1;
	for (int v : adj[u])
		if (v != par && !exclude[v])
			sz += getSize(v, u);
	return sz;
}

int main () {
	scanf("%d", &N);

	for (int i = 0; i < N; i++)
		scanf("%d", D + i);

	for (int i = 0; i < N - 1; i++) {
		int u, v;
		scanf("%d%d", &u, &v);
		u--, v--;
		adj[u].push_back(v);
		adj[v].push_back(u);
	}

	queue<int> q;
	q.push(0);
	LL ans = 0;

	while (!q.empty()) {
		int u = q.front();
		q.pop();
		u = getCentroid(u, -1, getSize(u, -1));

		LL outgoing = 0, incoming = 0;

		for (int v : adj[u]) {
			if (exclude[v])
				continue;
			LL currOutgoing = 0;
			LL currIncoming = 0;

			if (D[u] >= D[v])
				currOutgoing = getOutgoing(v, u);
			if (D[u] <= D[v])
				currIncoming = getIncoming(v, u);

			ans += currOutgoing + currIncoming;
			ans += currOutgoing * incoming + outgoing * currIncoming;

			outgoing += currOutgoing;
			incoming += currIncoming;
		}

		exclude[u] = true;
		for (int v : adj[u])
			if (!exclude[v])
				q.push(v);
	}

	printf("%lld\n", ans);
	return 0;
}
