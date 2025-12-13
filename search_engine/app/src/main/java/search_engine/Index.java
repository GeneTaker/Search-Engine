package search_engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import search_engine.document.Document;

public class Index {
    private final Map<String, List<Posting>> invertedIndex = new HashMap<>();
    private final Map<Integer, Document> docIdMap = new HashMap<>();
    private int documentCount;

    public int addDocument() {
        return 0;
    }

    public List<Posting> getPostings(String term) {
        return null;
    }

    public Document getDocument(int docId) {
        return null;
    }

    public List<String> tokenise(String text) {
        return null;
    }

}
