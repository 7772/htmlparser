package com.example.htmlparser.domains.outputcondition;

import com.example.htmlparser.domains.parsing.ParsedResult;

public class AlphanumericOutputConditionStrategy implements OutputConditionStrategy {
    @Override
    public ParsedResult apply(ParsedResult parsedResult) {
        String content = parsedResult.getContent();
        parsedResult.setContent(
            content.replaceAll("[^A-Za-z0-9]", "")
        );

        return parsedResult;
    }
}
