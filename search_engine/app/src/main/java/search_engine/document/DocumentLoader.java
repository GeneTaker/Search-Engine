package search_engine.document;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DocumentLoader {
    private List<String> fileTypes;
    private Path directory;
    
    public DocumentLoader(String directory, List<String> fileTypes) {
        this.directory = Paths.get(directory);
        this.fileTypes = fileTypes;
    }
    /**
     * 
     * @param fileName
     * @throws InvalidLoadException if file type is not supported
     * @return
     */
    public List<Document> loadDocument() {
        return null;
    }

    public boolean isValidFile(String fileName) {
        return false;
    }
}
