#include <bits/stdc++.h>

using namespace std;

#define SIZE 1000000

struct command{
    char c;
    int a, b;
    command(){}
    command(char c_, int a_){
        c = c_;
        a = a_;
    }
    command(char c_, int a_, int b_){
        c = c_;
        a = a_;
        b = b_;
    }
};

int n, counter;
int tree[SIZE];
command commands[SIZE];
int idToRating[SIZE];
int ratingToId[SIZE];
void update(int idxx, int k){
    for(int x = idxx; x < counter; x += (x & -x))
        tree[x] += k;
}
int freq(int idxx){
    int sum = 0;
    for(int x = idxx; x > 0; x-= (x & -x))
        sum += tree[x];
    return sum;
}
int main(){
    vector<int> id;
    vector<int> rating;
    scanf("%d", &n);
    for(int x = 0; x < n; x++){
        char c;
        scanf(" %c", &c);
        if(c == 'N' || c == 'M'){
            int a, b;
            scanf("%d%d", &a, &b);
            id.push_back(a);
            rating.push_back(b);
            commands[x] = command(c, a, b);
        }else{
            int a;
            scanf("%d", &a);
            commands[x] = command(c, a);
        }
    }
    sort(id.begin(), id.end());
    sort(rating.begin(), rating.end());

    unordered_map<int, int> fromRating;
    unordered_map<int, int> fromId;
    unordered_map<int, int> toId;

    // change to unordered_map for constant time lookup.
    counter = 1;
    for(int x = 0; x < id.size(); x++){
        toId.insert(make_pair(counter, id[x]));
        fromId.insert(make_pair(id[x], counter++));
    }
    counter = 1;
    for(int x = 0; x < rating.size(); x++){
        fromRating.insert(make_pair(rating[x], counter++));
    }
    int totalPeople = 0;
    for(int x = 0; x < n; x++){
        command curr = commands[x];
        if(curr.c == 'N'){
            idToRating[fromId.at(curr.a)] = fromRating.at(curr.b);
            ratingToId[fromRating.at(curr.b)] = fromId.at(curr.a);
            update(idToRating[fromId.at(curr.a)], 1);
            totalPeople++;
        }else if(curr.c == 'M'){
            int currIdIndex = fromId.at(curr.a);
            update(idToRating[currIdIndex], -1);
			idToRating[currIdIndex] = fromRating.at(curr.b);
            ratingToId[fromRating.at(curr.b)] = currIdIndex;
            update(idToRating[currIdIndex], 1);
        }else if(curr.c == 'Q'){
            int lo = 1;
            int hi = counter;
            while(lo <= hi){
                int mid = lo + (hi - lo)/2;
                if(freq(mid) > totalPeople-curr.a)
                    hi = mid - 1;
                else
                    lo = mid + 1;

            }
            int idIndex = ratingToId[lo];
            printf("%d\n", toId.at(idIndex));
        }
    }

    return 0;
}
