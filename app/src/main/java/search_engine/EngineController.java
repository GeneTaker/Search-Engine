package search_engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import search_engine.document.Document;
import search_engine.document.DocumentLoader;
import search_engine.exceptions.InvalidLoadException;

public class EngineController {
    private SearchEngine searchEngine = new SearchEngine();
    private static List<String> supportedTypes = Arrays.asList("txt", "md");
    private List<SearchResult> currentlyPresented = new ArrayList<>();

    /**
     * Adds a document to the search engine
     * @param directory the string directory path of the directory that documents should be loaded from
     */
    public void loadDocuments(String directory) throws InvalidLoadException {
        DocumentLoader loader = new DocumentLoader(directory, supportedTypes);
        List<Document> docs = new ArrayList<>();

        try {
            docs = loader.loadDocument();
        } catch (InvalidLoadException i) {
            throw new InvalidLoadException(directory);
        }

        searchEngine.addDocuments(docs);
    }

    /**
     * Searches a text input in the search engine, and returns all results
     * @param query the string text input
     * @return a list of search results for the query
     */
    public List<SearchResult> search(String query) {
        currentlyPresented = searchEngine.search(query);
        return currentlyPresented;
    }

    /**
     * Opens a document after a search is performed, returns null if there are 
     * no current search results
     * @precondition: index is an existing index in the currentlyPresented list, 0-index
     * @param index the index of the search result that should be opened
     * @return the string output of the retrieved document
     */
    public String openDocument(int index) {
        if (index < 0 || index > currentlyPresented.size() - 1) return null;

        return searchEngine.openDocument(currentlyPresented.get(index));
    }
}
