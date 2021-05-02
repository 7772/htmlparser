package com.example.htmlparser.domains.print;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PrinterTest {
    @Autowired
    private Printer printer;

    @Test
    public void success() {
        // Given
        String parsedContent = "WAOSDas234$%^&*(Z@dfNF89=-X0-asdf12345#()63y89hbq2qb12#$@34fau4zHOFNAIgqerh252346722SFNIA456234isdnias";
        int outputUnits = 3;

        // When
        printer.print(parsedContent, outputUnits);

        // Then

    }
}
