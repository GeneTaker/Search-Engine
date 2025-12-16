package search_engine;

public class SearchResult implements Comparable<SearchResult> {
    private int docId;
    private int relevance;
    private String title;

    public SearchResult(int docId, int relevance, String title) {
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    
}
