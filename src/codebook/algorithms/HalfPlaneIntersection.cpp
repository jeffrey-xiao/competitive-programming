/*
 * Implementation of half plane intersection algorithm
 *
 * Reference Problem: https://dmoj.ca/problem/ccoprep3p3
 */


#include <bits/stdc++.h>

using namespace std;

const int maxn = 100000; const double eps = 1e-15;

int n;

struct Point{
    double x,y;
    Point(double x = 0.0,double y = 0.0): x(x),y(y)	{}
    Point operator + (const Point &b)	const{return Point(x + b.x,y + b.y);}
    Point operator - (const Point &b)	const{return Point(x - b.x,y - b.y);}
    Point operator * (double k)	const{return Point(x * k,y * k);}
    double operator * (const Point &b)	const{return x * b.y - b.x * y;}
    double operator ^ (const Point &b)	const{return x * b.x + y * b.y;}
};

struct Line{
    Point p,v; double rad;
    Line()	{}
    Line(const Point &p,const Point &v)	: p(p),v(v){rad = atan2(v.y,v.x);}
    bool operator < (const Line &b)	const{return rad < b.rad;}
};

vector<Line> Ln,L;

inline bool OnLeft(const Line &l,const Point &p){return l.v * (p - l.p)>eps;}

inline Point GetLineIntersection(const Line &a,const Line &b){
    Point u = a.p - b.p;
    double t = (b.v * u) / (a.v * b.v);
    return a.p + a.v * t;
}

bool HalfplaneIntersection(int n){
    static Point p[maxn << 1]; static Line q[maxn << 1];
    sort(L.begin(), L.end());
    int front,rear; q[front = rear = 0] = L[0];
    for(int i = 1; i < n; i ++){
        while(front < rear && !OnLeft(L[i],p[rear - 1]))	-- rear;
        while(front < rear && !OnLeft(L[i],p[front]))		++ front;
        if(fabs(q[rear].v * L[i].v) < eps){
            if(OnLeft(q[rear],L[i].p))	q[rear] = L[i];
        }else	q[++ rear] = L[i];
        if(front < rear) p[rear - 1]=GetLineIntersection(q[rear - 1],q[rear]);
    }
    while(front < rear && !OnLeft(q[front],p[rear - 1]))	-- rear;
    return (rear - front) > 1;
}

bool check(int n){
    L.clear();
    for(int i = 0; i < (n << 1); i ++)	L.push_back(Ln[i]);
    return HalfplaneIntersection(n << 1);
}

int main(){
    scanf("%d",&n);
    for(int i = 1, x, y1, y2; i <= n; i ++){
        scanf("%d%d%d",&x,&y1,&y2);
        Ln.push_back(Line(Point(0,(double) y2 / x),Point(-1.0 / x, 1)));
        Ln.push_back(Line(Point(0,(double) y1 / x),Point( 1.0 / x,-1)));
    }
    int l = 1,r = n,ans = 1;
    while(l <= r){
        int mid = l + r >> 1;
        if(check(mid)){ ans = mid; l = mid + 1;}
        else {r = mid - 1;}
    }
    printf("%d",ans);
    return 0;
}
