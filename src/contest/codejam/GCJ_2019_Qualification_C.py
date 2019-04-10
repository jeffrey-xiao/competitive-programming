#!/usr/bin/env python3


def gcd(a, b):
    return a if b == 0 else gcd(b, a % b)


if __name__ == "__main__":
    T = int(input())

    for t in range(1, T + 1):
        N, L = [int(_) for _ in input().split()]
        pairs = [int(_) for _ in input().split()]
        i = 0
        while pairs[i] == pairs[i + 1]:
            i += 1
        seq = [0] * (L + 1)
        seq[i + 1] = gcd(pairs[i], pairs[i + 1])
        for j in range(i + 1, len(pairs)):
            seq[j + 1] = pairs[j] // seq[j]
        for j in range(i, -1, -1):
            seq[j] = pairs[j] // seq[j + 1]
        val = sorted(list(set(seq)))
        alphaMap = {}
        for i, item in enumerate(val):
            alphaMap[item] = chr(i + 65)

        print("Case #{0}: {1}".format(t, "".join([alphaMap[x] for x in seq])))
