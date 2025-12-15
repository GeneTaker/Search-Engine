package search_engine;

import java.util.Arrays;
import java.util.List;

public class Tokeniser {
    /**
     * Converts a string input into a list of tokens
     * @param input string input
     * @return a list of string tokens
     */
    public static List<String> tokenise(String input) {
        input = input.toLowerCase();
        input = input.replaceAll("\\p{Punct}", "");

        return Arrays.asList(input.split("\\s+"));
    }
}
