/*
 * The shunting yard algorithm is a method of parsing mathematical expressions. It is stack-based.
 *
 * Time complexity: O(N) where N is the length of the sequence to evaluate.
 */

package codebook.algorithms;

import java.util.LinkedList;

public class ShuntingYard {
  private static boolean isDelim(char c) {
    return c == ' ';
  }

  private static boolean isOperator(char c) {
    return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
  }

  private static int getPriority(char op) {
    switch (op) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
      case '%':
        return 2;
      default:
        return -1;
    }
  }

  static void operate(LinkedList<Integer> st, char op) {
    int r = st.removeLast();
    int l = st.removeLast();
    switch (op) {
      case '+':
        st.add(l + r);
        break;
      case '-':
        st.add(l - r);
        break;
      case '*':
        st.add(l * r);
        break;
      case '/':
        st.add(l / r);
        break;
      case '%':
        st.add(l % r);
        break;
    }
  }

  public static int eval(String s) {
    LinkedList<Integer> st = new LinkedList<>();
    LinkedList<Character> op = new LinkedList<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (isDelim(c))
        continue;
      if (c == '(') {
        op.add('(');
      } else if (c == ')') {
        while (op.getLast() != '(')
          operate(st, op.removeLast());
        op.removeLast();
      } else if (isOperator(c)) {
        while (!op.isEmpty() && getPriority(c) <= getPriority(op.getLast()))
          operate(st, op.removeLast());
        op.add(c);
      } else {
        int operand = 0;
        while (i < s.length() && '0' <= s.charAt(i) && s.charAt(i) <= '9')
          operand = operand * 10 + s.charAt(i++) - '0';
        --i;
        st.add(operand);
      }
    }
    while (!op.isEmpty())
      operate(st, op.removeLast());
    return st.get(0);
  }

  public static void main(String[] args) {
    System.out.println(eval("(1+3)*((5+2)*3)"));
  }
}
