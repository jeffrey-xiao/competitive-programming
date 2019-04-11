package contest.ccc;

import java.util.Scanner;

public class CCC_2008_J1 {
  static Scanner scan = new Scanner(System.in);

  public static void main(String[] args) {
    double weight = scan.nextDouble();
    double height = scan.nextDouble();
    double bmi = weight / height / height;
    if (bmi > 25)
      System.out.println("Overweight");
    else if (bmi <= 25 && bmi > 18.5)
      System.out.println("Normal weight");
    else
      System.out.println("Underweight");
  }
}
