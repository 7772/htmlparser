package com.example.htmlparser.domains.outputcondition;

import com.example.htmlparser.domains.parsing.ParsedResult;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.matchesPattern;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AlphanumericOutputConditionStrategyTest {
    private final ParsedResult parsedResult = new ParsedResult();

    @Test
    public void success() {
        // Given
        String content = "Acoq1293-=가나다-a31~!va11@";
        parsedResult.setContent(content);

        OutputConditionStrategy strategy = new AlphanumericOutputConditionStrategy();

        // When
        strategy.apply(parsedResult);

        // Then
        assertThat(parsedResult.getContent()).isNotNull();
        assertThat(parsedResult.getContent()).containsPattern("^[a-zA-Z0-9]*$");
    }
}
