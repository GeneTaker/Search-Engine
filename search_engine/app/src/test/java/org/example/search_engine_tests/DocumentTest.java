package org.example.search_engine_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import search_engine.document.Document;

public class DocumentTest {
    @Test
    @Tag("Document-1")
    @DisplayName("Basic document test")
    public void testDocument() {
        Document doc = new Document("four", "quick, Fox");
        assertEquals(doc.getContent(), "quick, Fox");
        assertEquals(doc.getTitle(), "four");
    }
}
