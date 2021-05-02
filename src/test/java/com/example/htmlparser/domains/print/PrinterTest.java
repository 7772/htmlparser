package com.example.htmlparser.domains.print;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PrinterTest {
    @Autowired
    private Printer printer;

    /**
     * parsedContent 가 존재하지 않을 경우
     * 빈 리스트를 반환할 수 있다.
     */
    @Test
    public void testWhenParsedContentIsNull() {
        // Given
        int outputUnit = 3;

        // When
        DividedResult dividedResult = printer.print(null, outputUnit);

        // Then
        assertThat(dividedResult.get()).isEmpty();
    }

    /**
     * outputUnit 이 0 이면 모든 결과는 한 Array의 원소 내에 존재해야 한다.
     */
    @Test
    public void testWhenOutputUnitIsZero() {
        // Given
        String parsedContent = "WAOSDas234$%^&*(Z@dfN";
        int outputUnit = 0;

        // When
        DividedResult dividedResult = printer.print(parsedContent, outputUnit);

        // Then
        assertThat(dividedResult.get().size()).isEqualTo(1);
        assertThat(dividedResult.get()).contains("A2a3D4dfNOSsWZ");
    }

    /**
     * 영어 및 숫자 출력, 오름차순 출력, 영어 숫자 Mix 조건을 모두 적용시키고,
     * outputUnit 에 따라 나누어진 결과를 반환할 수 있다.
     */
    @Test
    public void testWhenApplyingAllStrategies() {
        // Given
        String parsedContent = "WAOSDas234$%^&*(Z@dfN";
        int outputUnit = 3;

        // When
        DividedResult dividedResult = printer.print(parsedContent, outputUnit);

        // Then
        assertThat(dividedResult.get()).contains("A2a");
        assertThat(dividedResult.get()).contains("3D4");
        assertThat(dividedResult.get()).contains("dfN");
        assertThat(dividedResult.get()).contains("OSs");
        assertThat(dividedResult.get()).contains("WZ");
    }
}
