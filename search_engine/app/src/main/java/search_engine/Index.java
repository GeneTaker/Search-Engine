package search_engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

        List<String> tokens = tokenise(document);

        Map<String, Integer> occurrences = new HashMap<>();

        for (String s : tokens) {
            occurrences.put(s, occurrences.getOrDefault(s,0) + 1);
        }

        occurrences.forEach((key, value) -> {
            List<Posting> newList = invertedIndex.getOrDefault(key, new ArrayList<>());
            newList.add(new Posting(id, key, value));
            invertedIndex.put(key, newList);
        });

        return id;
    }

    /**
     * Helper function to combine title and content tokens for now
     * @param document 
     * @return list of tokens
     */
    private List<String> tokenise(Document document) {
        List<String> result = document.tokeniseTitle();
        result.addAll(document.tokeniseContent());
        return result;
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
     * @return a shallow copy of all existing postings in the index
     */
    public List<Posting> getAllPostings() {
        return new ArrayList<>(invertedIndex.values().stream().flatMap(List::stream).toList());
    }

    /**
     * Retrieves the title of a document given its docId
     * @param docId int
     * @return the title
     */

    public String getTitle(int docId) {
        return docIdMap.get(docId).getTitle();
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

    public int getDocLength(int docId) {
        return docIdMap.get(docId).getDocLength();
    }

    public double getGlobalIdf(String token) {
        int docCount = docIdMap.size();
        Set<Posting> uniqueDocs = new HashSet<>();
        uniqueDocs.addAll(invertedIndex.get(token));

        int matchedDocs = uniqueDocs.size();

        return Math.log((double) (docCount - matchedDocs + 0.5) / (matchedDocs + 0.5) + 1);
    }
}
