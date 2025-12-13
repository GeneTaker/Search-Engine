package search_engine;

public class SearchResult implements Comparable<SearchResult> {
    private String docId;
    private int relevance;
    private String title;
    private String snippet;

    public SearchResult(String docId, int relevance, String title, String snippet) {
        this.docId = docId;
        this.relevance = relevance;
        this.title = title;
        this.snippet = snippet;
    }

    public String getDocId() {
        return docId;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }

    @Override
    public int compareTo(SearchResult o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }

    
}
