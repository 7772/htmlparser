package com.example.htmlparser.controllers;

import com.example.htmlparser.domains.parsing.ParserType;
import com.example.htmlparser.domains.print.DividedResult;
import com.example.htmlparser.services.ParseHTMLService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ParseHTMLController {
    private final ParseHTMLService parseHTMLService;

    @Autowired
    public ParseHTMLController(ParseHTMLService parseHTMLService) {
        this.parseHTMLService = parseHTMLService;
    }

    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public ModelAndView print(
        @RequestParam("urlToParse") String urlToParse,
        @RequestParam("parserType") ParserType parserType,
        @RequestParam("outputUnit") int outputUnit
    ) {
        DividedResult result = parseHTMLService.print(urlToParse, parserType, outputUnit);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("quotients", result.getQuotients());
        modelAndView.addObject("remainder", result.getRemainder());
        modelAndView.setViewName("main");

        return modelAndView;
    }
}
