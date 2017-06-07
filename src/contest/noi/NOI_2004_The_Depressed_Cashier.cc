#include <bits/stdc++.h>

using namespace std;

#define SIZE 300000
#define MID 100000

int tree[SIZE];
int delta, n, minWage, employees, employeesLeft;

void update(int idxx, int k){
    for(int x = idxx; x < SIZE; x += (x & - x))
        tree[x] += k;
}

int freq(int idxx){
    int total = 0;
    for(int x = idxx; x > 0; x -= (x & - x))
        total += tree[x];
    return total;
}

int main() {

    scanf("%d%d", &n, &minWage);

    for(int x = 0; x < n; x++){
        char c;
        int k;
        scanf(" %c %d", &c, &k);
        if(c == 'I'){
            if(k < minWage)
                continue;
            update(k - delta + MID, 1);
            ++employees;
        }else if(c == 'A'){
            delta += k;
        }else if(c == 'S'){
            delta -= k;
            int lower = freq(minWage - 1 - delta + MID);
            employees -= lower;
            employeesLeft += lower;

            for(int y = 1; y <= minWage - 1 - delta + MID; y++)
                update(y, - tree[y]);
        }else if(c == 'F'){
            int lo = 1;
            int hi = SIZE - 1;
            if(k > employees){
                printf("-1\n");
                continue;
            }
            k = employees - k + 1;
            while(lo <= hi){
                int mid = lo + (hi - lo)/2;
                if(freq(mid) < k)
                    lo = mid + 1;
                else
                    hi = mid - 1;
            }
            printf("%d\n", lo + delta - MID);
        }
    }
    printf("%d\n", employeesLeft);
    return 0;
}
