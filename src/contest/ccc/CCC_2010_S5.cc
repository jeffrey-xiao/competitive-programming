#include <bits/stdc++.h>

using namespace std;

struct node {
  node *parent;
  node *left;
  node *right;
  int value;
  int dp[2501];
  node() {}
  node(int value) {
    this->value = value;
    left = NULL;
    right = NULL;
    parent = NULL;
  }
};
node tree[150];
int c = 1;
int g;
void compute(node *curr) {

  if (curr->value != -1) {
    for (int z = 0; z <= g; z++) {
      curr->dp[z] = curr->value + z;
    }
    return;
  }
  compute(curr->left);
  compute(curr->right);
  int dpl[2501];
  int dpr[2501];
  for (int z = 0; z <= g; z++) {
    dpl[z] = dpr[z] = 0;
    for (int i = 0; i <= z; i++) {
      dpl[z] = max(dpl[z], min(curr->left->dp[i], (1 + z - i) * (1 + z - i)));
      dpr[z] = max(dpr[z], min(curr->right->dp[i], (1 + z - i) * (1 + z - i)));
    }
  }
  for (int z = 0; z <= g; z++) {
    curr->dp[z] = 0;
    for (int i = 0; i <= z; i++) {
      curr->dp[z] = max(curr->dp[z], dpl[i] + dpr[z - i]);
    }
  }
}
int main() {
  string s;
  getline(cin, s);
  tree[0] = node(-1);
  node *curr = &tree[0];
  if (s[0] != '(') {
    int val = 0;
    int x = 0;
    while (s[x] >= '0' && s[x] <= '9') {
      val = (val * 10) + (s[x++] - '0');
    }
    tree[0].value = val;
  } else {
    for (int x = 1; x < s.length(); x++) {
      if (s[x] == ' ' || s[x] == ')')
        continue;
      else if (s[x] == '(') {
        tree[c] = node(-1);
        if (curr->left == NULL) {
          curr->left = &tree[c];
          curr->left->parent = curr;
          curr = curr->left;
        } else {
          curr->right = &tree[c];
          curr->right->parent = curr;
          curr = curr->right;
        }
        c++;
      } else {

        int val = 0;
        while (s[x] >= '0' && s[x] <= '9') {
          val = (val * 10) + (s[x++] - '0');
        }
        tree[c] = node(val);
        if (curr->left == NULL) {
          curr->left = &tree[c];
          curr->left->parent = curr;
          curr = curr->left;
        } else {
          curr->right = &tree[c];
          curr->right->parent = curr;
          curr = curr->right;
        }
        c++;
        curr = curr->parent;
      }
      while (curr->right != NULL && curr->left != NULL && curr->parent != NULL) {
        curr = curr->parent;
      }
    }
  }
  scanf("%d", &g);
  compute(&tree[0]);
  printf("%d", tree[0].dp[g]);
  return 0;
}
