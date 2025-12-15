package org.example.search_engine_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import search_engine.Tokeniser;
import search_engine.document.Document;

public class TokenTest {
    @Test
    @Tag("Token-1")
    @DisplayName("Test that basic text can be properly tokenised")
    public void testBasicTokenise() {
        List<String> tokens = Tokeniser.tokenise("Wassuh   daWg, Yall, Got?, dawg, Airfryers???");
        TestUtils.assertListAreEqualIgnoringOrder(tokens, Arrays.asList("wassuh", "dawg", "dawg", "yall", "got", "airfryers"));
    }

    @Test
    @Tag("Token-2")
    @DisplayName("Test that tokens contain all relevant tokens and are lowercase")
    public void testTokenise() {
        Document doc = new Document("How to Manage Your Anger", "The Quick Brown Fox Jumped Over the Grey Fence");
        
        List<String> titleTokens = doc.tokeniseTitle();
        List<String> contentTokens = doc.tokeniseContent();
        
        assertFalse(titleTokens.equals(Arrays.asList()));
        assertTrue(TestUtils.checkToken(titleTokens));
        assertTrue(TestUtils.checkToken(contentTokens));
    }

    @Test
    @Tag("Token-3")
    @DisplayName("Test that tokens can contain special character phrases")
    public void testSpecials() {
        Document doc = new Document("      ", "New *blanket = $5.99");
        
        List<String> titleTokens = doc.tokeniseTitle();
        List<String> contentTokens = doc.tokeniseContent();
        
        assertEquals(titleTokens, Arrays.asList());
        assertTrue(TestUtils.checkToken(contentTokens));
    }

}
