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
    private static double k1 = 1.5;
    private static double b = 0.75;

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

        List<Posting> postings = node.evaluate();

        double averageDocLength = Document.getAllLengthAverage();

        Map<Integer, Double> scores = new HashMap<>();
        for (Posting p : postings) {
            int docId = p.getId();
            int termFrequency = p.getFrequency();
            int docLength = index.getDocLength(docId);

            double idf = index.getGlobalIdf(p.getTerm());

            scores.put(docId, scores.getOrDefault(docId, 0.0) + idf * termFrequency *
                (k1 + 1) / (termFrequency + k1 * (1 - b + b * docLength / averageDocLength)));
        }
        
        List<SearchResult> result = new ArrayList<>();
        scores.forEach((key, value) -> result.add(new SearchResult(key, value, index.getTitle(key))));
        result.sort((a, b) -> b.compareTo(a));

        return result;
    }

    /**
     * @return All documents from the index
     */
    public List<Document> getDocuments() {
        return index.getDocuments();
    }

    /**
     * @returns the title and contents of a docuemnt
     */
    public String openDocument(SearchResult result) {
        Document doc = index.getDocument(result.getDocId());
        return doc.toString();
    }
}
