package com.example.htmlparser.domains.outputcondition;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.matchesPattern;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlphanumericOutputConditionStrategyTest {
    private final OutputConditionResult outputConditionResult = new OutputConditionResult();

    @Test
    public void testContentIsNull() {
        // Given
        outputConditionResult.setContent(null);

        OutputConditionStrategy strategy = new AlphanumericOutputConditionStrategy();

        // When
        strategy.apply(outputConditionResult);

        // Then
        assertThat(outputConditionResult.getContent()).isNull();
    }

    @Test
    public void success() {
        // Given
        String content = "Acoq1293-=가나다-a31~!va11@";
        outputConditionResult.setContent(content);

        OutputConditionStrategy strategy = new AlphanumericOutputConditionStrategy();

        // When
        strategy.apply(outputConditionResult);

        // Then
        assertThat(outputConditionResult.getContent()).isNotNull();
        assertThat(outputConditionResult.getContent()).containsPattern("^[a-zA-Z0-9]*$");
    }
}
