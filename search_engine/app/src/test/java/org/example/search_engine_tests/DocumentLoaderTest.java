package org.example.search_engine_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import search_engine.document.Document;
import search_engine.document.DocumentLoader;
import search_engine.exceptions.InvalidLoadException;

public class DocumentLoaderTest {
    @Test
    @Tag("Loader-1")
    @DisplayName("Basic functionality test for document loader")
    public void testDocumentLoader() {
        String testFile = "search_engine/app/src/test/resources/paragraph.txt";
        DocumentLoader loader = new DocumentLoader(testFile, Arrays.asList("txt", "md"));

        List<Document> docs = loader.loadDocument();
        
        assertTrue(docs.size() == 3);
        assertTrue(docs.get(0).getTitle().equals("paragraph"));
        assertTrue(docs.get(0).getContent().equals("The sun dipped below the horizon, casting a golden" +
                                                    " hue across the vast ocean. Waves crashed against the jagged" +
                                                    " rocks, their rhythmic sound echoing in the cool evening air." +
                                                    "A gentle breeze rustled through the tall grass that lined the" +
                                                    " cliffside, as birds soared high above, silhouetted against the " +
                                                    "fading light. In the distance, a lone sailboat drifted slowly, its " + 
                                                    "sails billowing in the wind, while the sky transitioned from " +
                                                    "shades of orange to deep purple. It was a moment of " +
                                                    "tranquility, one that made the world seem both immense and small " +
                                                    "at once."));

    }

    @Test
    @Tag("Loader-2")
    @DisplayName("Load a directory of files")
    public void testDirectoryLoad() {
        String testDirectory = "search_engine/app/src/test/resources/txts";

        DocumentLoader loader = new DocumentLoader(testDirectory, Arrays.asList("txt", "md"));

        List<Document> docs = loader.loadDocument();

        assertEquals(docs.size(), 3);

        List<String> titles = docs.stream().map(a -> a.getTitle()).collect(Collectors.toList());
        List<String> contents = docs.stream().map(a -> a.getContent()).collect(Collectors.toList());

        TestUtils.assertListAreEqualIgnoringOrder(titles, Arrays.asList("Crazy Frog", "File"));
        TestUtils.assertListAreEqualIgnoringOrder(contents, Arrays.asList("funkytown apples", "dunkytown rail"));       

    }

    @Test
    @Tag("Loader-3")
    @DisplayName("Load invalid filetype")
    public void testInvalid() {
        String invalid = "search_engine/app/src/test/resources/paragraph.md";
        DocumentLoader loader = new DocumentLoader(invalid, Arrays.asList("md"));
        
        assertThrows(InvalidLoadException.class, () -> {
            loader.loadDocument();
        });
    }
}