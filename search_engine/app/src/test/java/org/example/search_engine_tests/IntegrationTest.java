package org.example.search_engine_tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import search_engine.EngineController;
import search_engine.SearchResult;

public class IntegrationTest {
    @Test
    @Tag("Integration-1")
    @DisplayName("Ensure controller works")
    public void testController() {
        Path path;
        try {
            path = Paths.get(
                Objects.requireNonNull(
                    getClass().getClassLoader().getResource("search.txt")
                ).toURI()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        EngineController controller = new EngineController();
        assertDoesNotThrow(() -> {
            controller.loadDocuments(path.toString());
        });

        assertEquals(controller.search("river engine").get(0).getTitle(), "Search");
        assertDoesNotThrow(() -> {controller.openDocument(0);});
        assertTrue(controller.openDocument(1) == null);
        assertTrue(controller.openDocument(-1) == null);
        
    }

    @Test
    @Tag("Integration-2")
    @DisplayName("search logical operators and multiple results")
    public void testControllerOpenMultiple() {
        Path path;
        try {
            path = Paths.get(
                Objects.requireNonNull(
                    getClass().getClassLoader().getResource("documents")
                ).toURI()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        EngineController controller = new EngineController();
        assertDoesNotThrow(() -> {
            controller.loadDocuments(path.toString());
        });

        List<SearchResult> results = controller.search("cool OR lazy AND NOT banana ");
        assertEquals(results.get(0).getTitle(), "Fairies Are Cool");
        assertEquals(results.get(1).getTitle(), "Banana");
        assertEquals(results.get(2).getTitle(), "How To Train Your Dragon");
        assertEquals(results.get(3).getTitle(), "Happy");

        assertTrue(controller.openDocument(0).contains("Fairies Are Cool"));
        assertTrue(controller.openDocument(1).contains("Banana"));
        assertTrue(controller.openDocument(2).contains("How To Train Your Dragon"));
        assertTrue(controller.openDocument(3).contains("Happy"));
        

    }
}
