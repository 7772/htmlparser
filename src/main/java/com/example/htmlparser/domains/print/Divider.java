package com.example.htmlparser.domains.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Divider {
    private final String content;

    private final int outputUnit;

    public Divider(String content, int outputUnit) {
        this.content = content;
        this.outputUnit = outputUnit;
    }

    public DividedResult divide() {
        if (content == null) {
            return new DividedResult(new ArrayList<>(), 0);
        }

        final String SPLIT_PATTERN = "(?<=\\G.{" + outputUnit + "})";

        List<String> results = Arrays.asList(content.split(SPLIT_PATTERN));

        return new DividedResult(results, outputUnit);
    }
}
