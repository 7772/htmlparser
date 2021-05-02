package com.example.htmlparser.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.htmlparser.domains.print.DividedResult;
import com.example.htmlparser.domains.print.Printer;

@Service
public class ParseHTMLService {
    private final Printer printer;

    @Autowired
    public ParseHTMLService(Printer printer) {
        this.printer = printer;
    }

    public DividedResult print() {
        return null;
    }
}
