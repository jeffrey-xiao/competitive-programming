N = int(input())

RES = {}
def result(n, m, k):
    if (n, m, k) in RES:
        return RES[n,m,k]
    else:
        RES[n,m,k] = _result(n, m, k)
        return RES[n,m,k]

def _result(n, m, k):
    if n == 0:
        return 1
    else:
        s = 0
        for l in range(1, n+1):
            if (l - m) % k == 0:
                continue
            s += result(n - l, m, k)
        return s

for i in range(N):
    K, n, m, k = map(int, input().split())
    print(K, result(n, m, k))
