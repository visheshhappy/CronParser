package org.vishesh.cronjob.utils;

import java.time.temporal.ValueRange;

public class CommonValidationService {

    public boolean containsDuplicate(String expression, String pattern) {
        return expression.indexOf(pattern) != expression.lastIndexOf(pattern);
    }

    public boolean isInvalidValue(String val, ValueRange range) {
        return isNumeric(val) && !isWithinRange(Integer.parseInt(val), range);
    }

    public boolean isWithinRange(int value, ValueRange range) {
        return range.isValidValue(value);
    }

    public boolean isNumeric(String expression) {
        try {
            Integer.parseInt(expression);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
