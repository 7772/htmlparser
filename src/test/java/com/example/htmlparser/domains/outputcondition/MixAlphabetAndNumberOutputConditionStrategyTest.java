package com.example.htmlparser.domains.outputcondition;

import com.example.htmlparser.domains.parsing.ParsedResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MixAlphabetAndNumberOutputConditionStrategyTest {
    private final ParsedResult parsedResult = new ParsedResult();

    @Test
    public void testContentIsNull() {
        // Given
        parsedResult.setContent(null);

        OutputConditionStrategy strategy = new MixAlphabetAndNumberOutputConditionStrategy();

        // When
        strategy.apply(parsedResult);

        // Then
        assertThat(parsedResult.getContent()).isNull();
    }

    @Test
    public void testMixAlphabetAndNumber() {
        // Given
        String content1 = "001111111222233344788899AAAAAAAAAAAAaaaabbbbFfffgghjjjKllMnoopUvvvvvwwwZzz";
        String content2 = "0001112223347888999AaBCCCcccDDFFFFfffffGGHhiJJJJJJjjjjjjjkkkkNNnnnOOoQrSvXxzzz";
        String content3 = "1223334889999aBbCCCCccccDDddfffffhIIiiiiJJJJJjjjjKKkkkkLllNNOOOooQqqVVVvvZzz";
        String content4 = "0000000111111111111222222233333333344444778888888888899999999dijKLMNNnOOorSuuVVvZZz";

        ParsedResult parsedResult1 = new ParsedResult();
        ParsedResult parsedResult2 = new ParsedResult();
        ParsedResult parsedResult3 = new ParsedResult();
        ParsedResult parsedResult4 = new ParsedResult();

        parsedResult1.setContent(content1);
        parsedResult2.setContent(content2);
        parsedResult3.setContent(content3);
        parsedResult4.setContent(content4);

        String result1 = "A0A0A1A1A1A1A1A1A1A2A2A2a2a3a3a3b4b4b7b8F8f8f9f9gghjjjKllMnoopUvvvvvwwwZzz";
        String result2 = "A0a0B0C1C1C1c2c2c2D3D3F4F7F8F8f8f9f9f9fGGHhiJJJJJJjjjjjjjkkkkNNnnnOOoQrSvXxzzz";
        String result3 = "a1B2b2C3C3C3C4c8c8c9c9D9D9ddfffffhIIiiiiJJJJJjjjjKKkkkkLllNNOOOooQqqVVVvvZzz";
        String result4 = "d0i0j0K0L0M0N0N1n1O1O1o1r1S1u1u1V1V1v1Z2Z2z2222233333333344444778888888888899999999";

        OutputConditionStrategy strategy1 = new MixAlphabetAndNumberOutputConditionStrategy();
        OutputConditionStrategy strategy2 = new MixAlphabetAndNumberOutputConditionStrategy();
        OutputConditionStrategy strategy3 = new MixAlphabetAndNumberOutputConditionStrategy();
        OutputConditionStrategy strategy4 = new MixAlphabetAndNumberOutputConditionStrategy();

        // When
        strategy1.apply(parsedResult1);
        strategy2.apply(parsedResult2);
        strategy3.apply(parsedResult3);
        strategy4.apply(parsedResult4);

        // Then
        assertThat(parsedResult1.getContent()).isEqualTo(result1);
        assertThat(parsedResult2.getContent()).isEqualTo(result2);
        assertThat(parsedResult3.getContent()).isEqualTo(result3);
        assertThat(parsedResult4.getContent()).isEqualTo(result4);
    }
}
