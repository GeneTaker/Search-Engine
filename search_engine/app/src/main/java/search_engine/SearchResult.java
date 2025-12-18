package search_engine;

public class SearchResult implements Comparable<SearchResult> {
    private int docId;
    private double relevance;
    private String title;

    public SearchResult(int docId, double relevance, String title) {
        this.docId = docId;
        this.relevance = relevance;
        this.title = title;
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

    
}
