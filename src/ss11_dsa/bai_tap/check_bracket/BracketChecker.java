package ss11_dsa.bai_tap.check_bracket;

import java.util.Stack;

public class BracketChecker {
    public static void main(String[] args) {
        String expr1 = "s * (s - a) * (s - b) * (s - c)";
        String expr2 = "(- b + (b2 - 4*a*c)^0.5) / 2*a";
        String expr3 = "s * (s - a) * (s - b * (s - c)";
        String expr4 = "s * (s - a) * s - b) * (s - c)";
        String expr5 = "(- b + (b^2 - 4*a*c)^(0.5/ 2*a))";
        System.out.println(expr1 + " -> " + (checkBracket(expr1) ? "Well" : "Not well"));
        System.out.println(expr2 + " -> " + (checkBracket(expr2) ? "Well" : "Not well"));
        System.out.println(expr3 + " -> " + (checkBracket(expr3) ? "Well" : "Not well"));
        System.out.println(expr4 + " -> " + (checkBracket(expr4) ? "Well" : "Not well"));
        System.out.println(expr5 + " -> " + (checkBracket(expr5) ? "Well" : "Not well"));
    }

    public static boolean checkBracket(String expression) {
        Stack<Character> bracketStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);
            if (symbol == '(' || symbol == '[' || symbol == '{') {
                bracketStack.push(symbol);
            } else if (symbol == ')' || symbol == ']' || symbol == '}') {
                if (bracketStack.isEmpty()) {
                    return false;
                } else {
                    char left = bracketStack.pop();
                    if ((symbol == ')' && left != '(') ||
                        (symbol == ']' && left != '[') ||
                        (symbol == '}' && left != '{')) {
                        return false;
                    }
                }
            }
        }
        return bracketStack.isEmpty();
    }
}
