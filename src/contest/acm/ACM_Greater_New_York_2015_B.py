def choose(x, y):
    ret = 1
    for i in range(x - y + 1, x + 1):
        ret *= i
    for i in range(1, y + 1):
        ret //= i
    return ret

T = int(input())

for _ in range(T):
    x, y = map(int, input().split())
    ans = 0
    for i in range(y+1):
        for j in range(i+1):
            if 4 * i + 2 * j != y:
                continue
            ans += choose(i + j, j) ** 2
    print(x, ans)