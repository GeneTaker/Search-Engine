package search_engine.document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import search_engine.exceptions.InvalidLoadException;

public class DocumentLoader {
    private Set<String> fileTypes = new HashSet<>();
    private Path directory;

    public DocumentLoader(String directory, List<String> fileTypes) {
        this.directory = Paths.get(directory);       
        for (String s : fileTypes) this.fileTypes.add(s);
    }

    // public void switchDirectory(String directory) {
    //     this.directory = Paths.get(directory);
    // }

    /**
     * Lazily traverses the file path to return a list of documents, one for each file traversed
     * @throws InvalidLoadException if we fail to read from the directory
     * @return a list of documents inside the current file path
     */
    public List<Document> loadDocument() throws InvalidLoadException {
        List<Document> docs = new ArrayList<>();
        try {
            if (Files.isRegularFile(directory) && isValidFile(directory.getFileName().toString())) {
                docs.add(Document.createDocument(directory));
            } else if (Files.isDirectory(directory)) {
                try (Stream<Path> paths = Files.walk(directory)) {
                    docs = findFiles(paths);
                }
            } else {
                throw new InvalidLoadException("Invalid path provided, " + directory);
            }
        } catch (IOException i) {
            i.printStackTrace();
            throw new InvalidLoadException("Failed to read from directory" + directory);
        }

        return docs;
    }

    /**
     * Converts a stream of file paths to documents
     * @param paths A stream of path objects
     * @return A list of documents
     */
    private List<Document> findFiles(Stream<Path> paths) {
        List<Document> results = new ArrayList<>();
        paths.filter(Files::isRegularFile)
            .forEach(f -> {
                if (isValidFile(f.getFileName().toString())) {
                    results.add(Document.createDocument(f));
                }
            });
        return results;
    }

    /**
     * Returns true or false depending on whether the filetype is supported
     * @param fileName The name of a file
     * @return a boolean depending on whether it is supported
     */
    public boolean isValidFile(String fileName) {
        int index = fileName.lastIndexOf(".");

        if (index == -1 || index == fileName.length() - 1) return false;

        String s = fileName.substring(index + 1);
        if (fileTypes.contains(s.toLowerCase())) return true;

        return false;
    }
}
