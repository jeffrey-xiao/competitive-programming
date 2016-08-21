#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include "scales.h"

#define _MAXN 6
#define _MAX_ANSWER_CALLS 100

static int _realC[_MAXN];
static int _ind[_MAXN];
static int _numQueries;
static int _numAnswerCalls;
static FILE * _f;
static FILE * _of;

static int _getNumberOfTests() {
    int T, ret;

    _f = fopen("scales.in", "r");
    _of = fopen("scales.out", "w");

    ret = fscanf(_f, "%d", &T);
    assert(ret == 1);
    return T;
}

static void _initNewTest() {
    int i, ret;

    for (i = 0; i < _MAXN; i++) {
        ret = fscanf(_f, "%d", &_realC[i]);
        assert(ret == 1);
        _realC[i]--;
        _ind[_realC[i]] = i;
    }

    _numQueries = 0;
    _numAnswerCalls = 0;
}

void answer(int W[]) {
    int i;

    _numAnswerCalls++;
    //printf("%d\n", _numAnswerCalls);
    if (_numAnswerCalls > _MAX_ANSWER_CALLS)
        return;

    for (i = 0; i < 6; i++)
        fprintf(_of, "%d ", W[i]);
    fprintf(_of, "\n%d\n", _numQueries);
}

static void _checkQuery(int A, int B, int C, int D) {
    if (D == -1) {
        if (A < 1 || A > 6 || B < 1 || B > 6 || C < 1 || C > 6)
            assert(0);
        if (A == B || B == C || A == C)
            assert(0);
    }
    else {
        if (A < 1 || A > 6 || B < 1 || B > 6 || C < 1 || C > 6 || D < 1 || D > 6)
            assert(0);
        if (A == B || A == C || A == D || B == C || B == D || C == D)
            assert(0);
    }
}

int getMedian(int A, int B, int C) {
    _numQueries++;
    _checkQuery(A, B, C, -1);

    A--; B--; C--;

    if (_ind[B] < _ind[A] && _ind[A] < _ind[C])
        return A + 1;

    if (_ind[C] < _ind[A] && _ind[A] < _ind[B])
        return A + 1;

    if (_ind[A] < _ind[B] && _ind[B] < _ind[C])
        return B + 1;

    if (_ind[C] < _ind[B] && _ind[B] < _ind[A])
        return B + 1;

    return C + 1;
}

int getHeaviest(int A, int B, int C) {
    _numQueries++;
    _checkQuery(A, B, C, -1);

    A--; B--; C--;

    if (_ind[A] > _ind[B] && _ind[A] > _ind[C])
        return A + 1;

    if (_ind[B] > _ind[A] && _ind[B] > _ind[C])
        return B + 1;

    return C + 1;
}

int getLightest(int A, int B, int C) {
    _numQueries++;
    _checkQuery(A, B, C, -1);

    A--; B--; C--;

    if (_ind[A] < _ind[B] && _ind[A] < _ind[C])
        return A + 1;

    if (_ind[B] < _ind[A] && _ind[B] < _ind[C])
        return B + 1;

    return C + 1;
}

int getNextLightest(int A, int B, int C, int D) {
    int allLess = 1;

    _numQueries++;
    _checkQuery(A, B, C, D);

    A--; B--; C--; D--;

    if (_ind[A] > _ind[D] || _ind[B] > _ind[D] || _ind[C] > _ind[D])
        allLess = 0;

    if (allLess == 1) {
        if (_ind[A] < _ind[B] && _ind[A] < _ind[C])
            return A + 1;

        if (_ind[B] < _ind[A] && _ind[B] < _ind[C])
            return B + 1;

        return C + 1;
    }

    if (_ind[A] > _ind[D]) {
        if ((_ind[A] < _ind[B] || _ind[B] < _ind[D]) && (_ind[A] < _ind[C] || _ind[C] < _ind[D]))
            return A + 1;
    }

    if (_ind[B] > _ind[D]) {
        if ((_ind[B] < _ind[A] || _ind[A] < _ind[D]) && (_ind[B] < _ind[C] || _ind[C] < _ind[D]))
            return B + 1;
    }

    return C + 1;
}
