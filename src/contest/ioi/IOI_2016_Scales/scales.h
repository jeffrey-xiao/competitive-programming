#ifndef __SCALES_H__
#define __SCALES_H__

#ifdef __cplusplus
extern "C" {
#endif

void init(int T);
void orderCoins();
void answer(int W[]);

int getMedian(int A, int B, int C);
int getHeaviest(int A, int B, int C);
int getLightest(int A, int B, int C);
int getNextLightest(int A, int B, int C, int D);

#ifdef __cplusplus
}
#endif

#endif /* __SCALES_H__ */