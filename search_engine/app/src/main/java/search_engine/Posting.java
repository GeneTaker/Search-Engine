package search_engine;

public class Posting {
    private int termFrequency;
    private String term;
    private int docId;

    public Posting(int docId, String term, int termFrequency) {
        this.docId = docId;
        this.term = term;
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
    public String toString() {
        return "Posting [termFrequency=" + termFrequency + ", term=" + term + ", docId=" + docId + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + docId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        
        if (obj == null)
            return false;
        
        if (getClass() != obj.getClass())
            return false;

        Posting other = (Posting) obj;  
        if (docId != other.docId)
            return false;
        return true;
    }
    

}