#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))

#define VSIZE 50500
#define ESIZE 100000

using namespace std;
typedef long long ll;
typedef pair<int, int> pi;
typedef pair<ll, ll> pll;

bool v[VSIZE];
int count = 0;

vector<pi> e;
set<pi> setE;

void insertEdge(int a, int b){
    if(a > b)
        swap(a, b);
    e.pb(mp(a, b));
    setE.insert(mp(a, b));
}

int getRand(int x){
    ll num = rand();
    num = num<< 32ll | rand();
    return num % x;
}

int main(){
    int edges[] = {50000, 60000, 70000, 80000, 90000, 100000, 100000};
    int vertices[] = {15000, 20000, 35000, 40000, 45000, 50000, 50000};
    int cost[] = {5000, 5000, 5000, 5000, 5000, 5000, 10000};
    for(int t = 0; t < 7; t++){
        srand(time(0));
        ostringstream ss;
        ss << (t+1);
        ofstream out("C:\\Documents and Settings\\Name\\Desktop\\UTS Open\\Subtask 3\\UTS - P5 - " + ss.str() + ".in");

        e.clear();
        setE.clear();
        memset(v, false, sizeof(v));

        out << vertices[t] << " " << edges[t] << endl;

        int prev = getRand(vertices[t]);
        int count = 1;
        v[prev] = true;
        while(count < vertices[t]){
            int next = getRand(vertices[t]);
            if(!v[next]){
                v[next] = true;
                count++;
                insertEdge(prev, next);
            }
            prev = next;
        }

        for(int x = 0; x < edges[t] - (vertices[t] - 1);){
            int a = getRand(vertices[t]);
            int b = getRand(vertices[t]);
            if(a > b)
                swap(a, b);
            if(a != b && !setE.count(mp(a, b))){
                insertEdge(a, b);
                x++;
            }
        }
        for(int x = 0; x < e.size(); x++){
            int c = getRand(cost[t])+1;
            int isSwap = rand()%2;
            if(isSwap){
                out << (e[x].first+1) << " " << (e[x].second+1) << " " << c << endl;
            }
            else{
                out << (e[x].second+1) << " " << (e[x].first+1) << " " << c << endl;
            }
        }
    }
}
