import java.io.*;
import java.util.Stack;

public class GroupingSymbolsChecker {

    // Method to check if a source file has balanced grouping symbols
    public static boolean isBalanced(String filename) {
        Stack<Character> stack = new Stack<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (char ch : line.toCharArray()) {
                    // Check for opening symbols
                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    }
                    // Check for closing symbols
                    else if (ch == ')' || ch == '}' || ch == ']') {
                        // If stack is empty or mismatched closing symbol, return false
                        if (stack.isEmpty()) {
                            return false;
                        }
                        char top = stack.pop();
                        if (!isMatchingPair(top, ch)) {
                            return false;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return false;
        }
        
        // If the stack is empty, the symbols are balanced
        return stack.isEmpty();
    }

    // Helper method to check if the opening and closing symbols match
    private static boolean isMatchingPair(char opening, char closing) {
        return (opening == '(' && closing == ')') ||
               (opening == '{' && closing == '}') ||
               (opening == '[' && closing == ']');
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java GroupingSymbolsChecker <filename>");
            return;
        }

        String filename = args[0];

        // Check if the file has balanced grouping symbols
        if (isBalanced(filename)) {
            System.out.println("The grouping symbols are balanced.");
        } else {
            System.out.println("The grouping symbols are not balanced.");
        }
    }
}