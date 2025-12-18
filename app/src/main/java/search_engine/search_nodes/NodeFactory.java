package search_engine.search_nodes;

import java.util.Arrays;
import java.util.List;

import search_engine.Index;
import search_engine.Posting;

public class NodeFactory {
    private static String OR = "OR";
    private static String AND = "AND";
    private static String NOT = "NOT";
    private static String IMPLICIT_AND = " ";
    private static int NOT_TAG = 4;

    /**
     * Method to create our composite pattern tree to handle search engine logical operators
     * @param index: search engine index containing token -> List<Posting> mappings
     * @param query: string search query input
     * @return root node SearchNode for the composite tree
     */
    public static SearchNode createNode(Index index, String query) {
        if (query.isEmpty()) return null;

        //Split ORs first
        List<String> ors = cleanSplit(query, OR);
        if (ors.size() > 1) {
            return new OrNode(
                recurse(index, ors)
            );
        }

        // Split ANDs next
        List<String> ands = cleanSplit(query, AND);
        if (ands.size() > 1) {
            return new AndNode(
                recurse(index, ands)
            );
        }

        // Split NOTs
        if (query.startsWith(NOT)) {
            return new NotNode(createNode(index, query.substring(NOT_TAG)), index.getAllPostings());
        }

        // Split adjacent query tokens (implicit and)
        List<String> newAnds = cleanSplit(query, IMPLICIT_AND);
        if (newAnds.size() > 1) {
            return new AndNode(
                recurse(index, newAnds)
            );
        }

        // Leaf node
        List<Posting> postings = index.getPostings(query.toLowerCase());
        if (postings != null) return new TokenNode(postings); 

        return null;
    }

    /**
     * Helper function for createNode, maps a query to a List of search nodes
     * @param index search engine index
     * @param strs list of subqueries
     * @return list of search nodes
     */
    private static List<SearchNode> recurse(Index index, List<String> strs) {
        return strs.stream()
            .map(s -> createNode(index, s))
            .toList();
    }

    /**
     * Helper function for createNode, isolates logical operators from the query
     * @param query search query
     * @param splitter a logical operator string as defined
     * @precondition splitter is a predefined logical operator string constant 
     * @return list of string tokens
     */
    private static List<String> cleanSplit(String query, String splitter) {
        return Arrays.stream(query.split("\\s*" + splitter + "\\s*"))
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .toList();
    }
}