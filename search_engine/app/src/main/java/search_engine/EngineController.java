package search_engine;

import java.util.List;

import search_engine.document.DocumentLoader;

public class EngineController {
    
    private SearchEngine searchEngine = new SearchEngine();
    private DocumentLoader loader;
    
    public EngineController(DocumentLoader loader) {
        this.loader = loader;
    }

    /**
     * Adds a document to the search engine
     * @param path
     */
    public void addDocument(String path) {
        
    }

    /**
     * Searches a text input in the search engine, and returns all results
     * @param query the string text input
     * @return a list of search results for the query
     */
    public List<SearchResult> search(String query) {
        return null;
    }

}
