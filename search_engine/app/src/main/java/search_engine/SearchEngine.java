package search_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import search_engine.document.Document;
import search_engine.search_nodes.NodeFactory;
import search_engine.search_nodes.SearchNode;

public class SearchEngine {
    private Index index = new Index();
    private ResponseFormatter formatter = new ResponseFormatter();

    /**
     * Adds documents to the index
     * @param documents: the list of documents to be added
     */
    public void addDocuments(List<Document> documents) {
        documents.forEach(d -> index.addDocument(d));
    }

    /**
     * Retrieves a document from the index
     * @param docId
     * @return the corresponding document
     */
    public Document getDocument(int docId) {
        return index.getDocument(docId);
    }

    /**
     * Retrieves a list of search results
     * @param query the search term used
     * @return a list of search results
     */
    public List<SearchResult> search(String query) {
        SearchNode node = NodeFactory.createNode(index, query);

        if (node == null) return new ArrayList<>();
        // node.prettyPrint();

        List<Posting> postings = node.evaluate();
        
        Map<Integer, Integer> map = new HashMap<>();
        postings.forEach(p -> map.put(p.getId(), map.getOrDefault(p.getId(), 0) + p.getFrequency()));
        
        
        List<SearchResult> result = new ArrayList<>();
        
        map.forEach((key, value) -> result.add(new SearchResult(key, value, index.getTitle(key))));

        return result;
    }

    /**
     * @return All documents from the index
     */
    public List<Document> getDocuments() {
        return index.getDocuments();
    }
}
