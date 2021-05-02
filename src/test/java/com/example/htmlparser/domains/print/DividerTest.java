package com.example.htmlparser.domains.print;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DividerTest {
    private final Divider divider = new Divider();

    @Test
    public void testContentIsNull() {
        // Given
        int outputUnit = 3;

        // When
        DividedResult dividedResult = divider.divide(null, outputUnit);

        // Then
        assertThat(dividedResult.get()).isEmpty();
    }

    @Test
    public void testDivideByOutputUnit() {
        // Given
        String content = "asndlfkansdof1";
        int outputUnit = 3;

        // When
        DividedResult dividedResult = divider.divide(content, outputUnit);

        // Then
        assertThat(dividedResult.get()).isNotEmpty();

        assertThat(dividedResult.get().contains("asn")).isTrue();
        assertThat(dividedResult.get().contains("dlf")).isTrue();
        assertThat(dividedResult.get().contains("kan")).isTrue();
        assertThat(dividedResult.get().contains("sdo")).isTrue();
        assertThat(dividedResult.get().contains("f1")).isTrue();
    }
}
