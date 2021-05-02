package com.example.htmlparser.domains.outputcondition;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class MixAlphabetAndNumberOutputConditionStrategy implements OutputConditionStrategy {
    private final StringBuilder result = new StringBuilder();

    @Override
    public OutputConditionResult apply(OutputConditionResult outputConditionResult) {
        String content = outputConditionResult.getContent();

        if (content == null) {
            return outputConditionResult;
        }

        mixAlphabetAndNumber(content);

        outputConditionResult.setContent(result.toString());

        initStringBuilder();

        return outputConditionResult;
    }

    private void mixAlphabetAndNumber(String content) {
        final String NOT_NUMBER_PATTERN = "[^-?0-9]+";
        final String NOT_ALPHABET_PATTERN = "[^a-zA-Z]";

        List<Character> onlyNumbers = content
            .replaceAll(NOT_NUMBER_PATTERN, "")
            .chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());

        List<Character> onlyAlphabet = content
            .replaceAll(NOT_ALPHABET_PATTERN, "")
            .chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());

        int index = 0;

        for (int i = 0; i < onlyAlphabet.size(); i++) {
            result.append(onlyAlphabet.get(i));

            if (i >= onlyNumbers.size()) continue;

            result.append(onlyNumbers.get(i));
            index++;
        }

        for (int i = index; i < onlyNumbers.size(); i++) {
            result.append(onlyNumbers.get(i));
        }
    }

    private void initStringBuilder() {
        result.setLength(0);
    }
}
