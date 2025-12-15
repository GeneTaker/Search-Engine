package org.example.search_engine_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import search_engine.Index;
import search_engine.Posting;
import search_engine.document.Document;

public class IndexTest {
    @Test
    @Tag("Index-1")
    @DisplayName("Test that documents can be properly added to the index")
    public void basicIndex() {
        Index index = new Index();
        
        Document doc1 = new Document("title title", "If your name begins with a H, this is for you!");
        Document doc2 = new Document("title title", "WHen you realise you are the one, then the one becomes you");

        int docId1 = index.addDocument(doc1);
        int docId2 = index.addDocument(doc2);

        assertEquals(index.getDocument(docId1), doc1);
        assertEquals(index.getDocument(docId2), doc2);

        List<Document> docs = index.getDocuments();
        TestUtils.assertListAreEqualIgnoringOrder(docs, Arrays.asList(doc1, doc2));
    }

    @Test
    @Tag("Index-2")
    @DisplayName("Test that created postings are properly added")
    public void postings() {
        Index index = new Index();

        int id1 = index.addDocument(new Document("title very, when", "If your name begins with a H, this is for you!"));
        int id2 = index.addDocument(new Document("dumb title", "WHen you realise you are the one, then the one becomes you"));

        TestUtils.assertListAreEqualIgnoringOrder(index.getPostings("title"), Arrays.asList(new Posting(id1, 1), new Posting(id2, 1)));
        TestUtils.assertListAreEqualIgnoringOrder(index.getPostings("when"), Arrays.asList(new Posting(id1, 1), new Posting(id2, 1)));
        assertTrue(index.getPostings("").size() == 0);
        assertTrue(index.getPostings("imposter").size() == 0);

        TestUtils.assertListAreEqualIgnoringOrder(index.getPostings("you"), Arrays.asList(new Posting(id1, 1), new Posting(id2, 3)));
    }
}
