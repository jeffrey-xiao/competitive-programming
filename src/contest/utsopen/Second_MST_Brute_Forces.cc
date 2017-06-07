#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define VSIZE 50500

using namespace std;
typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

int id[VSIZE];
int size[VSIZE];

void init(){
    for(int x = 0; x < VSIZE; x++)
        id[x] = x;
}

int find(int x){
    return (x == id[x]) ? x : (id[x] = find(id[x]));
}

bool merge(int x, int y){
    int rootx = find(x);
    int rooty = find(y);
    if(rootx == rooty)
        return 0;
    if(size[rootx] > size[rooty]){
        size[rootx] += size[rooty];
        id[rooty] = rootx;
    }else{
        size[rooty] += size[rootx];
        id[rootx] = rooty;
    }
    return 1;
}
int n, m;
int main(){
    scanf("%d%d", &n, &m);
    vector<pair<int, pi>> edges;
    for(int x = 0; x < m; x++){
        int a, b, c;
        scanf("%d%d%d", &a, &b, &c);
        edges.pb(mp(c, mp(a, b)));
    }
    sort(edges.begin(), edges.end());
    int firstMin = 1 << 30;
    int secondMin = 1 << 30;
    for(int x = 0; x < m; x++){
        init();
        int currCost = 0;
        merge(edges[x].second.first, edges[x].second.second);
        currCost += edges[x].first;
        for(int y = 0; y < m; y++){
            if(merge(edges[y].second.first, edges[y].second.second))
                currCost += edges[y].first;
        }
        if(currCost < firstMin){
            secondMin = firstMin;
            firstMin = currCost;
        }else if(currCost < secondMin && currCost != firstMin)
            secondMin = currCost;
    }
    if(secondMin == 1 << 30 || m < n-1)
        cout << -1 << endl;
    else
        cout << secondMin << endl;
}
