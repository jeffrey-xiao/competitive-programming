#include <bits/stdc++.h>

using namespace std;

struct LinkCut {
  struct Node {
    int sz;
    Node *child[2], *par, *pathPar;
    Node(int sz) : sz(sz) {
      child[0] = this;
      child[1] = this;
      par = this;
      pathPar = this;
    }
  };

  static Node *null;
  vector<Node *> nodes;

  LinkCut(int N) : nodes(N) {
    for (int i = 0; i < N; i++) {
      nodes[i] = new Node(1);
      nodes[i]->child[0] = nodes[i]->child[1] = nodes[i]->par = nodes[i]->pathPar = null;
    }
  }

  static void update(Node *u) {
    if (u == null)
      return;
    u->sz = 1 + u->child[0]->sz + u->child[1]->sz;
  }

  static int getDir(Node *u, Node *p) {
    return p->child[0] == u ? 0 : 1;
  }

  static Node *rotate(Node *u, int dir) {
    Node *c = u->child[dir ^ 1], *p = u->par, *pp = p->par;
    p->child[dir] = c;
    c->par = p;
    u->child[dir ^ 1] = p;
    p->par = u;
    u->par = pp;
    pp->child[getDir(p, pp)] = u;

    u->pathPar = p->pathPar;
    p->pathPar = null;
    update(p);
    update(u);
    update(pp);
    return u;
  }

  void splay(Node *u) {
    while (u->par != null) {
      Node *p = u->par, *pp = p->par;
      int dp = getDir(u, p), dpp = getDir(p, pp);
      if (pp == null)
        rotate(u, dp);
      else if (dp == dpp)
        rotate(p, dpp), rotate(u, dp);
      else
        rotate(u, dp), rotate(u, dpp);
    }
  }

  Node *access(Node *u) {
    splay(u);
    if (u->child[1] != null) {
      u->child[1]->pathPar = u;
      u->child[1]->par = null;
      u->child[1] = null;
      update(u);
    }
    Node *w = u;
    while (u->pathPar != null) {
      Node *v = u->pathPar;
      splay(v);
      if (v->child[1] != null) {
        v->child[1]->pathPar = v;
        v->child[1]->par = null;
      }
      v->child[1] = u;
      update(v);
      u->par = v;
      u->pathPar = null;
      u = v;
    }
    splay(w);
    return w;
  }

  // precondition: n must be a root node, and n and m must be in different trees
  void link(Node *n, Node *m) {
    n = access(n);
    m = access(m);
    n->child[0] = m;
    m->par = n;
    update(n);
  }

  // precondition: n must not be a root node
  void cut(Node *n) {
    n = access(n);
    if (n->child[0] != null) {
      n->child[0]->par = null;
      n->child[0] = null;
    }
    update(n);
  }

  Node *getRoot(Node *n) {
    n = access(n);
    while (n->child[0] != null)
      n = n->child[0];
    n = access(n);
    return n;
  }

  int getHeight(Node *n) {
    access(n);
    return n->child[0]->sz + 1;
  }
};

LinkCut::Node *LinkCut::null = new Node(0);

int main() {
  int N;
  scanf("%d", &N);

  LinkCut t(N + 1);
  for (int i = 0; i < N; i++) {
    int val;
    scanf("%d", &val);
    val += i;
    val = min(N, val);
    t.link(t.nodes[i], t.nodes[val]);
  }
  int Q;
  scanf("%d", &Q);
  for (int i = 0; i < Q; i++) {
    int type;
    scanf("%d", &type);
    if (type == 1) {
      int u;
      scanf("%d", &u);
      printf("%d\n", t.getHeight(t.nodes[u]) - 1);
    } else if (type == 2) {
      int u, val;
      scanf("%d%d", &u, &val);
      val = min(N, val + u);
      t.cut(t.nodes[u]);
      t.link(t.nodes[u], t.nodes[val]);
    }
  }
}
