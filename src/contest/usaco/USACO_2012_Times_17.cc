#include <bits/stdc++.h>

using namespace std;

char num1[1001];
char num2[1001];
char input[1001];
char result[1001] = {0};

int main(){
    scanf("%s", input);
    sprintf(num1, "00000%s", input);
    sprintf(num2, "0%s0000", input);

    int length = strlen(num1);
    for(int x = length; x > 0; x--){
        result[x] += num1[x] + num2[x] - 2 * '0';
        if(result[x] >= 2)
            result[x] -= 2, result[x-1] += 1;
    }
    for(int x = 0; x < length; x++){
        if(x == 0 && result[0] == 0)
            continue;
        printf("%d", result[x]);
    }
    printf("\n");
    return 0;
}
