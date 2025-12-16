package search_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import search_engine.document.Document;

public class Index {
    private final Map<String, List<Posting>> invertedIndex = new HashMap<>();
    private final Map<Integer, Document> docIdMap = new HashMap<>();
    private int documentCount;

    /**
     * Adds a document object to the index
     * @param document: a document object to be added
     * @return the id to the document added
     */
    public int addDocument(Document document) {
        int id = documentCount++;
        docIdMap.put(id, document);

        List<String> tokens = document.tokeniseContent();

        Map<String, Integer> occurrences = new HashMap<>();

        for (String s : tokens) {
            occurrences.put(s, occurrences.getOrDefault(s,0) + 1);
        }

        occurrences.forEach((key, value) -> {
            List<Posting> newList = invertedIndex.getOrDefault(key, new ArrayList<>());
            newList.add(new Posting(id, value));
            invertedIndex.put(key, newList);
        });

        return id;
    }

    /**
     * fetches all postings that correspond to the query
     * @param document a document containing 
     * @param term a query for all corresponding postings
     * @return returns a list of postings that correspond to the term
     */
    public List<Posting> getPostings(String term) {
        return invertedIndex.get(term);
    }

    /**
     * Retrieves a document from the index
     * @param docId the docId of the document
     * @return the document object
     */
    public Document getDocument(int docId) {
        return docIdMap.get(docId);
    }
    
    /**
     * @return a list of all documents
     */
    public List<Document> getDocuments() {
        return docIdMap.values().stream().toList();
    }
}
