#include <stdio.h>
#include <stdlib.h>

static int _N;
static int _dist[110][110];
static int _quota, _user_query;

void _ini_query(int N, int k) {
    int ret;
    _N = N;
    for (int i = 0; i < _N; i++)
        for (int j = 0; j < _N; j++)  {
	    ret = scanf("%d", &_dist[i][j]);
            assert(ret == 1);
        }
    if (k == 1 || k == 3) _quota = _N * (_N - 1) / 2;
    else if (k == 2 || k == 4 || k == 6) _quota = (7 * _N + 1) / 2;
    else _quota = 5 * _N;
    _user_query = 0;
}


int getDistance(int i, int j) {
    _user_query++;
    if (_user_query > _quota) {
        printf("0\n");
        exit(0);
    }
    if (i < 0 || i >= _N) return 0;
    if (j < 0 || j >= _N) return 0;
    return _dist[i][j];
}

