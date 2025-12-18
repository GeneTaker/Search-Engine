package search_engine.search_nodes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        if (child == null) {
            return allPostings;
        }

        List<Posting> original = child.evaluate();

        List<Integer> remove = original.stream().distinct().map(o -> o.getId()).toList();

        Set<Integer> set = new HashSet<>();
        remove.forEach(r -> set.add(r));

        allPostings = allPostings.stream().filter(a -> !set.contains(a.getId())).toList();
        
        return allPostings;
    }

}
