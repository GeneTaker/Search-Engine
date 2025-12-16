package search_engine.search_nodes;

import java.util.List;

import search_engine.Posting;

public class NotNode implements SearchNode {
    private SearchNode child;
    private List<Posting> allPostings;

    public NotNode(SearchNode child, List<Posting> allPostings) {
        this.child = child;
        this.allPostings = allPostings;
    }

    @Override
    public List<Posting> evaluate() {
        List<Posting> original = child.evaluate();
        allPostings.removeAll(original);
        
        return allPostings;
    }
}
