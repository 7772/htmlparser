package com.example.htmlparser.domains.print;

import java.util.ArrayList;
import java.util.List;

public class DividedResult {
    private final List<String> quotients = new ArrayList<>();

    private final List<String> remainders = new ArrayList<>();

    public List<String> getQuotients() {
        return this.quotients;
    }

    public List<String> getRemainders() {
        return this.remainders;
    }

    public void addQuotient(String quotient) {
        quotients.add(quotient);
    }

    public void addRemainder(String remainder) {
        remainders.add(remainder);
    }
}
