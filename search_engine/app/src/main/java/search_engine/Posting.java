package search_engine;

public class Posting {
    private int searchFrequency;
    private int docId;

    public Posting(int docId, int searchFrequency) {
        this.docId = docId;
        this.searchFrequency = searchFrequency;
    }

    /**
     * @return the id to the document that the posting refers to
     */
    public int retrieveId() {
        return docId;         
    }

    /**
     * @return the number of times a document has been searched
     */
    public int getFrequency() {
        return searchFrequency;
    }

}