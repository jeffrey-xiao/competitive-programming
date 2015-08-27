package contest.ccc;

import java.util.*;

public class CCC_2014_J3 {
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args){
		int n = scan.nextInt();
		int antonia = 100;
		int david = 100;
		for(int x = 0; x < n; x++){
			int a = scan.nextInt();
			int d = scan.nextInt();
			if(a>d)
				david-=a;
			else if(a<d)
				antonia-=d;
		}
		System.out.println(antonia+"\n"+david);
	}
}