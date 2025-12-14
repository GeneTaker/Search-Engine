package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import search_engine.document.Document;

public class DocumentTest {
    @Test
    @Tag("Document-1")
    @DisplayName("Basic document test")
    public void testDocument() {
        Document doc = new Document(1, "four", "quick, Fox");
        assertTrue(doc.getDocId() == 1);
        assertEquals(doc.getContent(), "quick, Fox");
        assertEquals(doc.getTitle(), "four");
    }
}
