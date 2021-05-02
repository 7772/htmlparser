package com.example.htmlparser.domains.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DividerResultTest {
    @Test
    public void testGetQuotients() {
        // Given
        String content = "asndlfkansdof1";
        int outputUnit = 3;

        Divider divider = new Divider(content, outputUnit);

        DividedResult dividedResult = divider.divide();

        List<String> expectedResults = new ArrayList<>();
        expectedResults.add("asn");
        expectedResults.add("dlf");
        expectedResults.add("kan");
        expectedResults.add("sdo");

        // When
        List<String> quotients = dividedResult.getQuotients();

        // Then
        assertThat(quotients).isNotEmpty();
        assertThat(
            Arrays.equals(quotients.toArray(),
            expectedResults.toArray())
        ).isTrue();
    }

    @Test
    public void testGetReminder() {
        // Given
        String content = "asndlfkansdof1";
        int outputUnit = 3;

        Divider divider = new Divider(content, outputUnit);

        DividedResult dividedResult = divider.divide();

        // When
        String remainder = dividedResult.getRemainder();

        // Then
        assertThat(remainder).isNotNull();
        assertThat(remainder).isEqualTo("f1");
    }

    @Test
    public void testWhenReminderIsZero() {
        // Given
        String content = "asndlfkansdo";
        int outputUnit = 3;

        Divider divider = new Divider(content, outputUnit);

        DividedResult dividedResult = divider.divide();

        // When
        String remainder = dividedResult.getRemainder();

        // Then
        assertThat(remainder).isNull();
    }
}
