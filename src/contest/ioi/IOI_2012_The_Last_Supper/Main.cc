#include <bits/stdc++.h>
#include "grader.h"

using namespace std;

#define MAX_N 100001
#define MAX_K 100001

bool needed[MAX_N + MAX_K];
int nextOccurence[MAX_N + MAX_K];

// index of last occurence
int lastOccurence[MAX_K];
int scaffold[MAX_K];

void ComputeAdvice(int *C, int N, int K, int M) {

    for (int i = 0; i < MAX_K; i++)
        lastOccurence[i] = -1;

    for (int i = 0; i < MAX_N + MAX_K; i++)
        nextOccurence[i] = 1 << 30;

    for (int i = 0; i < K; i++)
        lastOccurence[i] = i;

    for (int i = 0; i < N; i++) {
        if (lastOccurence[C[i]] != -1)
            nextOccurence[lastOccurence[C[i]]] = K + i;
        lastOccurence[C[i]] = K + i;
    }

    // current colors on scaffold
    unordered_map<int, int> current;    // color, index
    priority_queue<pair<int, pair<int, int>>> pq;  // next occ, color, index
    for (int i = 0; i < K; i++) {
        current.insert({i, i});
        pq.push({nextOccurence[i], {i, i}});
    }

    // simulating removals
    for (int i = 0; i < N; i++) {
        // found color on current scaffold
        if (current.count(C[i])) {
            needed[current[C[i]]] = true;

            current.erase(C[i]);
            current.insert({C[i], i + K});
            pq.push({nextOccurence[i + K], {C[i], i + K}});
        } else {
            auto next = pq.top();
            while (!current.count(next.second.first) || current[next.second.first] != next.second.second) {
                pq.pop();
                next = pq.top();
                cout << pq.size() << endl;
            }

            current.erase(next.second.first);
            pq.pop();
            current.insert({C[i], i + K});
            pq.push({nextOccurence[i + K], {C[i], i + K}});
        }
    }

    for (int i = 0; i < N + K; i++)
        WriteAdvice(needed[i]);
}


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
