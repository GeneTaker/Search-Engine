package search_engine.search_nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import search_engine.Posting;

public class AndNode implements SearchNode {
    private SearchNode leftChild;
    private SearchNode rightChild;

    public AndNode(SearchNode leftChild, SearchNode rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public List<Posting> evaluate() {
        List<Posting> leftList = leftChild.evaluate();
        List<Posting> rightList = rightChild.evaluate();

        List<Posting> result = new ArrayList<>();
        
        Map<Posting, Integer> seen = new HashMap<>();
        leftList.forEach(l -> seen.put(l, 1));
        
        rightList.forEach(r -> {
            if (seen.containsKey(r)) result.add(r);
        });

        return result;
    }
}
