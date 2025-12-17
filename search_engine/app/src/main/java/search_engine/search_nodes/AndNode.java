package search_engine.search_nodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import search_engine.Posting;

public class AndNode implements SearchNode {
    private List<SearchNode> searchNodes;

    public AndNode(List<SearchNode> searchNodes) {
        this.searchNodes = searchNodes;
    }
    
    @Override
    public List<Posting> evaluate() {
        if (searchNodes == null || searchNodes.isEmpty()) return new ArrayList<>();

        List<Posting> leftList = searchNodes.get(0).evaluate();
        
        for (int i = 1; i < searchNodes.size(); i++) {
            SearchNode node = searchNodes.get(i);
            if (node == null) return new ArrayList<>();

            leftList = intersect(leftList, node.evaluate());
        }
        
        return leftList;
    }

    @Override
    public void prettyPrint() {
        System.err.print("AND {");
        
        searchNodes.forEach(s -> {
            s.prettyPrint();
            System.err.println("|");
        });

        System.err.println("\n}");
    }

    public List<Posting> intersect(List<Posting> left, List<Posting> right) {        
        List<Posting> result = new ArrayList<>();
        
        Set<Posting> set = new HashSet<>(right);
        
        left.forEach(r -> {
            if (set.contains(r)) result.add(r);
        });

        return result;
    }
}
