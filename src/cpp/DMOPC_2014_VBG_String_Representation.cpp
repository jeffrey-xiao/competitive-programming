#include <bits/stdc++.h>
using namespace std;

#define llj(a,b,c) for (int (a) = (b); (a) < (c); (a)++)
#define lll(i, x) llj(i, 0, x)
#define fori(x) lll(i, x)
#define forj(x) lll(j, x)
#define fork(x) lll(k, x)

#define all(X) (X).begin(), (X).end()
#define ever ;;
#define fe(x, C) for (auto& x : (C))
#define INF ((int) 1e9+10)
#define LINF ((ll) 1e16)
#define pb push_back
#define mp make_pair
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))
#define nrint(x) int x; rint(x);
#define nrlong(x) long long x; rlong(x);
#define rfloat(x) scanf("%lf", &(x))
#define endl "\n"
#define trace(X) cout << (X) << endl;
typedef long long ll;
typedef pair<int,int> pii;
#define x first
#define y second
/*-------------------------------------------THE END------------------------------------*/
const int MAX = 301;


typedef long double ld;

ld x[MAX] , y[MAX];
bool mat[MAX][MAX];
vector<pii> graph;



bool do_inter(int a1 , int a2 , int b1 , int b2)
{
    ld A1 = y[a2] - y[a1];
    ld B1 = x[a1] - x[a2];
    ld C1 = A1*x[a1] + B1*y[a1];

    ld A2 = y[b2] - y[b1];
    ld B2 = x[b1] - x[b2];
    ld C2 = A2*x[b1] + B2*y[b1];


    ld det = A1*B2-A2*B1;
    if (det == 0) {
        if (A1*C2==A2*C1 && B1*C2 == B2*C1)
            return 1;
        return 0;
    }
    ld X = (B2*C1 - B1*C2) / det;
    ld Y = (A1*C2 - A2*C1) / det;
    if ( X > min(x[a1] , x[a2]) && X < max(x[a1] , x[a2]) && X > min(x[b1] , x[b2]) && X < max(x[b1] , x[b2]))
//        if ( Y > min(y[a1] , y[a2]) && Y < max(y[a1] , y[a2]) && Y > min(y[b1] , y[b2]) && Y < max(y[b1] , y[b2]))
            return 1;
    return 0;

}







int main()
{
    //freopen("test.txt", "r", stdin);
    nrint(n);
    nrint(m);

    llj(i , 1 , n+1)
        cin >> x[i] >> y[i];

    fori(m)
    {
        bool can = 1;
        nrint(a); nrint(b);
        forj(graph.size() )
        {
            if (do_inter(graph[j].x , graph[j].y ,a,b ))
                can = 0;
        }
        if (can)
        {
            //trace(a);
            //trace(b);
            //cout << endl;
//            cout << "CAN ADD" << endl;
            graph.pb(mp(a,b));
            mat[a][b] = mat[b][a] = 1;
        } else {
//            cout << "CANNOT ADD" << endl;
        }



    }

    int ans = 1;

    llj(i , 1 , n+1)
    {
        llj(j , i+1 , n+1)
            if (mat[i][j]){
                ans = max(ans , 2);
                llj(k , j+1 , n+1){
                    if (mat[i][k] && mat[j][k]){
                        ans = max(ans , 3);
                        llj(l , k+1 , n+1)
                            if (mat[i][l] && mat[j][l] && mat[k][l])
                                ans = 4;

                    }
                }

            }
    }
    trace(ans);

};
