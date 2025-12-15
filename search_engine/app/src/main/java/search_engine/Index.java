package search_engine;

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
        return 0;
    }

    /**
     * fetches all postings that correspond to the query
     * @param term a query for all corresponding postings
     * @return returns a list of postings that correspond to the term
     */
    public List<Posting> getPostings(String term) {
        return null;
    }

    /**
     * Retrieves a document from the index
     * @param docId the docId of the document
     * @return the document object
     */
    public Document getDocument(int docId) {
        return null;
    }

    /**
     * Tokenises a string of text
     * @param text the text input
     * @return a list of tokens
     */
    private List<String> tokenise(String text) {
        return null;
    }

    /**
     * @return a list of all documents
     */
    public List<Document> getDocuments() {
        return docIdMap.values().stream().toList();
    }
}
