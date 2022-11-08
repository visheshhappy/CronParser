package org.vishesh.cronjob.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.temporal.ValueRange;

@AllArgsConstructor
@Getter
public class CronFieldValue {

    private final CronFieldName cronFieldName;
    private final String expression;
    private final ExpressionType expressionType;
    private final ValueRange valueRange;

    public enum ExpressionType {
        ALWAYS("*"), RANGE("-"), SPECIFIC(","), INTERVAL("/");

        String value;

        ExpressionType(String s) {
            this.value = s;
        }

        public String getValue() {
            return value;
        }
    }
}
