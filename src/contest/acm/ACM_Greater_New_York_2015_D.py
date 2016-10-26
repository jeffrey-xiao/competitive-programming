primes = []
for i in range(2, 10000):
    for p in primes:
        if i % p == 0:
            break
    else:
        primes.append(i)

phi = [0 for i in range(10001)]
for i in range(2, 10001):
    for p in primes:
        if i % p == 0:
            pwr = 0
            ic = i
            while ic % p == 0:
                pwr += 1
                ic //= p
            if ic == 1:
                phi[i] = i - p ** (pwr - 1)
            else:
                phi[i] = (p ** pwr - p ** (pwr - 1)) * phi[ic]
            break

pfx = [0]
for i in phi:
    pfx.append(pfx[-1] + i)
N = int(input())

for i in range(N):
    x, y = map(int, input().split())
    print(x, pfx[y+1] + 2)
