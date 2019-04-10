import sys

UNK = 0
INT = 1
ADD = 2
CON = 3
ASS = 4


def bits(x):
    ret = []
    for i in range(10):
        ret.append(x % 2)
        x /= 2
    return ret


def parse(l, i):
    if l[i] == '[':
        x = l[i + 1]
        a = []
        for j in range(i + 3, len(l)):
            if l[j] == ']':
                break
            a.append(l[j])
        if a == ['?']:
            return (UNK, x), j + 1
        elif len(a) >= 3 and a[1] == '+':
            return (ADD, x, a[0], a[2]), j + 1
        else:
            return (INT, x, int(''.join(map(str, a)))), j + 1
    elif l[i] == '(':
        x = l[i + 1]
        y = l[i + 3]
        k = i + 5
        exs = []
        while l[k] != '}':
            ex, k = parse(l, k)
            exs.append(ex)
        return (CON, x, y, exs), k + 2
    else:
        x = l[i + 1]
        y = l[i + 3]
        return (ASS, x, y), i + 5


def run(l, a, var=None):
    if var is None:
        var = {}
    curunk = 0

    for expr in l:
        if expr[0] == UNK:
            var[expr[1]] = a[curunk]
            curunk += 1
        elif expr[0] == INT:
            var[expr[1]] = expr[2]
        elif expr[0] == ADD:
            var[expr[1]] = var.get(expr[2], 0) + var.get(expr[3], 0)
        elif expr[0] == CON:
            if var.get(expr[1], 0) < var.get(expr[2], 0):
                if not run(expr[3], a, var):
                    return False
        elif expr[0] == ASS:
            if var.get(expr[1], 0) != var.get(expr[2], 0):
                return False
    return True


def runnd(l):
    k = 0
    exprs = []
    while k < len(l):
        ex, k = parse(l, k)
        exprs.append(ex)

    for x in range(1024):
        bs = [int(x) for x in bits(x)]
        if not run(exprs, bs):
            return False
    return True


N = int(input())
for _ in range(N):
    l = input()
    if runnd(l):
        print("ASSERTIONS ALWAYS HOLD")
    else:
        print("ASSERTIONS INVALID")
