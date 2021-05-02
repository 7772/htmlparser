package com.example.htmlparser.domains.print;

import java.util.List;
import java.util.stream.Collectors;

public class DividedResult {
    private final List<String> results;

    public DividedResult(List<String> results) {
        this.results = results;
    }

    public List<String> get() {
        return this.results;
    }

    public List<String> getQuotients(int outputUnit) {
        return results
            .stream()
            .filter(result -> result.length() == outputUnit)
            .collect(Collectors.toList());
    }

    public String getRemainder(int outputUnit) {
        String lastElement = results.get(results.size() - 1);

        return lastElement.length() != outputUnit ? lastElement : null;
    }
}
