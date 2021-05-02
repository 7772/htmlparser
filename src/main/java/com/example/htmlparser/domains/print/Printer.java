package com.example.htmlparser.domains.print;

import java.util.ArrayList;
import java.util.List;

import com.example.htmlparser.domains.outputcondition.AlphanumericOutputConditionStrategy;
import com.example.htmlparser.domains.outputcondition.AscOutputConditionStrategy;
import com.example.htmlparser.domains.outputcondition.MixAlphabetAndNumberOutputConditionStrategy;
import com.example.htmlparser.domains.outputcondition.OutputConditionStrategy;
import com.example.htmlparser.domains.outputcondition.OutputConditionResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Printer {
    private final List<OutputConditionStrategy> strategies = new ArrayList<>();

    @Autowired
    public Printer(
        AlphanumericOutputConditionStrategy alphanumericOutputConditionStrategy,
        AscOutputConditionStrategy ascOutputConditionStrategy,
        MixAlphabetAndNumberOutputConditionStrategy mixAlphabetAndNumberOutputConditionStrategy
    ) {
        strategies.add(alphanumericOutputConditionStrategy);
        strategies.add(ascOutputConditionStrategy);
        strategies.add(mixAlphabetAndNumberOutputConditionStrategy);
    }

    public DividedResult print(String parsedContent, int outputUnit) {
        OutputConditionResult outputConditionResult = new OutputConditionResult();
        outputConditionResult.setContent(parsedContent);

        applyStrategies(outputConditionResult);

        return divide(outputConditionResult.getContent(), outputUnit);
    }

    private void applyStrategies(OutputConditionResult outputConditionResult) {
        strategies.stream().forEach(strategy -> {
            strategy.apply(outputConditionResult);
        });
    }

    private DividedResult divide(String content, int outputUnit) {
        Divider divider = new Divider(content, outputUnit);

        return divider.divide();
    }
}
