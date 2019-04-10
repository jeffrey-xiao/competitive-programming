#include <bits/stdc++.h>

using namespace std;

int n;
struct node {
  char c;
  int depth;
  node *par[20];
  node *child[26];
  node(char c, int depth, node *parent) {
    this->c = c;
    this->depth = depth;
    for (int i = 0; i < 26; i++)
      child[i] = NULL;
    for (int i = 0; i < 20; i++)
      par[i] = NULL;
    par[0] = parent;
    for (int i = 1; i < 20; i++)
      if (par[i - 1] != NULL)
        par[i] = par[i - 1]->par[i - 1];
  }
};
node *add(node *n, char c) {
  if (n->child[c - 'a'] == NULL)
    n->child[c - 'a'] = new node(c, n->depth + 1, n);
  return n->child[c - 'a'];
}
char getCharacter(node *n, int depth) {
  node *curr = n;
  while (true) {
    if (curr->depth == depth)
      return curr->c;
    for (int i = 19; i >= 0; i--)
      if (curr->par[i] != NULL && curr->par[i]->depth >= depth) {
        curr = curr->par[i];
        break;
      }
  }
}

node *state[1000001];
int main() {
  scanf("%d", &n);
  node root = node('\u0000', 0, NULL);
  int currState = 0;
  state[0] = &root;
  for (int i = 0; i < n; i++) {
    char in;
    scanf(" %c", &in);
    if (in == 'T') {
      char c;
      scanf(" %c", &c);
      state[currState + 1] = add(state[currState], c);
      currState++;
    } else if (in == 'U') {
      int u;
      scanf("%d", &u);
      state[currState + 1] = state[currState - u];
      currState++;
    } else {
      int depth;
      scanf("%d", &depth);
      printf("%c\n", getCharacter(state[currState], depth + 1));
    }
  }
}
