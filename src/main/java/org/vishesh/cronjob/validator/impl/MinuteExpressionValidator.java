package org.vishesh.cronjob.validator.impl;

import org.vishesh.cronjob.dto.CronExpressionDto;
import org.vishesh.cronjob.exception.ErrorCode;
import org.vishesh.cronjob.exception.InvalidExpressionException;

import java.time.temporal.ValueRange;

public class MinuteExpressionValidator extends BaseCronExpressionValidator {

    private final int position;
    public MinuteExpressionValidator(ValueRange range, int position) {
        super(range);
        this.position = position;
    }

    @Override
    public void validate(final CronExpressionDto cronExpression) {
        String minuteExpression = cronExpression.getCronExpressions()[position];
        if (!validateExpression(minuteExpression)) {
            throw new InvalidExpressionException("Minute expression is invalid", ErrorCode.INVALID_MINUTE_EXPRESSION);
        }
    }
}
