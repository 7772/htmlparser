package com.example.htmlparser.domains.outputcondition;

import com.example.htmlparser.domains.parsing.ParsedResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AscOutputConditionStrategyTest {
    private final ParsedResult parsedResult = new ParsedResult();

    @Test
    public void testContentIsNull() {
        // Given
        parsedResult.setContent(null);

        OutputConditionStrategy strategy = new AscOutputConditionStrategy();

        // When
        strategy.apply(parsedResult);

        // Then
        assertThat(parsedResult.getContent()).isNull();
    }

    @Test
    public void testSortNumberFirst() {
        // Given
        String content = "abc321";
        parsedResult.setContent(content);

        String result = "123abc";

        OutputConditionStrategy strategy = new AscOutputConditionStrategy();

        // When
        strategy.apply(parsedResult);

        // Then
        assertThat(parsedResult.getContent()).isEqualTo(result);
    }

    @Test
    public void testSortSameAlphabetAndUppercaseFirst() {
        // Given
        String content = "ABab";
        parsedResult.setContent(content);

        String result = "AaBb";

        OutputConditionStrategy strategy = new AscOutputConditionStrategy();

        // When
        strategy.apply(parsedResult);

        // Then
        assertThat(parsedResult.getContent()).isEqualTo(result);
    }

    @Test
    public void testSuccessComplicatedCases() {
        // Given
        String content1 = "AAAvaw14123AAAvo94KFUZ091M8g1jf810fjzh173wl2nvgpzlv82A123fAvowjAAAAaaabbbb";
        String content2 = "ovnz01FOAJH8fj9JFJ2jfkckGCG18fjzkzn2DJD0CNBQOjckanxifj1983rjFJC0234SNXFJ9jfhc7";
        String content3 = "alzoOLVO391jvkcvj2IVJCBQOifhqifkco984JNkckcbqi38CKJCNDJZDJ93jfjdzlfidf29VKCI";
        String content4 = "OVZL0f9VKCKCNicmndh28dkCJCX8035678920987djcnHCXJyDJ27XCSJc81hd9XKCXzJCH28jc91dkc9JCJ8";

        ParsedResult parsedResult1 = new ParsedResult();
        ParsedResult parsedResult2 = new ParsedResult();
        ParsedResult parsedResult3 = new ParsedResult();
        ParsedResult parsedResult4 = new ParsedResult();

        parsedResult1.setContent(content1);
        parsedResult2.setContent(content2);
        parsedResult3.setContent(content3);
        parsedResult4.setContent(content4);

        String result1 = "001111111222233344788899AAAAAAAAAAAAaaaabbbbFfffgghjjjKllMnoopUvvvvvwwwZzz";
        String result2 = "0001112223347888999AaBCCCcccDDFFFFfffffGGHhiJJJJJJjjjjjjjkkkkNNnnnOOoQrSvXxzzz";
        String result3 = "1223334889999aBbCCCCccccDDddfffffhIIiiiiJJJJJjjjjKKkkkkLllNNOOOooQqqVVVvvZzz";
        String result4 = "0001122223567778888888999999CCCCCCCCCcccccDdddddfHHhhiJJJJJJJjjKKKkkLmNnnOSVVXXXXXyZz";

        OutputConditionStrategy strategy1 = new AscOutputConditionStrategy();
        OutputConditionStrategy strategy2 = new AscOutputConditionStrategy();
        OutputConditionStrategy strategy3 = new AscOutputConditionStrategy();
        OutputConditionStrategy strategy4 = new AscOutputConditionStrategy();

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
