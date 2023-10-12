package functionality;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import functionality.TokenizationFunctionality;
public class SyntaxFunctionality {
    enum State{
        START,
        STRING,
        VALUE,
        DATA_TYPES,
        CLASS,
        VARIABLES,
        FUNCTIONS,
        OPERATOR
    }

    public static List<String> analyzeTokens(List<String> tokens) {
        List<String> syntax = new ArrayList<>();
        StringBuilder currentSyntax = new StringBuilder();
        SyntaxFunctionality.State currentState = SyntaxFunctionality.State.START;
        Queue<Character> charQueue = new LinkedList<>();




        return syntax;
    }

}
