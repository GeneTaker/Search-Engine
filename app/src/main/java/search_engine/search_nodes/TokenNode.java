package search_engine.search_nodes;

import java.util.List;

import search_engine.Posting;

public class TokenNode implements SearchNode {
    private List<Posting> postings;

    public TokenNode(List<Posting> postings) {
        this.postings = postings;
    }

    @Override
    public List<Posting> evaluate() {
        return postings;
    }
}
