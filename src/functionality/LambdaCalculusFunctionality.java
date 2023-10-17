package functionality;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LambdaCalculusFunctionality {

    public static int evaluateLambdaExpression(String input) {
        // Define a regular expression pattern to match the input format
        Pattern pattern = Pattern.compile("\\(([\\-+]?\\d*x[\\-+]?\\d*)\\)(\\d+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            // Extract the operation and values from the input
            String operation = matcher.group(1); // e.g., "2x-7" or "-x+3"
            int xValue = Integer.parseInt(matcher.group(2)); // e.g., 10 or 2

            // Parse the operation to separate the coefficient, 'x', and the constant
            String[] parts = operation.split("x");
            int coefficient = parts.length > 1 ? Integer.parseInt(parts[0]) : 1;
            char sign = (operation.indexOf('+') != -1) ? '+' : '-';
            int constant = Integer.parseInt(parts[1].replaceAll("[\\-+]", ""));

            // Evaluate the expression
            int result = (sign == '+') ? (coefficient * xValue + constant) : (coefficient * xValue - constant);

            return result;
        } else {
            throw new IllegalArgumentException("Invalid input format. Please use the format ([+-]?nx[+-]m)p.");
        }
    }

    public static void main(String[] args) {
        String input1 = "(3x-4)10";
        int result1 = evaluateLambdaExpression(input1);
        System.out.println("Result 1: " + result1);

        String input2 = "(-2x+7)5";
        int result2 = evaluateLambdaExpression(input2);
        System.out.println("Result 2: " + result2);
    }
}
