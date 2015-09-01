int tree[17][65536];
void insert(int x) { 
	for (int i=0; i<17; i++) { 
		tree[i][x]++; x/=2;
	} 
}
void erase(int x) { 
	for (int i=0; i<17; i++) { 
		tree[i][x]--; x/=2; 
	} 
}
int kThElement(int k) { 
	int a=0, 
	b=16;
	while (b--) { 
		a*=2; 
		if (tree[b][a]<k) 
			k-=tree[b][a++]; 
	}
	return a; 
}
