package com.example.htmlparser.domains.outputcondition;

import org.springframework.stereotype.Component;

@Component
public class AlphanumericOutputConditionStrategy implements OutputConditionStrategy {
    @Override
    public OutputConditionResult apply(OutputConditionResult outputConditionResult) {
        String content = outputConditionResult.getContent();

        if (content == null) {
            return outputConditionResult;
        }

        outputConditionResult.setContent(
            content.replaceAll("[^A-Za-z0-9]", "")
        );

        return outputConditionResult;
    }
}
