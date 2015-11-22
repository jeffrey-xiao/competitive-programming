#include <bits/stdc++.h>

#define mp make_pair
#define pb push_back
#define INF 1<<30
#define MOD 1000000007
#define rint(x) scanf("%d", &(x))
#define rlong(x) scanf("%lld", &(x))

using namespace std;
typedef long long ll;
typedef pair<int, int> pi;
typedef pair<double, double> pd;
typedef pair<float, float> pf;
typedef pair<ll, ll> pll;

vector<pf> ps1;
vector<pf> ps2;

vector<float> a1;
vector<float> a2;

float dist(pf p1, pf p2){
    float x = p1.first - p2.first;
    float y = p1.second - p2.second;
    return sqrt(x*x + y*y);
}

inline bool isEqual(double x, double y)
{
  const double epsilon = 1e-5;
  return std::abs(x - y) <= epsilon * std::abs(x);
}

float getAngle(pf p1, pf p2, pf p3){
    float d12 = dist(p1, p2);
    float d23 = dist(p2, p3);
    float x1 = p1.first - p2.first;
    float y1 = p1.second - p2.second;
    float x2 = p3.first - p2.first;
    float y2 = p3.second - p2.second;
    float dot = x1*x2 + y1*y2;
    float det = x1*y2 - y1*x2;
    return atan2(det, dot) * (d12/d23);
}
int prefix[500000];
void computePrefix(){
    int len = 0;
    prefix[0] = 0;
    int i = 1;
    int size = a1.size();
    while(i < size){
        if(isEqual(a1[i], a1[len])){
            len++;
            prefix[i] = len;
            i++;
        }else{
            if(len != 0){
                len = prefix[len-1];
            }else{
                prefix[i] = 0;
                i++;
            }
        }
    }
}


int main(){
    int t;
    scanf("%d", &t);
    for(; t > 0; t--){
        memset(prefix, 0, sizeof(prefix));
        ps1.clear();
        ps2.clear();
        a1.clear();
        a2.clear();
        int n;
        scanf("%d", &n);
        for(int x = 0; x < n; x++){
            float a, b;
            scanf("%f%f", &a, &b);
            ps1.pb(mp(a, b));
        }
        for(int x = 0; x < n; x++){
            float a, b;
            scanf("%f%f", &a, &b);
            ps2.pb(mp(a, b));
        }
        float min1 = 1<<30;
        float min2 = 1<<30;
        for(int x = 0; x < n; x++){
            int p1 = x;
            int p2 = (x+1)%n;
            int p3 = (x+2)%n;
            a1.pb(getAngle(ps1[p1], ps1[p2], ps1[p3]));
            min1 = min(min1, a1[x]);
        }
        for(int x = 0; x < n; x++){
            int p1 = x;
            int p2 = (x+1)%n;
            int p3 = (x+2)%n;
            a2.pb(getAngle(ps2[p1], ps2[p2], ps2[p3]));
            min2 = min(min2, a2[x]);
        }
        int length = a2.size();
        for(int x = 0; x < length; x++)
            a2.pb(a2[x]);
        computePrefix();
        unsigned int i = 0;
        unsigned int j = 0;
        bool found = false;
        while(i < a2.size()){
            if(isEqual(a1[j], a2[i])){
                j++;
                i++;
            }
            if(j == a1.size()){
                printf("%d\n", i-j+1);
                found = true;
                break;
            }else if(i < a2.size() && !isEqual(a1[j], a2[i])){

                if(j != 0)
                    j = prefix[j-1];
                else
                    i = i+1;
            }
        }
        if(!found)
            printf("0\n");
    }
    return 0;
}
