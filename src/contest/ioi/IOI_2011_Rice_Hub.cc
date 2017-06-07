#include <bits/stdc++.h>

using namespace std;
int n, l;
long long b;
long long rice[100005];
long long sum[100005];
int main(){
	scanf("%d%d%lld",&n, &l, &b);

	sum[0] = 0;
	for(int x = 1; x <= n; x++){
		scanf("%lld",&rice[x]);
		sum[x] = rice[x] + sum[x-1];
	}
	int lo = 1;
	int hi = 1;
	int maxFields = 0;
	while(hi <= n){
		int median = (hi+lo)/2;
		int leftFields = median-lo+1;
		int rightFields = hi-median;
		long long rightCost = sum[hi]-sum[lo+leftFields-1];
		long long leftCost = sum[leftFields+lo-1] - sum[lo-1];
		long long nextCost = leftFields*rice[median] - leftCost + rightCost-rightFields*rice[median];
		if(nextCost <= b){
			maxFields = max(maxFields, hi-lo+1);
			hi++;
		}
		else{
			lo++;
			hi++;
		}
	}
	printf("%d", maxFields);
}
