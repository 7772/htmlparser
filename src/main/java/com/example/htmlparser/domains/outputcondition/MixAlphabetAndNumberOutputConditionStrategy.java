package com.example.htmlparser.domains.outputcondition;

import java.util.List;
import java.util.stream.Collectors;

import com.example.htmlparser.domains.parsing.ParsedResult;

public class MixAlphabetAndNumberOutputConditionStrategy implements OutputConditionStrategy {
    private final StringBuilder result = new StringBuilder();

    @Override
    public ParsedResult apply(ParsedResult parsedResult) {
        String content = parsedResult.getContent();

        if (content == null) {
            return parsedResult;
        }

        mixAlphabetAndNumber(content);

        parsedResult.setContent(result.toString());

        return parsedResult;
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
}
