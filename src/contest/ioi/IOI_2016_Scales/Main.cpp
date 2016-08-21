#include "scales.h"
#include <bits/stdc++.h>

#define SIZE 720

using namespace std;

void init(int T) { }

vector<int> getPermutation (int permutationIndex) {
    int base = 120;
    vector<int> ret;
    bool used[] = {0, 0, 0, 0, 0, 0};
    for (int i = 0; i < 6; i++) {
        int index = permutationIndex / base;
        permutationIndex %= base;
        if (6 - i - 1)
            base /= (6 - i - 1);

        int cnt = 0;
        int j = 0;
        for (; j < 6 && cnt <= index; j++)
            cnt += 1 - used[j];
        used[--j] = true;
        ret.push_back(j);
    }
    return ret;
}

void orderCoins() {
    vector<int> validSolutions;

    for (int i = 0; i < SIZE; i++) {
        validSolutions.push_back(i);
        vector<int> ret = getPermutation(i);
    }

    int WW[6];
    answer(WW);
    return;
    while (validSolutions.size() > 1) {
        int best = 0;
        int type = -1;
        vector<int> state;

        // determining the best type
        for (int i = 0; i < 1 << 6; i++) {
            vector<int> val;
            for (int j = 0; j < 6; j++)
                if (i & 1 << j)
                    val.push_back(j);

            // valid measurement
            if (val.size() == 3) {
                for (int j = 0; j < 3; j++) {
                    int minEliminated = 1 << 30;
                    for (int k = 0; k < 3; k++) {
                        int currEliminated = 0;
                        for (int permutationIndex : validSolutions) {
                            vector<int> permutation = getPermutation(permutationIndex);
                            int toIndex[6];

                            for (int m = 0; m < 6; m++)
                                toIndex[permutation[m]] = m;

                            // get smallest
                            if (j == 0) {
                                if (toIndex[val[k]] > toIndex[val[(k + 1) % 3]] || toIndex[val[k]] > toIndex[val[(k + 2) % 3]])
                                    currEliminated++;
                            }
                            // get median
                            else if (j == 1) {
                                if ((toIndex[val[k]] > toIndex[val[(k + 1) % 3]] && toIndex[val[k]] > toIndex[val[(k + 2) % 3]]) ||
                                    (toIndex[val[k]] < toIndex[val[(k + 1) % 3]] && toIndex[val[k]] < toIndex[val[(k + 2) % 3]]))
                                    currEliminated++;
                            }
                            // get largest
                            else if (j == 2) {
                                if (toIndex[val[k]] < toIndex[val[(k + 1) % 3]] || toIndex[val[k]] < toIndex[val[(k + 2) % 3]])
                                    currEliminated++;
                            }
                        }
                        minEliminated = min(minEliminated, currEliminated);
                    }
                    if (minEliminated >= best) {
                        best = minEliminated;
                        type = j;
                        state = val;
                    }
                }
            }
        }

        vector<int> nextSolutions;
        int curr = 0;
        if (type == 0) {
            int res = getLightest(state[0] + 1, state[1] + 1, state[2] + 1) - 1;
            int index = 0;
            if (res == state[1])
                index = 1;
            else if (res == state[2])
                index = 2;
            for (int permutationIndex : validSolutions) {
                vector<int> permutation = getPermutation(permutationIndex);
                int toIndex[6];

                for (int m = 0; m < 6; m++)
                    toIndex[permutation[m]] = m;

                if (toIndex[state[index]] < toIndex[state[(index + 1) % 3]] && toIndex[state[index]] < toIndex[state[(index + 2) % 3]])
                    nextSolutions.push_back(permutationIndex);
                else
                    curr++;
            }
        } else if (type == 1) {
            int res = getMedian(state[0] + 1, state[1] + 1, state[2] + 1) - 1;
            int index = 0;
            if (res == state[1])
                index = 1;
            else if (res == state[2])
                index = 2;

            for (int permutationIndex : validSolutions) {
                vector<int> permutation = getPermutation(permutationIndex);
                int toIndex[6];

                for (int m = 0; m < 6; m++)
                    toIndex[permutation[m]] = m;

                if (!(((toIndex[state[index]] > toIndex[state[(index + 1) % 3]] && toIndex[state[index]] > toIndex[state[(index + 2) % 3]])) ||
                    ((toIndex[state[index]] < toIndex[state[(index + 1) % 3]] && toIndex[state[index]] < toIndex[state[(index + 2) % 3]]))))
                    nextSolutions.push_back(permutationIndex);
            }
        } else if (type == 2) {
            int res = getHeaviest(state[0] + 1, state[1] + 1, state[2] + 1) - 1;
            int index = 0;
            if (res == state[1])
                index = 1;
            else if (res == state[2])
                index = 2;

            for (int permutationIndex : validSolutions) {
                vector<int> permutation = getPermutation(permutationIndex);
                int toIndex[6];

                for (int m = 0; m < 6; m++)
                    toIndex[permutation[m]] = m;

                if (toIndex[state[index]] > toIndex[state[(index + 1) % 3]] && toIndex[state[index]] > toIndex[state[(index + 2) % 3]])
                    nextSolutions.push_back(permutationIndex);
            }
        }
        validSolutions = nextSolutions;
    }

    vector<int> ans = getPermutation(validSolutions[0]);
    int W[6];
    for (int i = 0; i < 6; i++)
        W[i] = ans[i] + 1;
    answer(W);
}
