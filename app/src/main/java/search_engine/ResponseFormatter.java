package search_engine;

import java.util.List;

public class ResponseFormatter {
    public String formatText(List<SearchResult> results) {
        String str = "";
        for (int i = 0; i < results.size(); i++) {
            str += "Search Result " + i + ": " + results.get(i).toString() + "\n\n";
        }
        return str;
    }
}
