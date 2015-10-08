#include <bits/stdc++.h>

using namespace std;

double x, y;
int n, m;
double a[100], b[100];
int c[100];
int len[100];

double dist (double x1, double y1) {
    return sqrt((x1-x)*(x1-x) + (y1 - y)*(y1 - y));
}
bool poss[51000];
int main () {
    scanf("%lf%lf%d%d", &x, &y, &n, &m);
    int total = 0;
    for (int i = 0; i < n; i++) {
        scanf("%d", &len[i]);
        total += len[i];
    }
    for (int i = 0; i < m; i++)
        scanf("%lf%lf%d", &a[i], &b[i], &c[i]);
    for (int i = 0; i < m; i++) {
        for (int j = i+1; j < m; j++) {
            if (c[i] == c[j])
                continue;
            int len1 = dist(a[i], b[i]);
            int len2 = dist(a[j], b[j]);
            memset(poss, false, sizeof poss);
            poss[0] = true;
            for (int i = 0; i < n; i++)
                for (int j = min(len1, len2); j >= 0; j--)
                    poss[j + len[i]] |= poss[j];
            bool valid = false;
            for (int i = min(len1, len2); i <= total - max(len1, len2); i++) {
                if (poss[i])
                    valid = true;
            }
            if (valid) {
                printf("Harry can connect to outlets at (%0.1f, %0.1f) and (%0.1f, %0.1f).\n",a[i],b[i],a[j],b[j]);
                return 0;
            }
        }
    }
    printf("Harry is helpless.\n");
    return 0;
}
