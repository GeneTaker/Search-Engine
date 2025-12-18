package org.example.search_engine_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import search_engine.SearchEngine;
import search_engine.document.Document;

public class RankingTest {
    SearchEngine engine;
    List<Document> documents;

    @BeforeEach
    public void setup() {
        engine = new SearchEngine();
        documents = Arrays.asList(new Document("banana", "bread is very cool, not your bread though"), 
            new Document("happy", "happy discord time, you know the drift and the way to your heart, but these days, I am a little too lazy"),
            new Document("how to train your dragon", "The quick brown fox jumped over the lazy and stupid rabbit, or maybe it could not"),
            new Document("fairies are cool", "If you know, you know, because the essense of the search engine is overly cool, but also not cool"));
        engine.addDocuments(documents);
    }

    @Test
    @Tag("Ranking-1")
    @DisplayName("Basic ranking test")
    public void rankTest() {
        List<Document> docs = TestUtils.searchToDocuments(engine, "cool");
        
        assertEquals(docs.size(), 2);
        assertEquals("fairies are cool", docs.get(0).getTitle());
        assertEquals("banana", docs.get(1).getTitle());
    }

    @Test
    @Tag("Ranking-2")
    @DisplayName("Duplicate search terms")
    public void dupeTerms() {
        List<Document> docs = TestUtils.searchToDocuments(engine, "cool cool cool");
        
        assertEquals(2, docs.size());
        assertEquals("fairies are cool", docs.get(0).getTitle());
    }

    @Test
    @Tag("Ranking-4")
    @DisplayName("Test OR query")
    public void testOr() {
        List<Document> docs = TestUtils.searchToDocuments(engine, "cool OR lazy AND NOT banana");

        assertEquals(4, docs.size());
        assertEquals("fairies are cool", docs.get(0).getTitle());
        assertEquals("banana", docs.get(1).getTitle());
        assertEquals("how to train your dragon", docs.get(2).getTitle());
    }



}
