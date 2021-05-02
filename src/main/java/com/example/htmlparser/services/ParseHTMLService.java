package com.example.htmlparser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htmlparser.domains.parsing.Parser;
import com.example.htmlparser.domains.parsing.ParserType;
import com.example.htmlparser.domains.print.DividedResult;
import com.example.htmlparser.domains.print.Printer;
import com.example.htmlparser.utils.JsoupParser;

@Service
public class ParseHTMLService {
    private final Printer printer;

    private final Parser parser;

    @Autowired
    public ParseHTMLService(Printer printer, Parser parser) {
        this.printer = printer;
        this.parser = parser;
    }

    public DividedResult print(String urlToParse, ParserType type, int outputUnits) {
        parser.parse(urlToParse, type);

        return null;
    }
}
