package com.example.htmlparser.domains.outputcondition;

import java.util.List;
import java.util.stream.Collectors;

import com.example.htmlparser.domains.parsing.ParsedResult;

public class AscOutputConditionStrategy implements OutputConditionStrategy {
    private final StringBuilder result = new StringBuilder();

    @Override
    public ParsedResult apply(ParsedResult parsedResult) {
        String content = parsedResult.getContent();

        if (content == null) {
            return parsedResult;
        }

        sortNumber(content);
        sortAsc(content);

        parsedResult.setContent(result.toString());

        return parsedResult;
    }

    private void sortNumber(String content) {
        final String NUMBER_PATTERN = "[^-?0-9]+";

        List<Character> onlyNumbers = content
            .replaceAll(NUMBER_PATTERN, "")
            .chars()
            .sorted()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());

        for (int i = 0; i < onlyNumbers.size(); i++) {
            result.append(onlyNumbers.get(i));
        }
    }

    private void sortAsc(String content) {
        List<Character> onlyUppercases = content
            .chars()
            .sorted()
            .filter(c -> Character.isUpperCase((char) c))
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());
        List<Character> onlyLowercases = content
            .chars()
            .sorted()
            .filter(c -> Character.isLowerCase((char) c))
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());

        int index = 0;

        for (int i = 0; i < onlyUppercases.size(); i++) {
            for (int j = 0; j < onlyLowercases.size(); j++) {

                if (index >= onlyLowercases.size()) break;

                char c = onlyLowercases.get(index);
                if ((int) onlyUppercases.get(i) > toUppercase(c)) {
                    result.append(c);
                    index++;
                }
            }
            result.append(onlyUppercases.get(i));
        }

        for (int i = index; i < onlyLowercases.size(); i++) {
            result.append(onlyLowercases.get(i));
        }
    }

    private int toUppercase(char c) {
        return ((int) c - 32);
    }
}
