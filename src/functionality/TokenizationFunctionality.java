package functionality;

import java.util.ArrayList;
import java.util.List;

public class TokenizationFunctionality {

    enum State {
        START, IDENTIFIER, OPERATOR, STRING_LITERAL, WHITESPACE

    }

    public static List<String> tokenizeString(String input) {
        List<String> tokens = new ArrayList<>();
        State currentState = State.START;
        StringBuilder currentToken = new StringBuilder();

        for (char c : input.toCharArray()) {
            switch (currentState) {
                case START:
                    if (Character.isLetter(c) || c == '.') {
                        currentState = State.IDENTIFIER;
                        currentToken.append(c);
                    } else if (isOperator(c)) {
                        currentState = State.OPERATOR;
                        currentToken.append(c);
                    } else if (c == '"') {
                        currentState = State.STRING_LITERAL;
                        currentToken.append(c);
                    } else if (Character.isWhitespace(c)) {
                        currentState = State.WHITESPACE;
                    }
                    break;

                case IDENTIFIER:
                    if (Character.isLetterOrDigit(c) || c == '.') {
                        currentToken.append(c);
                    } else {
                        tokens.add(currentToken.toString());
                        currentToken.setLength(0);
                        currentState = State.START;
                    }
                    break;

                case OPERATOR:
                    if (isOperator(c)) {
                        currentToken.append(c);
                    } else {
                        tokens.add(currentToken.toString());
                        currentToken.setLength(0);
                        currentState = State.START;
                    }
                    break;

                case STRING_LITERAL:
                    currentToken.append(c);
                    if (c == '"') {
                        tokens.add(currentToken.toString());
                        currentToken.setLength(0);
                        currentState = State.START;
                    }
                    break;

                case WHITESPACE:
                    if (!Character.isWhitespace(c)) {
                        if (Character.isLetter(c) || c == '.') {
                            currentState = State.IDENTIFIER;
                            currentToken.append(c);
                        } else if (isOperator(c)) {
                            currentState = State.OPERATOR;
                            currentToken.append(c);
                        } else if (c == '"') {
                            currentState = State.STRING_LITERAL;
                            currentToken.append(c);
                        }
                    }
                    break;
            }
        }

        // Add the last token, if any
        if (!currentToken.isEmpty()) {
            tokens.add(currentToken.toString());
        }

        return tokens;
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '=' || c == ';'
                || c == '(' || c == ')';
    }

    public static void main(String[] args) {
        String inputString = "System.out.println(\"Hello World!\");";
        List<String> tokens = tokenizeString(inputString);

        System.out.println("Tokens: " + tokens);
    }

}