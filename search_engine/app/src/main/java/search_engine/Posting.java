package search_engine;

import search_engine.document.Document;

public class Posting {
    private static int count;
    private int searchFrequency;
    private Document doc;

    public Posting(Document doc) {
        this.doc = doc;
        searchFrequency = 0;
        count++;
    }

    public Document showDocument() {
        return null;        
    }

}