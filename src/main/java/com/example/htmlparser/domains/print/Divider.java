package com.example.htmlparser.domains.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Divider {
    public DividedResult divide(String content, int outputUnit) {
        if (content == null) {
            return new DividedResult(new ArrayList<>());
        }

        final String SPLIT_PATTERN = "(?<=\\G.{" + outputUnit + "})";

        List<String> results = Arrays.asList(content.split(SPLIT_PATTERN));

        return new DividedResult(results);
    }
}
