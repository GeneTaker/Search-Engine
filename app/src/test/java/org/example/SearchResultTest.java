package org.example;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import search_engine.SearchResult;

public class SearchResultTest {
    @Test
    @Tag("SearchEngine-1")
    @DisplayName("Test the precedence of search results")
    public void testRelevance() {
        SearchResult result1 = new SearchResult(1, 1, "title");
        SearchResult result2 = new SearchResult(2, 5, "funky funky");

        assertFalse(result1.compareTo(result2) > 0);
    }
}
