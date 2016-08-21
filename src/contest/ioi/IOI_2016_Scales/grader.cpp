
#include "scales.cpp"

int main() {

    int T, i;

    T = _getNumberOfTests();
    init(T);

    for (i = 1; i <= T; i++) {
        _initNewTest();
        orderCoins();
    }

    return 0;
}
