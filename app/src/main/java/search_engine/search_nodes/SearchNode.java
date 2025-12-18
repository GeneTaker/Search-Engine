package search_engine.search_nodes;

import java.util.List;

import search_engine.Posting;

public interface SearchNode {
    /**
     * @return a list of postings that satisfy the original constraints
     */
    public List<Posting> evaluate();
}
