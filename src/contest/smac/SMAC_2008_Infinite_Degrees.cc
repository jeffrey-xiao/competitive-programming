#include <bits/stdc++.h>

using namespace std;
#define SIZE 100000
int out[SIZE], label[SIZE], start[SIZE];
bool v[SIZE];
int n, m;
void dfs (int i) {
	v[i] = true;
	int j = out[i];
	if (v[j]) {
		if (label[j] == -1)
			label[i] = j;
		else
			label[i] = -2;
	} else {
		dfs(j);
		if (label[j] != -1 && j != label[j])
			label[i] = label[j];
		else
			label[i] = -2;
	}
	if (label[i] == -2)
		start[i] = start[j];
	else
		start[i] = i;
}
int main () {
	scanf("%d%d", &n, &m);
	for (int i = 0; i < n; i++) {
		label[i] = -1;
		scanf("%d", &out[i]);
	}
	for (int i = 0; i < n; i++)
		if (!v[i])
			dfs(i);
	for (int i = 0; i < m; i++) {
		int a, b;
		scanf("%d%d", &a, &b);
		if (start[a] == start[b])
			printf("1\n");
		else if (label[start[a]] == label[start[b]])
			printf("2\n");
		else
			printf("0\n");
	}
}