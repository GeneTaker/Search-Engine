package org.example.search_engine_tests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import search_engine.EngineController;

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
}
