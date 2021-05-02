package com.example.htmlparser.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.htmlparser.domains.print.DividedResult;
import com.example.htmlparser.services.ParseHTMLService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParseHTMLControllerPrintTest {
    @Autowired
    private WebApplicationContext context;

    @MockBean
    private ParseHTMLService parseHTMLService;

    private MockMvc mvc;

    @BeforeAll
    public void setUp() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .build();
    }

    @Test
    public void test() throws Exception {
        // Given
        String urlToParse = "https://naver.com";
        String parserType = "ALL_TEXT";
        int outputUnit = 3;

        List<String> mockResult = new ArrayList<>();
        mockResult.add("A1a");
        mockResult.add("2B3");
        mockResult.add("C4");

        when(parseHTMLService.print(anyString(), any(), anyInt()))
            .thenReturn(new DividedResult(mockResult, outputUnit));

        String endpoint = "/print"
            + "?urlToParse="
            + urlToParse
            + "&parserType="
            + parserType
            + "&outputUnit="
            + outputUnit;

        // When & Then
        mvc.perform(get(endpoint).contentType(MediaType.APPLICATION_JSON))
            .andExpect(model().attributeExists("quotients"))
            .andExpect(model().attributeExists("remainder"))
            .andExpect(view().name("main"));
    }
}
