#include <bits/stdc++.h>

using namespace std;

#define SIZE 10001

int n;
int l[SIZE], r[SIZE], h[SIZE], maxh[1001];

char g[1002][1001];

int main () {
	scanf("%d", &n);
	memset(g, '.', sizeof g);
	int minV = 1 << 30;
	int maxV = 0;
	for (int i = 0; i < n; i++) {
		scanf("%d%d%d", &l[i], &r[i], &h[i]);
		r[i]--;
		minV = min(minV, l[i]);
		maxV = max(maxV, r[i]);
	}
	int ans = 0;
	int maxheight = 0;
	for (int i = 1; i <= 1000; i++) {
		for (int j = 0; j < n; j++)
			if (l[j] <= i && i <= r[j])
				maxh[i] = max(maxh[i], h[j]);
		ans += abs(maxh[i] - maxh[i-1]);
		maxheight = max(maxheight, maxh[i]);
		if (maxh[i] != 0)
			ans++;
		for (int j = min(maxh[i], maxh[i-1]); j <= max(maxh[i], maxh[i-1]); j++) {
			if (maxh[i] >= maxh[i-1])
				g[i][j] = '#';
			else
				g[i-1][j] = '#';
		}
		g[i][maxh[i]] = '#';
		g[i][0] = g[i-1][0] = '*';
	}
	printf("%d\n", ans);
	for (int i = maxheight; i >= 0; i--) {
		for (int j = minV; j <= maxV; j++)
			printf("%c", g[j][i]);
		printf("\n");
	}
	return 0;
}