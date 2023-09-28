package functionality;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TokenizationFunctionality {

    enum State {
        START,
        ALPHABET,
        DIGIT,
        OPERATOR,
        WHITESPACE,
        SPECIAL_CHAR

    }

    public static List<String> tokenizeString(String input) {
        List<String> tokens = new ArrayList<>();
        StringBuilder currentToken = new StringBuilder();
        State currentState = State.START;
        Queue<Character> charQueue = new LinkedList<>();

        for (char c : input.toCharArray()) {
            charQueue.offer(c);
        }

        while (!charQueue.isEmpty()) {
            char c = charQueue.peek();

            switch (currentState) {
                case START -> {
                    if (Character.isLetter(c)) {
                        currentToken.setLength(0);
                        currentToken.append(c);
                        charQueue.poll();
                        currentState = State.ALPHABET;

                    } else if (Character.isDigit(c)) {
                        currentToken.setLength(0);
                        currentToken.append(c);
                        charQueue.poll();
                        currentState = State.DIGIT;

                    } else if (isOperator(c)) {
                        currentToken.setLength(0);
                        currentToken.append(c);
                        charQueue.poll();
                        currentState = State.OPERATOR;

                    } else if (Character.isWhitespace(c)) {
                        currentToken.setLength(0);
                        currentToken.append(c);
                        charQueue.poll();
                        currentState = State.WHITESPACE;

                    } else {
                        currentToken.setLength(0);
                        currentToken.append(c);
                        charQueue.poll();
                        currentState = State.SPECIAL_CHAR;

                    }
                } case ALPHABET -> {
                    if (Character.isLetter(c) || Character.isDigit(c)) {
                        currentToken.append(c);
                        charQueue.poll();
                    } else {
                        tokens.add(currentToken.toString());
                        currentState = State.START;

                    }
                } case DIGIT -> {
                    if (Character.isDigit(c)) {
                        currentToken.append(c);
                        charQueue.poll();
                    } else {
                        tokens.add(currentToken.toString());
                        currentState = State.START;

                    }

                } case WHITESPACE -> {
                    if (Character.isWhitespace(c)) {
                        currentToken.append(c);
                        charQueue.poll();
                    } else {
                        tokens.add(currentToken.toString());
                        currentState = State.START;

                    }

                } case OPERATOR -> {
                    if (isOperator(c)) {
                        currentToken.append(c);
                        charQueue.poll();
                    } else {
                        tokens.add(currentToken.toString());
                        currentState = State.START;

                    }
                } case SPECIAL_CHAR -> {
                    if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c) && !isOperator(c)) {
                        currentToken.append(c);
                        charQueue.poll();
                    } else {
                        tokens.add(currentToken.toString());
                        currentState = State.START;

                    }

                }

            }

        }
        if (!currentToken.isEmpty()) {
            tokens.add(currentToken.toString());

        }

        return tokens;

    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '=' || c == ';'
                || c == '(' || c == ')' || c == '.' || c == '%' || c == '!' || c == '<'
                || c == '>' || c == '|' || c == '&';

    }

    public static void main(String[] args) {
        String inputString = "System.out.println(\"Hello World!!!\")";
        List<String> tokens = tokenizeString(inputString);

        System.out.println("Tokens: " + tokens);

    }

}
