package com.example.htmlparser.domains.parsing;

import java.io.IOException;

import com.example.htmlparser.utils.JsoupParser;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Parser {
    private final JsoupParser jsoupParser;

    @Autowired
    public Parser(JsoupParser jsoupParser) {
        this.jsoupParser = jsoupParser;
    }

    public String parse(String urlToParse, ParserType type) {
        String result = "";

        try {
            if (type == ParserType.ALL_TEXT) {
                result = parseByAllText(urlToParse);
            }
            else if (type == ParserType.EXCEPT_FOR_HTML) {
                result = parseByExceptForHTML(urlToParse);
            }
            else {
                // Nothing entry.
            }
        }
        catch (Exception exception) {
            throw new RuntimeException("URL 분석에 실패했습니다. 다시 시도해 주세요.");
        } finally {
            if (result != null) {
                result = result.replaceAll("(\\r\\n|\\r|\\n|\\n\\r|\\p{Z}|\\t)","");
            }
        }

        return result;
    }

    private String parseByAllText(String urlToParse) throws IOException {
        Document document = jsoupParser.parse(urlToParse);

        return document.html().replaceAll("\\/|\\<|\\>", "");
    }

    private String parseByExceptForHTML(String urlToParse) throws IOException {
        Document document = jsoupParser.parse(urlToParse);

        return document.text();
    }
}
