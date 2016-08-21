#include <bits/stdc++.h>
#include "assistant.h"

using namespace std;

void Assist(unsigned char *A, int N, int K, int R) {
    set<int> neededColors;
    set<int> unneededColors;

    for (int i = 0; i < K; i++) {
        if (A[i])
            neededColors.insert(i);
        else
            unneededColors.insert(i);
    }

    for (int i = 0; i < N; i++) {
        int colorNeeded = GetRequest();
        if (neededColors.count(colorNeeded)) {
            neededColors.erase(colorNeeded);
        } else {
            PutBack(*unneededColors.begin());
            unneededColors.erase(unneededColors.begin());
        }
        if (A[i + K])
            neededColors.insert(colorNeeded);
        else
            unneededColors.insert(colorNeeded);
    }

}
