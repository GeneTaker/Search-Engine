package search_engine;

public class Posting {
    private int termFrequency;
    private int docId;

    public Posting(int docId, int termFrequency) {
        this.docId = docId;
        this.termFrequency = termFrequency;
    }

    /**
     * @return the id to the document that the posting refers to
     */
    public int getId() {
        return docId;         
    }

    /**
     * @return the number of times a document has been searched
     */
    public int getFrequency() {
        return termFrequency;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        Posting other = (Posting) obj;

        if (termFrequency != other.termFrequency) {
            return false;
        }
        if (docId != other.docId) {
            return false;
        }

        return true;
    }
}