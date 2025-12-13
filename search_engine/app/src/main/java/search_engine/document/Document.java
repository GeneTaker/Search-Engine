package search_engine.document;

public class Document {
    private String docId;
    private String title;
    private String content;

    public Document(String id, String title, String content) {
        this.docId = id;
        this.title = title;
        this.content = content;
    }

    public String getDocId() {
        return docId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}
