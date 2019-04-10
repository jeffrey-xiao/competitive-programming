#include "scales.h"
#include "graderlib.c"
#include <bits/stdc++.h>

#define SIZE 720

using namespace std;

void init(int T) {}

vector<int> getPermutation(int permutationIndex) {
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

struct State {
  int type;
  bool valid;
  vector<int> val;
  State() {}

  State(int type, bool valid, vector<int> val) {
    this->type = type;
    this->valid = valid;
    this->val = val;
  }
};

int target[] = {243, 81, 27, 9, 3, 1};
vector<int> toOrder[720];

map<pair<vector<int>, int>, State> memo;

// determining the best question
State compute(vector<int> validStates, int iteration) {
  if (memo.count({validStates, iteration}))
    return memo[{validStates, iteration}];

  State ret = State(-1, 0, vector<int>());
  ret.val = {0, 0, 0};

  if (validStates.size() == 1 || validStates.size() == 0) {
    ret.valid = 1;
    return ret;
  }

  for (int i = 0; i < 1 << 6; i++) {
    vector<int> val;
    for (int j = 0; j < 6; j++)
      if (i & 1 << j)
        val.push_back(j);

    // valid measurement
    if (val.size() == 3) {
      for (int j = 0; j < 3; j++) {
        vector<vector<int>> valids;
        for (int k = 0; k < 3; k++) {
          vector<int> nextValid;
          for (int permutationIndex : validStates) {
            vector<int> permutation = getPermutation(permutationIndex);
            vector<int> toIndex = toOrder[permutationIndex];

            // get smallest
            if (j == 0) {
              if (toIndex[val[k]] < toIndex[val[(k + 1) % 3]] &&
                  toIndex[val[k]] < toIndex[val[(k + 2) % 3]])
                nextValid.push_back(permutationIndex);
            }
            // get median
            else if (j == 1) {
              if (!((toIndex[val[k]] > toIndex[val[(k + 1) % 3]] &&
                        toIndex[val[k]] > toIndex[val[(k + 2) % 3]]) ||
                      (toIndex[val[k]] < toIndex[val[(k + 1) % 3]] &&
                          toIndex[val[k]] < toIndex[val[(k + 2) % 3]])))
                nextValid.push_back(permutationIndex);
            }
            // get largest
            else if (j == 2) {
              if (toIndex[val[k]] > toIndex[val[(k + 1) % 3]] &&
                  toIndex[val[k]] > toIndex[val[(k + 2) % 3]])
                nextValid.push_back(permutationIndex);
            }
          }
          valids.push_back(nextValid);
        }

        bool valid = true;
        for (int k = 0; k < (int)valids.size(); k++) {
          if ((int)valids[k].size() > target[iteration]) {
            valid = false;
            break;
          }
        }

        for (int k = 0; k < (int)valids.size() && valid; k++) {
          if (!compute(valids[k], iteration + 1).valid) {
            valid = false;
            break;
          }
        }

        if (valid) {
          ret.type = j;
          ret.val = val;
          ret.valid = true;
          return memo[{validStates, iteration}] = ret;
        }
      }
    } else if (val.size() == 4) {
      for (int j = 0; j < 4; j++) { // val[j] is on D
        vector<vector<int>> valids;
        for (int k = 0; k < 4; k++) { // val[k] is result
          if (j == k)
            continue;
          vector<int> nextValid;
          for (int permutationIndex : validStates) {
            vector<int> permutation = getPermutation(permutationIndex);
            vector<int> toIndex = toOrder[permutationIndex];

            // D is the heaviest
            if (toIndex[val[j]] > toIndex[val[(j + 1) % 4]] &&
                toIndex[val[j]] > toIndex[val[(j + 2) % 4]] &&
                toIndex[val[j]] > toIndex[val[(j + 3) % 4]]) {
              if (toIndex[val[k]] <= toIndex[val[(j + 1) % 4]] &&
                  toIndex[val[k]] <= toIndex[val[(j + 2) % 4]] &&
                  toIndex[val[k]] <= toIndex[val[(j + 3) % 4]])
                nextValid.push_back(permutationIndex);
            }

            else {
              int minIndex = 1 << 30;
              for (int l = 0; l < 4; l++)
                if (toIndex[val[j]] < toIndex[val[l]])
                  minIndex = min(minIndex, toIndex[val[l]]);

              if (minIndex == toIndex[val[k]])
                nextValid.push_back(permutationIndex);
            }
          }
          valids.push_back(nextValid);
        }
        bool valid = true;
        for (int k = 0; k < (int)valids.size(); k++) {
          if ((int)valids[k].size() > target[iteration]) {
            valid = false;
            break;
          }
        }

        for (int k = 0; k < (int)valids.size() && valid; k++) {
          if (!compute(valids[k], iteration + 1).valid) {
            valid = false;
            break;
          }
        }

        if (valid) {
          ret.type = 3;

          ret.val = {val[(j + 1) % 4], val[(j + 2) % 4], val[(j + 3) % 4], val[j % 4]};
          ret.valid = true;
          return memo[{validStates, iteration}] = ret;
        }
      }
    }
  }

  return memo[{validStates, iteration}] = ret;
}

void orderCoins() {
  vector<int> validSolutions;

  for (int i = 0; i < SIZE; i++) {
    validSolutions.push_back(i);
    vector<int> permutation = getPermutation(i);

    vector<int> toIndex(6);
    for (int m = 0; m < 6; m++)
      toIndex[permutation[m]] = m;

    toOrder[i] = toIndex;
  }

  for (int q = 0; q < 6; q++) {
    State best = compute(validSolutions, q);
    vector<int> nextSolutions;
    if (best.type == 0) {
      int res = getLightest(best.val[0] + 1, best.val[1] + 1, best.val[2] + 1) - 1;
      int index = 0;
      if (res == best.val[1])
        index = 1;
      else if (res == best.val[2])
        index = 2;
      for (int permutationIndex : validSolutions) {
        vector<int> permutation = getPermutation(permutationIndex);
        vector<int> toIndex = toOrder[permutationIndex];

        if (toIndex[best.val[index]] < toIndex[best.val[(index + 1) % 3]] &&
            toIndex[best.val[index]] < toIndex[best.val[(index + 2) % 3]])
          nextSolutions.push_back(permutationIndex);
      }
    } else if (best.type == 1) {
      int res = getMedian(best.val[0] + 1, best.val[1] + 1, best.val[2] + 1) - 1;
      int index = 0;
      if (res == best.val[1])
        index = 1;
      else if (res == best.val[2])
        index = 2;

      for (int permutationIndex : validSolutions) {
        vector<int> permutation = getPermutation(permutationIndex);
        vector<int> toIndex = toOrder[permutationIndex];

        if (!(((toIndex[best.val[index]] > toIndex[best.val[(index + 1) % 3]] &&
                  toIndex[best.val[index]] > toIndex[best.val[(index + 2) % 3]])) ||
                ((toIndex[best.val[index]] < toIndex[best.val[(index + 1) % 3]] &&
                    toIndex[best.val[index]] < toIndex[best.val[(index + 2) % 3]]))))
          nextSolutions.push_back(permutationIndex);
      }
    } else if (best.type == 2) {
      int res = getHeaviest(best.val[0] + 1, best.val[1] + 1, best.val[2] + 1) - 1;
      int index = 0;
      if (res == best.val[1])
        index = 1;
      else if (res == best.val[2])
        index = 2;

      for (int permutationIndex : validSolutions) {
        vector<int> permutation = getPermutation(permutationIndex);
        vector<int> toIndex = toOrder[permutationIndex];

        if (toIndex[best.val[index]] > toIndex[best.val[(index + 1) % 3]] &&
            toIndex[best.val[index]] > toIndex[best.val[(index + 2) % 3]])
          nextSolutions.push_back(permutationIndex);
      }
    } else if (best.type == 3) {
      int res =
          getNextLightest(best.val[0] + 1, best.val[1] + 1, best.val[2] + 1, best.val[3] + 1) - 1;
      int index = 0;
      if (res == best.val[1])
        index = 1;
      else if (res == best.val[2])
        index = 2;

      for (int permutationIndex : validSolutions) {
        vector<int> permutation = getPermutation(permutationIndex);
        vector<int> toIndex = toOrder[permutationIndex];

        int j = 3;
        // D is the heaviest
        if (toIndex[best.val[j]] > toIndex[best.val[(j + 1) % 4]] &&
            toIndex[best.val[j]] > toIndex[best.val[(j + 2) % 4]] &&
            toIndex[best.val[j]] > toIndex[best.val[(j + 3) % 4]]) {
          if (toIndex[best.val[index]] <= toIndex[best.val[(j + 1) % 4]] &&
              toIndex[best.val[index]] <= toIndex[best.val[(j + 2) % 4]] &&
              toIndex[best.val[index]] <= toIndex[best.val[(j + 3) % 4]])
            nextSolutions.push_back(permutationIndex);
        }

        else {
          int minIndex = 1 << 30;
          for (int l = 0; l < 4; l++)
            if (toIndex[best.val[j]] < toIndex[best.val[l]])
              minIndex = min(minIndex, toIndex[best.val[l]]);

          if (minIndex == toIndex[best.val[index]])
            nextSolutions.push_back(permutationIndex);
        }
      }
    }

    validSolutions = nextSolutions;
    //        printf("%d\n", validSolutions.size());
  }

  vector<int> ans = getPermutation(validSolutions[0]);
  int W[6];
  for (int i = 0; i < 6; i++)
    W[i] = ans[i] + 1;
  answer(W);
}
