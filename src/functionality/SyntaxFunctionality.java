package functionality;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static functionality.TokenizationFunctionality.isOperator;

public class SyntaxFunctionality {

    private static final Set<String> VALID_DATA_TYPES = Set.of("int",
            "String", "char", "double", "float", "boolean");

    enum State {
        START,
        STRING,
        VALUE,
        VARIABLES,
        OPERATOR

    }

    public static List<String> analyzeTokens(String tokens) {
        List<String> syntax = new ArrayList<>();
        StringBuilder currentSyntax = new StringBuilder();
        SyntaxFunctionality.State currentState = SyntaxFunctionality.State.START;

        for (char c : tokens.toCharArray()) {

            switch (currentState) {
                case START:
                    if (Character.isLetter(c)) {
                        currentState = State.VARIABLES;
                        currentSyntax.append(c);

                    } else if (isOperator(c)) {
                        currentState = State.OPERATOR;
                        currentSyntax.append(c);

                    } else if (Character.isDigit(c)) {
                        currentState = State.VALUE;
                        currentSyntax.append(c);

                    } else if (c == '"') {
                        currentState = State.STRING;
                        currentSyntax.append(c);

                    }
                    break;

                case VARIABLES:
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        currentSyntax.append(c);
                    } else {
                        if (isDataType(currentSyntax.toString())) {
                            syntax.add("Data Type: " + currentSyntax);
                        } else {
                            syntax.add("Variable: " + currentSyntax);
                        }
                        currentSyntax.setLength(0);
                        currentState = State.START;
                    }
                    break;

                case VALUE:
                    if (Character.isDigit(c) || c == '.') {
                        currentSyntax.append(c);
                    } else {
                        syntax.add("Value: " + currentSyntax);
                        currentSyntax.setLength(0);
                        currentState = State.START;
                    }
                    break;

                case STRING:
                    currentSyntax.append(c);
                    if (c == '"') {
                        syntax.add("String: " + currentSyntax);
                        currentSyntax.setLength(0);
                        currentState = State.START;
                    }
                    break;

                case OPERATOR:
                    if (isOperator(c)) {
                        currentSyntax.append(c);
                    } else {
                        syntax.add("Operator: " + currentSyntax);
                        currentSyntax.setLength(0);
                        currentState = State.START;
                    }
                    break;
            }

        }

        // Add the last token if any
        if (!currentSyntax.isEmpty()) {
            syntax.add(getTokenTypeLabel(currentState) + currentSyntax);

        }

        return syntax;
    }

    private static String getTokenTypeLabel(State currentState) {
        return switch (currentState) {
            case VARIABLES -> "Variable: ";
            case VALUE -> "Value: ";
            case STRING -> "String: ";
            case OPERATOR -> "Operator: ";
            default -> "Unrecognized";
        };
    }

    static boolean isDataType(String token) {
        return VALID_DATA_TYPES.contains(token);

    }

    public static void main(String[] args) {
        String inputString = "int sum = num1 + 10;";
        List<String> analyzedTokens = analyzeTokens(inputString);

        System.out.println("Tokens: " + analyzedTokens);
    }

}
