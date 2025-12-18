package search_engine.search_nodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import search_engine.Posting;

public class OrNode implements SearchNode {
    private List<SearchNode> searchNodes;

    public OrNode(List<SearchNode> searchNodes) {
        this.searchNodes = searchNodes;
    }

    @Override
    public List<Posting> evaluate() {
        if (searchNodes == null || searchNodes.isEmpty()) return new ArrayList<>();

        Set<Posting> postings = new HashSet<>();
        
        searchNodes.forEach(s -> s.evaluate().forEach(p -> postings.add(p)));

        return postings.stream().toList();
    }
}
