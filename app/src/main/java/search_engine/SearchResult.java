package search_engine;

import java.time.LocalDateTime;

public class SearchResult implements Comparable<SearchResult> {
    private int docId;
    private double relevance;
    private String title;
    private LocalDateTime uploaded;

    public SearchResult(int docId, double relevance, String title) {
        this.docId = docId;
        this.relevance = relevance;
        this.title = title;
        uploaded = LocalDateTime.now();
    }

    public int getDocId() {
        return docId;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int compareTo(SearchResult o) {
        return Double.compare(relevance, o.relevance);
    }

    @Override
    public String toString() {
        return "[ " + title + " ]\n" + uploaded;
    }    
}
