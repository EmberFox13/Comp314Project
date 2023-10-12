package functionality;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LambdaCalculusFunctionality {

    public static int evaluateLambdaExpression(String input) {
        // Define a regular expression pattern to match the input format
        Pattern pattern = Pattern.compile("\\(([-+]?\\d*x[+-]\\d+)\\)(\\d+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.matches()) {
            // Extract the operation and values from the input
            String operation = matcher.group(1); // e.g., "2x-7" or "-x+3"
            int xValue = Integer.parseInt(matcher.group(2)); // e.g., 10 or 2

            // Parse the operation to separate the coefficient, 'x', and the constant
            int coefficient = 1; // Default coefficient if not specified
            char operator = operation.charAt(0); // Get the first character which can be '+' or '-'
            if (Character.isDigit(operator)) {
                coefficient = Integer.parseInt(operation.substring(0, operation.indexOf('x')));
            }
            char sign = operation.charAt(operation.indexOf('x') + 1); // Get the operator (+ or -)
            int constant = Integer.parseInt(operation.substring(operation.indexOf('x') + 2)); // Get the constant value

            // Evaluate the expression
            int result;
            result = (sign == '+') ? (coefficient * xValue + constant) : (coefficient * xValue - constant);

            return result;
        } else {
            throw new IllegalArgumentException("Invalid input format. Please use the format ([+-]?nx[+-]m)p.");
        }
    }

    public static void main(String[] args) {
        String input = "(3x-4)10";
        int result = evaluateLambdaExpression(input);
        System.out.println(result);
    }



}
