#include <bits/stdc++.h>

#ifndef TRUE
#define TRUE 1
#endif
#ifndef FALSE
#define FALSE 0
#endif

#define inbuf_len 1 << 16
#define outbuf_len 1 << 16

#include "advisor.cpp"
#include "assistant.cpp"

static int N, K, M;
static int *C;

static int R;
static unsigned char *A;

static FILE *fadvice;

static int current_request = -1;
static int *in_scaffold;
static int expect_put_back;

int GetRequest(void) {
  int req;

  if (expect_put_back) {
    fprintf(stderr, "Not putting back color when it is not on the scaffold\n");
    exit(1);
  }

  req = C[++current_request];

  if (!in_scaffold[req])
    expect_put_back = TRUE;
  else
    expect_put_back = FALSE;

  printf("R %d\n", req);
  return req;
}

void PutBack(int T) {
  int req;

  if (!expect_put_back) {
    fprintf(stderr, "Putting back a color when it is already on the scaffold\n");
    exit(1);
  }

  if (!in_scaffold[T]) {
    fprintf(stderr, "Putting back a color that is not on the scaffold\n");
    exit(1);
  }

  req = C[current_request];
  in_scaffold[req] = TRUE;
  in_scaffold[T] = FALSE;
  expect_put_back = FALSE;

  printf("P %d\n", T);
}

void WriteAdvice(unsigned char a) {
  /* Normalize a to be 0 or 1 */
  a = !!a;

  if (R < M) {
    fprintf(fadvice, "%hhu ", a);
    A[R] = a;
    R++;
  } else {
    fprintf(stderr, "Advisor is providing too many bits of advice\n");
    exit(1);
  };
}

static inline void input_assert(int ok) {
  if (!ok) {
    fprintf(stderr, "Invalid input file.\n");
    exit(1);
  }
}

int main() {
  int tmp;
  int i;

  /* Set input and output buffering */
  char *inbuf, *outbuf;
  inbuf = (char*) malloc(inbuf_len * sizeof(char));
  outbuf = (char*) malloc(outbuf_len * sizeof(char));
  tmp = setvbuf(stdin, inbuf, _IOFBF, inbuf_len);
  tmp = setvbuf(stdout, outbuf, _IOFBF, outbuf_len);

  fadvice = fopen("advice.txt", "w");
  freopen("input.txt", "r", stdin);
  tmp = scanf("%d %d %d", &N, &K, &M);
  input_assert(tmp == 3);
  C = (int*) malloc(N * sizeof(int));
  for (i = 0; i < N; i++) {
    tmp = scanf("%d", &C[i]);
    input_assert(tmp == 1);
  }

  A = (unsigned char*) malloc(M * sizeof(int));
  ComputeAdvice(C, N, K, M);
  fprintf(fadvice, "\n2\n");

  /* Init the scaffold */
  in_scaffold = (int*) malloc(N * sizeof(int));
  for (i = 0; i < K; i++)
    in_scaffold[i] = TRUE;
  for (i = K; i < N; i++)
    in_scaffold[i] = FALSE;

  Assist(A, N, K, R);
  printf("E\n");

  return 0;
}
