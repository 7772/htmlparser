package com.example.htmlparser.domains.print;

import java.util.List;
import java.util.stream.Collectors;

public class DividedResult {
    private final List<String> results;

    private final int outputUnit;

    public DividedResult(List<String> results, int outputUnit) {
        this.results = results;
        this.outputUnit = outputUnit;
    }

    public List<String> get() {
        return this.results;
    }

    public List<String> getQuotients() {
        return results
            .stream()
            .filter(result -> result.length() == outputUnit)
            .collect(Collectors.toList());
    }

    public String getRemainder() {
        String lastElement = results.get(results.size() - 1);

        return lastElement.length() != outputUnit ? lastElement : null;
    }
}
