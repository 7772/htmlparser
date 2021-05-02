package com.example.htmlparser.services;

import com.example.htmlparser.domains.parsing.Parser;
import com.example.htmlparser.domains.parsing.ParserType;
import com.example.htmlparser.domains.print.DividedResult;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ParseHTMLServicePrintTest {
    @Autowired
    private ParseHTMLService parseHTMLService;

    @MockBean
    private Parser parser;

    /**
     * url 을 parsing 한 결과가 mockParsedContent 일 때,
     * 몫은 AENRV 이며, 나머지는 존재하지 않는다.
     */
    @Test
    public void testResultOfParsing() {
        // Given
        String urlToParse = "https://naver.com";
        int outputUnit = 5;

        String mockParsedContent = "NAVER뉴스스탠드바로가기주제별캐스트바로가기타임스퀘어";
        when(parser.parse(anyString(), any()))
            .thenReturn(mockParsedContent);

        String expectedQuotient = "AENRV";

        // When
        DividedResult result = parseHTMLService.print(urlToParse, ParserType.ALL_TEXT, outputUnit);

        // Then
        assertThat(result.getQuotients())
            .isNotEmpty()
            .contains(expectedQuotient);

        assertThat(result.getRemainder()).isNull();
    }
}
