package com.example.htmlparser.domains.parsing;

import java.io.IOException;

import com.example.htmlparser.utils.JsoupParser;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ParserTest {
    @MockBean
    private JsoupParser jsoupParser;

    @Autowired
    private Parser parser;

    private String url;
    private Document document;

    @BeforeEach
    public void beforeEach() {
        url = "https://naver.com";
        document = Mockito.mock(Document.class);
    }

    @Test
    public void testWhenWrongURL() throws IOException {
        // Given
        String url = "wrong-url";

        when(jsoupParser.parse(anyString()))
            .thenThrow(new IOException());

        // When & Then
        assertThatThrownBy(
            () -> parser.parse(url, ParserType.ALL_TEXT)
        ).hasMessageContaining("URL 분석에 실패했습니다. 다시 시도해 주세요.");
    }

    @Test
    public void testAllText() throws IOException {
        // Given
        when(jsoupParser.parse(anyString()))
            .thenReturn(document);

        String mockDocumentText = "NAVER 뉴스스탠드 바로가기 주제별캐스트 바로가기 타임스퀘어";

        when(document.text())
            .thenReturn(mockDocumentText);

        String expectedResult = "NAVER뉴스스탠드바로가기주제별캐스트바로가기타임스퀘어";

        // When
        String result = parser.parse(url, ParserType.ALL_TEXT);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testExceptForHTML() throws IOException {
        // Given
        when(jsoupParser.parse(anyString()))
            .thenReturn(document);

        String mockDocumentHTML = "!doctype html\n"
                + "html lang=\"ko\" data-dark=\"false\" \n"
                + " head \n"
                + "  meta charset=\"utf-8\" \n"
                + "  titleNAVERtitle \n";

        when(document.html())
            .thenReturn(mockDocumentHTML);

        String expectedResult = "!doctypehtmlhtmllang=\"ko\"data-dark=\""
            + "false\"headmetacharset=\"utf-8\"titleNAVERtitle";

        // When
        String result = parser.parse(url, ParserType.EXCEPT_FOR_HTML);

        // Then
        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(expectedResult);
    }
}
