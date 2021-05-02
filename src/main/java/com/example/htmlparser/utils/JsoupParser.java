package com.example.htmlparser.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.springframework.stereotype.Component;

@Component
public class JsoupParser {
    public Document parse(String url) throws IOException {
        return Jsoup.connect(url).get();
    }
}
