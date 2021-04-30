package com.example.htmlparser.domains.outputcondition;

import com.example.htmlparser.domains.parsing.ParsedResult;

public interface OutputConditionStrategy {
    ParsedResult apply(ParsedResult parsedResult);
}
