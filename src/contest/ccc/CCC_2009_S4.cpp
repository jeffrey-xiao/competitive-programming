#include <bits/stdc++.h>
using namespace std;


int n, m, k;
const int INF = 1000000000;
const int SIZE = 5000;
vector< vector< pair <int, int> > > adj (SIZE);
int minValue[SIZE];

inline int scan (int &x) {
    char c;
    while (c = getchar(), c < '0' || c > '9'); x = c - '0';
    while (c = getchar(), c >= '0' && c <= '9')
        x = x*10 + c - '0';
}

struct comp{
    bool operator()(pair<int, int> p1, pair<int, int> p2){
        return p1.second > p2.second;
    }
};

int main(){
    scan(n);
	scan(m);
    for(int x = 0; x < m; x++){
        int a, b, c;
        scan(a), scan(b), scan(c);
        a = a-1;
        b = b-1;
        adj[a].push_back(make_pair(b, c));
        adj[b].push_back(make_pair(a, c));
    }
    for(int x = 0; x < n; x++)
        minValue[x] = INF;
    //FIRST IS INDEX, SECOND IS CURRENT COST
    priority_queue<pair<int, int>, vector<pair<int, int> >, comp > pq;

    scan(k);

    for(int x = 0; x < k; x++){
        int a, b;
        scan(a), scan(b);
        a--;
        if(b < minValue[a]){
            minValue[a] = b;
            pq.push(make_pair(a,b));
        }
    }
    int d;
    scan(d);
    d--;
    while(!pq.empty()){
        pair<int, int> curr = pq.top();
        pq.pop();
        if(curr.first == d)
            break;
        for(int x = 0; x < adj[curr.first].size(); x++){
            pair<int, int> nextPair = adj[curr.first][x];
            if(nextPair.second + curr.second >= minValue[nextPair.first])
                continue;
            minValue[nextPair.first] = nextPair.second + curr.second;
            pq.push(make_pair(nextPair.first, minValue[nextPair.first]));
        }
    }
    printf("%d", minValue[d]);
    return 0;
}
