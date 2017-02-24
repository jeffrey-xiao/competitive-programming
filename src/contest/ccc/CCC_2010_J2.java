package contest.ccc;

import java.util.Scanner;

class CCC_2010_J2 {

	public static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int NikkyForward = scan.nextInt();
		int NikkyBackward = scan.nextInt();
		int ByronForward = scan.nextInt();
		int ByronBackward = scan.nextInt();
		int steps = scan.nextInt();
		int NikkySteps;
		int ByronSteps;
		int NikkyAverageForward = NikkyForward - NikkyBackward;
		int ByronAverageForward = ByronForward - ByronBackward;
		int NTotal = 0;
		int BTotal = 0;
		NikkySteps = (int)Math.floor(steps / (NikkyForward + NikkyBackward)) * NikkyAverageForward;
		ByronSteps = (int)Math.floor(steps / (ByronForward + ByronBackward)) * ByronAverageForward;
		NTotal = (int)Math.floor(steps / (NikkyForward + NikkyBackward)) * (NikkyForward + NikkyBackward);
		BTotal = (int)Math.floor(steps / (ByronForward + ByronBackward)) * (ByronForward + ByronBackward);
		if (NTotal + NikkyForward > steps) {
			NikkySteps += steps - NTotal;
		} else if (NikkySteps + NikkyForward + NikkyBackward > steps) {
			NikkySteps += NikkyForward - (steps - NTotal);
		}
		if (BTotal + ByronForward > steps) {
			ByronSteps += steps - BTotal;
		} else if (ByronSteps + ByronForward + ByronBackward > steps) {
			ByronSteps += ByronForward - (steps - BTotal);
		}
		if (NikkySteps == ByronSteps) {
			System.out.println("Tied");
			return;
		}
		System.out.println(NikkySteps > ByronSteps ? "Nikky" : "Byron");
	}
}