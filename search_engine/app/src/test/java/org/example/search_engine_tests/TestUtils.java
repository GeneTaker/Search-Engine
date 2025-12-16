package org.example.search_engine_tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import search_engine.SearchEngine;
import search_engine.document.Document;

public class TestUtils {
    public static void assertListAreEqualIgnoringOrder(List<?> a, List<?> b) {
        assertTrue(a.size() == b.size() && a.containsAll(b) && b.containsAll(a));
    }

    public static void assertListAreNotEqualIgnoringOrder(List<?> a, List<?> b) {
        assertFalse(a.size() == b.size() && a.containsAll(b) && b.containsAll(a));
    }

    public static boolean checkToken(List<String> tokens) {
        for (String t : tokens) {
            Pattern pattern = Pattern.compile("\\s+");
            Matcher matcher = pattern.matcher(t);

            if (!t.equals(t.toLowerCase()) || matcher.find()) return false;
        }

        return true;
    }

    public static List<Document> searchToDocuments(SearchEngine engine, String query) {
        return engine.search(query).stream()
            .map(s -> engine.getDocument(s.getDocId()))
            .collect(Collectors.toList());
    }
}
