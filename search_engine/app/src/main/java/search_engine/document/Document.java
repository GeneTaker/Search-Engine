package search_engine.document;

import java.util.List;

public class Document {
    private int docId;
    private String title;
    private String content;

    public Document(int id, String title, String content) {
        this.docId = id;
        this.title = title;
        this.content = content;
    }

    public int getDocId() {
        return docId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<String> tokeniseTitle() {
        return null;
    }

    public List<String> tokeniseContent() {
        return null;
    }
}
