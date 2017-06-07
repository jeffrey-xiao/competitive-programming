#include <bits/stdc++.h>

using namespace std;

#define N 1500
#define CN 1124250

struct point{
    double x, y;
    point(){}
    point(double x, double y){
        this->x = x;
        this->y = y;
    }
};
struct state{
    double dist;
    int a, b;
    state(){}
    state(double dist, int a, int b){
        this->a = a;
        this->b = b;
        this->dist = dist;
    }
};
int n;
point p[N];
vector<state> s;
double dist[N][N];
double getDist(point& p1, point& p2){
    double x = p1.x - p2.x;
    double y = p1.y - p2.y;
    return sqrt(x*x+y*y);
}
bool compare(state s1, state s2){
    return s1.dist < s2.dist;
}
point mid(point p1, point p2){
    return point((p1.x+p2.x)/2, (p1.y + p2.y)/2);
}
int main(){
    scanf("%d", &n);
    for(int x = 0; x < n; x++){
        int a, b;
        scanf("%d%d", &a, &b);
        p[x] = point(a, b);
    }
    for(int x = 0; x < n; x++){
        for(int y = x + 1; y < n; y++){
            dist[x][y] = getDist(p[x], p[y]);
            dist[y][x] = dist[x][y];
            s.push_back(state(dist[x][y], x, y));
        }
    }
    sort(s.begin(), s.end(), compare);
    double maxV = 0;
    for(int x = 0; x < s.size(); x++){
        for(int y = x + 1; y < s.size() && s[x].dist == s[y].dist; y++){
            int i1 = s[x].a;
            int i2 = s[x].b;
			int i3 = s[y].a;
			int i4 = s[y].b;
			point m1 = mid(p[i1], p[i2]);
			point m2 = mid(p[i3], p[i4]);
			if(m1.x == m2.x && m1.y == m2.y){
				double area = dist[i1][i3] * dist[i1][i4];
				maxV = max(maxV, area);
			}
        }
    }
    printf("%lld", (long long)(floor(maxV + 0.5)));
}
