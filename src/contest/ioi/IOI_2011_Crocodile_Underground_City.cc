#include <bits/stdc++.h>

using namespace std;

int n, m, k;
const int INF = 1 << 30;
const int SIZE = 100000;
vector< vector< pair <int, int> > > adj (SIZE);
int firstMin[SIZE];
int secondMin[SIZE];
int v[SIZE];

struct comp{
    bool operator()(pair<int, int> p1, pair<int, int> p2){
        return p1.second > p2.second;
    }
};

int main(){
    scanf("%d%d%d",&n,&m,&k);
    for(int x = 0; x < m; x++){
        int a, b, c;
        scanf("%d%d%d",&a,&b,&c);
        adj[a].push_back(make_pair(c, b));
        adj[b].push_back(make_pair(c, a));
    }
    for(int x = 0; x < n; x++){
        firstMin[x] = INF;
        secondMin[x] = INF;
    }
    //FIRST IS INDEX, SECOND IS CURRENT COST
    set<pair<int, int> > pq;

    for(int x = 0; x < k; x++){
        int i;
        scanf("%d", &i);
        firstMin[i] = 0;
        secondMin[i] = 0;
        v[i] = 2;
        pq.insert(make_pair(0, i));
    }

    while(!pq.empty()){
        pair<int, int> current = make_pair(pq.begin()->first,pq.begin()->second);
        pq.erase(pq.begin());
        for(int x = 0; x < adj[current.second].size(); x++){
            pair<int, int> next = adj[current.second][x];
            int cost = current.first + next.first;
            if(cost >= secondMin[next.second])
                continue;
            pq.erase(make_pair(secondMin[next.second], next.second));
            if (cost < firstMin[next.second]) {
                secondMin[next.second] = firstMin[next.second];
                firstMin[next.second] = cost;
            } else if (cost < secondMin[next.second]) {
                secondMin[next.second] = cost;
            }
            pq.insert(make_pair(secondMin[next.second], next.second));
        }
    }
    printf("%d", secondMin[0]);
    return 0;
}
