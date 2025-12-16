package search_engine.search_nodes;

import java.util.List;

import search_engine.Posting;

public class OrNode implements SearchNode {
    private SearchNode leftChild;
    private SearchNode rightChild;

    public OrNode(SearchNode leftChild, SearchNode rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public List<Posting> evaluate() {
        return null;
    }
}
