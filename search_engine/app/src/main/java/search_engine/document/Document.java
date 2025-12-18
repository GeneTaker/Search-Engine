package search_engine.document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import search_engine.Tokeniser;

public class Document {
    private String title;
    private String content;
    private int length;
    private static int allLengths = 0;
    private static int allSize = 0;

    public Document(String title, String content) {
        this.title = title;
        this.content = content;
        this.length = computeDocLength(content) + computeDocLength(title);
        Document.allLengths += length;
        allSize++;
    }

    public static double getAllLengthAverage() {
        return 1.0 * allLengths / allSize;
    }

    public static double getAllSize() {
        return allSize;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    
    /**
     * tokenises the title of the document
     * @return list of token strings
     */
    public List<String> tokeniseTitle() {
        return Tokeniser.tokenise(title);
    }

    /**
     * tokenises the content of the document
     * @return list of token strings
     */
    public List<String> tokeniseContent() {
        return Tokeniser.tokenise(content);
    }

    /**
     * Converts a path to a document
     * @return a document object
     */
    public static Document createDocument(Path file) {
        String content = "";
        try {
            content = Files.readString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String title = file.getFileName().toString();
        
        int index = title.lastIndexOf(".");

        String stripped = title.substring(0, index);
        stripped = separateWords(stripped);
        
        return new Document(stripped, content);
    }

    /**
     * Converts snake case file names to text
     * @param words a snake case name
     * @return a string that has the first letter of each word capitalised
     */
    private static String separateWords(String words) {
        String resultant = "";
        String[] split = words.split("_");

        int n = split.length;
        for (int i = 0; i < n; i++) {
            if (!split[i].isEmpty()) {
                resultant += split[i].substring(0, 1).toUpperCase() + split[i].substring(1) + " ";
            }
        } 
        return resultant.strip();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;

        if (getClass() != obj.getClass()) return false;

        Document other = (Document) obj;

        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        if (content == null) {
            if (other.content != null) {
                return false;
            }
        } else if (!content.equals(other.content)) {
            return false;
        }
        return true;
    }

    private static int computeDocLength(String s) {
        if (s == null || s.isEmpty()) return 0;

        return s.split("\\s+").length;
    }

    public int getDocLength() {
        return length;
    }

    @Override
    public String toString() {
        String border = "-".repeat(title.length() + 8);
        return String.format("%s%n===%s ===%n%s%n%n%s%n", border, title, border, content);
    }

}
