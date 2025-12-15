package search_engine;

import search_engine.document.Document;

public class Posting {
    private int searchFrequency;
    private Document doc;

    public Posting(Document doc) {
        this.doc = doc;
        searchFrequency = 0;
    }

    /**
     * @return the document information from the posting
     */
    public Document showDocument() {
        return null;        
    }

    /**
     * @return the number of times a document has been searched
     */
    public int getFrequency() {
        return 0;
    }

}