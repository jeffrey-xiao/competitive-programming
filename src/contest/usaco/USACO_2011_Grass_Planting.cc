#include <bits/stdc++.h>

using namespace std;

#define N 100100
#define LN 18
#define l(x) x<<1
#define r(x) x<<1|1
#define m(x, y) (x + y)/2
#define vll vector<long long>

int n, m;

int chainNo = 0, chainInd[N], chainHead[N], chainPos[N], chainSize[N];
int depth[N], pa[N][LN], subtree[N];

vector<vector<int> > adj(N);
// vector representing the binary indexed tree
vector<vector<long long> > t1;
vector<vector<long long> > t2;

void update(vll& tree, int x, int treeSize, int v){
    for(; x <= treeSize; x += (x & -x)){
        tree[x] += v;
    }
}
void update(vll& tree1, vll& tree2, int x, int y, int treeSize, int v){
    update(tree1, x, treeSize, v);
    update(tree1, y+1, treeSize, -v);
    update(tree2, x, treeSize, v * (x-1));
    update(tree2, y+1, treeSize, -v * y);
}
long long query(vll tree, int x){
    long long sum = 0;
    for(; x > 0; x -= (x & -x)){
        sum += tree[x];
    }
    return sum;
}
long long query(vll tree1, vll tree2, int x){
    return query(tree1, x) * x - query(tree2, x);
}
long long query(vll tree1, vll tree2, int x, int y){
    return query(tree1, tree2, y) - query(tree1, tree2, x-1);
}

void HLD(int curr){
    if(chainHead[chainNo] == -1)
        chainHead[chainNo] = curr;
    chainInd[curr] = chainNo;
    chainPos[curr] = chainSize[chainNo]+1;
    chainSize[chainNo]++;

    int ind = -1;
    int s = -1;
    for(int i = 0; i < adj[curr].size(); i++){
        if(subtree[adj[curr][i]] > s && depth[curr] < depth[adj[curr][i]]){
            s = subtree[adj[curr][i]];
            ind = i;
        }
    }
    if(ind != -1)
        HLD(adj[curr][ind]);

    for(int i = 0; i < adj[curr].size(); i++){
        if(i != ind && depth[adj[curr][i]] > depth[curr]){
            chainNo++;
            HLD(adj[curr][i]);
        }
    }
}
// the depth of x is greater than the depth of y thus on a lower level than y
int LCA(int x, int y){
    if(depth[x] < depth[y])
        swap(x, y);
    for(int i = LN-1; i >= 0; i--){
        if(pa[x][i] != -1 && depth[pa[x][i]] >= depth[y])
            x = pa[x][i];
    }
    if(x == y){
         cout <<"HERE"<<x << endl;
        return x;
    }
    for(int i = LN-1; i >= 0; i--){
        if(pa[x][i] != -1 && pa[x][i] != pa[y][i]){
            x = pa[x][i], y = pa[y][i];
        }
    }
    cout <<"HERE"<<pa[x][0] << endl;
    return pa[x][0];
}

void dfs(int curr, int prev, int depth_){
    pa[curr][0] = prev;
    depth[curr] = depth_;
    subtree[curr] = 1;
    for(int x = 0; x < adj[curr].size(); x++){
        if(adj[curr][x] != prev){
            dfs(adj[curr][x], curr, depth_+1);
            subtree[curr] += subtree[adj[curr][x]];
        }
    }
}
// y will be higher up than x (I.E have a lower number)
long long query_up(int x, int y){
    long long sum = 0;
    while(true){
        int xchain = chainInd[x], ychain = chainInd[y];
        if(xchain == ychain){
            if(chainPos[x] == chainPos[y])
                break;
            sum += query(t1[xchain], t2[xchain], chainPos[y]+1, chainPos[x]);
            return sum;
        }
        sum += query(t1[xchain], t2[xchain], chainPos[x]);
        x = pa[chainHead[xchain]][0];
    }
    return sum;
}

long long query(int x, int y){
    int lca = LCA(x, y);
    int treeIndex = chainInd[lca];
    int treePos = chainPos[lca];
    long long a = query_up(x, 0);
    long long b = query_up(y, 0);
    long long c = 2*query_up(lca, 0);
    return a + b - c;
}

void update_up(int x, int y, int val){
    while(true){
        int xchain = chainInd[x];
        int ychain = chainInd[y];
        if(xchain == ychain){
            if(chainPos[x] == chainPos[y])
                break;
            update(t1[xchain], t2[xchain], chainPos[y]+1, chainPos[x], chainSize[xchain], val);
            break;
        }
        update(t1[xchain], t2[xchain], 1, chainPos[x], chainSize[xchain], val);
        x = pa[chainHead[xchain]][0];
    }
}

void update(int x, int y){
    int lca = LCA(x, y);
    int treeIndex = chainInd[lca];
    int treePos = chainPos[lca];

    update_up(x, 0, 1);
    update_up(y, 0, 1);
    update_up(lca, 0, -2);
}

int main(){
    scanf("%d%d", &n, &m);
    for(int x = 0; x < n-1; x++){
        int a, b;
        scanf("%d%d", &a, &b);
        a--, b--;
        adj[a].push_back(b);
        adj[b].push_back(a);
    }

    // initializing all values
    for(int x = 0; x < N; x++){
        for(int y = 0; y < LN; y++)
            pa[x][y] = -1;
        chainHead[x] = -1;
        chainSize[x] = 0;
    }
    dfs(0, -1, 0);
    HLD(0);

    // computing pa array for LCA
    for(int y = 1; y < LN; y++){
        for(int x = 0; x < n; x++){
            if(pa[x][y-1] != -1){
                pa[x][y] = pa[pa[x][y-1]][y-1];
            }
        }
    }

    // constructing binary indexed trees for chains
    for(int x = 0; x < n && chainSize[x] != 0; x++){
        t1.push_back(vector<long long>(chainSize[x]+5));
        t2.push_back(vector<long long>(chainSize[x]+5));
        for(int y = 0; y <= chainSize[x]+4; y++){
            t1[x][y] = 0;
            t2[x][y] = 0;
        }
    }

    // get queries
    for(int x = 0; x < m; x++){
        char c;
        int a, b;
        scanf(" %c%d%d", &c, &a, &b);
        if(c == 'P'){
            update(a-1, b-1);
        }else if(c == 'Q'){
            long long ans = query(a-1, b-1);
            cout << ans << endl;
        }
    }
    return 0;
}
