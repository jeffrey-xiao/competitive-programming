#ifndef INAHO_H_
#define INAHO_H_

#ifdef __cplusplus
extern "C" {
#endif /* __cplusplus */

    void Init(int N);
    void AddEdge(int U, int V);
    void RemoveLastEdge();
    int GetSize(int U);

#ifdef __cplusplus
}
#endif /* __cplusplus */

#endif  /* INAHO_H_ */
