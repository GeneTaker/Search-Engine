package search_engine;

import java.util.List;

import search_engine.document.Document;

public class SearchEngine {
    private Index index = new Index();
    private ResponseFormatter formatter = new ResponseFormatter();

    /**
     * Adds documents to the index
     * @param documents: the list of documents to be added
     */
    public void addDocuments(List<Document> documents) {

    }

    /**
     * Retrieves a list of search results
     * @param query the search term used
     * @return a list of search results
     */
    public List<SearchResult> search(String query) {
        return null;
    }

    /**
     * @return All documents from the index
     */
    public List<Document> getDocuments() {
        return index.getDocuments();
    }

    
}
