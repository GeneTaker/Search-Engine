package org.example.search_engine_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import search_engine.SearchEngine;
import search_engine.SearchResult;
import search_engine.document.Document;

public class SearchEngineTest {
    SearchEngine engine;
    List<Document> documents;

    @BeforeEach
    public void setup() {
        engine = new SearchEngine();
        documents = Arrays.asList(new Document("banana", "bread is very cool, not your bread though"), 
            new Document("happy", "happy discord time, you know the drift and the way to your heart"),
            new Document("how to train your dragon", "The quick brown fox jumped over the lazy and stupid rabbit, or maybe it could not"));
        engine.addDocuments(documents);
    }

    @Test
    @Tag("SearchEngine-1")
    @DisplayName("Test that documents can be successfully added")
    public void testDocuments() {
        TestUtils.assertListAreEqualIgnoringOrder(engine.getDocuments(), documents);
    }

    @Test
    @Tag("SearchEngine-2")
    @DisplayName("Test irrelevant queries give no results")
    public void testInvalidSearchDocuments() {
        List<SearchResult> results = engine.search("freedom");
        assertTrue(results.size() == 0);
    }

    @Test
    @Tag("SearchEngine-3")
    @DisplayName("Test that documents can be successfully searched and retrieved")
    public void testSearchDocuments() {
        TestUtils.assertListAreEqualIgnoringOrder(TestUtils.searchToDocuments(engine, "your"), documents);
    }

    @Test
    @Tag("SearchEngine-4")
    @DisplayName("Test that documents can be successfully searched and retrieved with a two-word")
    public void testSearchDocumentsTwo() {
        assertEquals(TestUtils.searchToDocuments(engine, "your happy"), Arrays.asList(new Document("happy", "happy discord time, you know the drift and the way to your heart")));
    }

    @Test
    @Tag("SearchEngine-5")
    @DisplayName("Test that documents can be successfully searched and retrieved with a multi-word")
    public void testSearchDocumentsMulti() {
        List<Document> results = TestUtils.searchToDocuments(engine, "your dragon train quick");
        assertEquals(results, Arrays.asList(new Document("how to train your dragon", "The quick brown fox jumped over the lazy and stupid rabbit, or maybe it could not")));
    }

    @Test
    @Tag("SearchEngine-6")
    @DisplayName("Test that logical operators can be properly applied to searches: OR")
    public void testOr() {
        List<Document> results = TestUtils.searchToDocuments(engine, "your OR dragon OR or");
        TestUtils.assertListAreEqualIgnoringOrder(results, documents);

        List<Document> selectiveResults = TestUtils.searchToDocuments(engine, "discord OR happy OR banana");
        TestUtils.assertListAreEqualIgnoringOrder(Arrays.asList(new Document("banana", "bread is very cool, not your bread though"), 
            new Document("happy", "happy discord time, you know the drift and the way to your heart")), 
            selectiveResults);
    }

    @Test
    @Tag("SearchEngine-7")
    @DisplayName("Test that logical operators can be properly applied to searches: AND")
    public void testAnd() {
        List<Document> results = TestUtils.searchToDocuments(engine, "happy AND your AND table");
        assertTrue(results.size() == 0);

        List<Document> selectiveResults = TestUtils.searchToDocuments(engine, "happy AND discord AND drift AND the AND way");
        assertEquals(Arrays.asList(new Document("happy", "happy discord time, you know the drift and the way to your heart")),
            selectiveResults);
    }

    @Test
    @Tag("SearchEngine-8")
    @DisplayName("Test that logical operators can be properly applied to searches: NOT")
    public void testNot() {
        List<Document> complement = TestUtils.searchToDocuments(engine, "NOT happy");
        TestUtils.assertListAreEqualIgnoringOrder(complement, Arrays.asList(
            new Document("banana", "bread is very cool, not your bread though"),
            new Document("how to train your dragon", "The quick brown fox jumped over the lazy and stupid rabbit, or maybe it could not")
        ));

        List<Document> setDifference = TestUtils.searchToDocuments(engine, "your NOT happy");
        TestUtils.assertListAreEqualIgnoringOrder(setDifference, Arrays.asList(new Document("banana", "bread is very cool, not your bread though"),
            new Document("how to train your dragon", "The quick brown fox jumped over the lazy and stupid rabbit, or maybe it could not")));
    }

    @Test
    @Tag("SearchEngine-9")
    @DisplayName("Test logical operators altogether")
    public void testAll() {
        List<Document> results = TestUtils.searchToDocuments(engine, "your AND NOT happy");
        TestUtils.assertListAreEqualIgnoringOrder(results, Arrays.asList(Arrays.asList(new Document("banana", "bread is very cool, not your bread though"),
            new Document("how to train your dragon", "The quick brown fox jumped over the lazy and stupid rabbit, or maybe it could not"))));
    
        List<Document> complex = TestUtils.searchToDocuments(engine, "happy AND NOT today OR jumped fox OR NOT your");
        TestUtils.assertListAreEqualIgnoringOrder(Arrays.asList(new Document("happy", "happy discord time, you know the drift and the way to your heart"),
            new Document("how to train your dragon", "The quick brown fox jumped over the lazy and stupid rabbit, or maybe it could not")), complex);
    }
    
}
