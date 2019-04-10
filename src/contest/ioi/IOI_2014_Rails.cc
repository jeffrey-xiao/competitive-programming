#include <bits/stdc++.h>

using namespace std;

#define mp make_pair
#define pb push_back

typedef pair<int, int> pi;

int dist[5001][5001];

int getDist(int i, int j) {
  if (dist[i][j] != -1)
    return dist[i][j];
  return dist[i][j] = dist[j][i] = getDistance(i, j);
}

void findLocation(int n, int first, int location[], int stype[]) {
  location[0] = first;
  stype[0] = 1;
  int index = -1;
  memset(dist, -1, sizeof dist);
  for (int i = 1; i < n; i++) {
    if (index == -1 || getDist(0, i) < getDist(0, index))
      index = i;
  }
  location[index] = location[0] + getDist(0, index);
  stype[index] = 2;

  vector<pi> rightStations;
  vector<pi> leftStations;
  unordered_set<int> dRightStations;
  unordered_set<int> cLeftStations;
  for (int i = 0; i < n; i++) {
    if (index == i || 0 == i)
      continue;
    if (getDist(0, i) == getDist(0, index) + getDist(i, index) &&
        getDist(index, i) < getDist(index, 0)) {
      location[i] = location[index] - getDist(index, i);
      stype[i] = 1;
    } else if (getDist(0, i) == getDist(0, index) + getDist(index, i)) {
      leftStations.pb(mp(getDist(index, i), i));
    } else {
      rightStations.pb(mp(getDist(0, i), i));
    }
  }
  dRightStations.insert(location[index]);
  sort(rightStations.begin(), rightStations.end());
  int indexLastD = index;
  for (unsigned int i = 0; i < rightStations.size(); i++) {
    int curr = rightStations[i].second;
    int d = (getDist(0, indexLastD) + getDist(indexLastD, curr) - getDist(0, curr)) / 2;
    if (dRightStations.count(location[indexLastD] - d)) {
      stype[curr] = 1;
      location[curr] = location[indexLastD] - getDist(indexLastD, curr);
    } else {
      stype[curr] = 2;
      location[curr] = location[0] + getDist(0, curr);
      indexLastD = curr;
      dRightStations.insert(location[curr]);
    }
    // if location[indexLastD] - d = D station, then curr is a C station
  }
  cLeftStations.insert(location[0]);
  sort(leftStations.begin(), leftStations.end());
  int indexLastC = 0;
  for (unsigned int i = 0; i < leftStations.size(); i++) {
    int curr = leftStations[i].second;
    int d = (getDist(indexLastC, index) + getDist(curr, indexLastC) - getDist(index, curr)) / 2;
    if (cLeftStations.count(location[indexLastC] + d)) {
      stype[curr] = 2;
      location[curr] = location[indexLastC] + getDist(indexLastC, curr);
    } else {
      stype[curr] = 1;
      location[curr] = location[index] - getDist(index, curr);
      indexLastC = curr;
      cLeftStations.insert(location[curr]);
    }
  }
}
