package com.example.htmlparser.domains.parsing;

public enum ParserType {
    ALL_TEXT("ALL_TEXT"),
    EXCEPT_FOR_HTML("EXCEPT_FOR_HTML");

    private final String type;

    ParserType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
