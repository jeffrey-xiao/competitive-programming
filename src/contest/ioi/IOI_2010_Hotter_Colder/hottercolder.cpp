#include "grader.h"
#include <bits/stdc++.h>

using namespace std;

int HC(int N){
    int prevGuess = 1;
    int lo = 1;
    int hi = N;
    Guess(prevGuess);

    while (lo < hi) {
        double prev = hi - lo + 1;
        int mid = (lo + hi + 1) >> 1;
        int nextGuess = (2 * mid - prevGuess);
        nextGuess = min(N, nextGuess);
        nextGuess = max(1, nextGuess);
        if (nextGuess == prevGuess) {
            if (nextGuess >= hi - 1)
                nextGuess--;
            else if (nextGuess <= lo + 1)
                nextGuess++;
            else if (abs(hi - nextGuess) > abs(lo - nextGuess))
                nextGuess++;
            else
                nextGuess--;
        }

        int res = Guess(nextGuess);
        // going up
        if ((nextGuess > prevGuess && res == 1) || (nextGuess < prevGuess && res == -1))
            lo = (nextGuess + prevGuess + 2) >> 1;
        // going down
        else if ((nextGuess < prevGuess && res == 1) | (nextGuess > prevGuess && res == -1))
            hi = (nextGuess + prevGuess - 1) >> 1;
        // found
        else if (res == 0)
            return (nextGuess + prevGuess) >> 1;
        prevGuess = nextGuess;

    }
    return hi;
}
