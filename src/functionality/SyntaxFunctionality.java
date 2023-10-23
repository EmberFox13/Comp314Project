package functionality;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SyntaxFunctionality {

    private static final Set<String> VALID_DATA_TYPES = Set.of("int", "String", "char", "double", "float", "boolean");
    private static final Set<String> OPERATORS = Set.of("+", "-", "*", "/", "=", "<", ">", "!", "&", "|");
    private static final Set<String> KEYWORDS = Set.of("if", "else", "while", "switch", "System.out.print"); // Add keywords here

    enum TokenType {
        START,
        STRING,
        VALUE,
        VARIABLES,
        OPERATOR,
        COMMENT,
        KEYWORD // Add KEYWORD token type
    }

    public static List<String> analyzeTokens(String tokens) {
        List<String> syntax = new ArrayList<>();
        StringBuilder currentSyntax = new StringBuilder();
        TokenType currentTokenType = TokenType.START;
        boolean inEscapeMode = false;

        for (char c : tokens.toCharArray()) {
            switch (currentTokenType) {
                case START:
                    if (c == '/') {
                        currentTokenType = TokenType.COMMENT;
                        currentSyntax.append(c);
                    } else if (Character.isLetter(c)) {
                        currentTokenType = TokenType.VARIABLES;
                        currentSyntax.append(c);
                    } else if (isOperator(c)) {
                        currentTokenType = TokenType.OPERATOR;
                        currentSyntax.append(c);
                    } else if (Character.isDigit(c)) {
                        currentTokenType = TokenType.VALUE;
                        currentSyntax.append(c);
                    } else if (c == '"') {
                        currentTokenType = TokenType.STRING;
                        currentSyntax.append(c);
                    } else {
                        // Handle invalid characters here
                        syntax.add("Invalid Character: " + c);
                        currentSyntax.setLength(0);
                    }
                    break;

                case VARIABLES:
                    if (Character.isLetterOrDigit(c) || c == '_') {
                        currentSyntax.append(c);
                    } else {
                        String varName = currentSyntax.toString();
                        if (Character.isDigit(varName.charAt(0)) || KEYWORDS.contains(varName)) {
                            // Handle variables starting with a digit or being keywords
                            syntax.add("Invalid Variable: " + varName);
                        } else if (VALID_DATA_TYPES.contains(varName)) {
                            syntax.add("Data Type: " + varName);
                        } else {
                            syntax.add("Variable: " + varName);
                        }
                        currentSyntax.setLength(0);
                        currentTokenType = TokenType.START;
                    }
                    break;

                case VALUE:
                    if (Character.isDigit(c) || (!currentSyntax.isEmpty() && c == '.')) {
                        currentSyntax.append(c);
                    } else if (c == '"') {
                        currentTokenType = TokenType.STRING;
                        currentSyntax.append(c);
                    } else {
                        syntax.add("Value: " + currentSyntax);
                        currentSyntax.setLength(0);
                        currentTokenType = TokenType.START;
                    }
                    break;

                case STRING:
                    currentSyntax.append(c);
                    if (inEscapeMode) {
                        inEscapeMode = false;
                    } else {
                        if (c == '\\') {
                            inEscapeMode = true;
                        } else if (c == '"') {
                            syntax.add("String: " + currentSyntax);
                            currentSyntax.setLength(0);
                            currentTokenType = TokenType.START;
                        }
                    }
                    break;

                case OPERATOR:
                    if (isOperator(c)) {
                        currentSyntax.append(c);
                    } else {
                        syntax.add("Operator: " + currentSyntax);
                        currentSyntax.setLength(0);
                        currentTokenType = TokenType.START;
                    }
                    break;

                case COMMENT:
                    currentSyntax.append(c);
                    if (c == '\n') {
                        syntax.add("Comment: " + currentSyntax);
                        currentSyntax.setLength(0);
                        currentTokenType = TokenType.START;
                    }
                    break;
            }
        }

        if (!currentSyntax.isEmpty()) {
            syntax.add(getTokenTypeLabel(currentTokenType) + currentSyntax);
        }

        return syntax;
    }

    private static String getTokenTypeLabel(TokenType currentTokenType) {
        return switch (currentTokenType) {
            case VARIABLES -> "Variable: ";
            case VALUE -> "Value: ";
            case STRING -> "String: ";
            case OPERATOR -> "Operator: ";
            case COMMENT -> "Comment: ";
            case KEYWORD -> "Keyword: "; // Add case for KEYWORD
            default -> "Unrecognized";
        };
    }

    private static boolean isOperator(char c) {
        return OPERATORS.contains(String.valueOf(c));
    }

    public static void main(String[] args) {
        String inputString = "int sum = num1 + \"10\"; if (condition) { System.out.print(\"Hello\"); }"; // Add a keyword "if"
        List<String> analyzedTokens = analyzeTokens(inputString);

        System.out.println("Tokens: " + analyzedTokens);
    }
}
