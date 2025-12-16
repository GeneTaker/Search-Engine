package search_engine.search_nodes;

import java.util.List;

import search_engine.Posting;

public interface SearchNode {
    public List<Posting> evaluate();
}
