package org.vishesh.cronjob.validator.impl;

import org.vishesh.cronjob.dto.CronExpressionDto;
import org.vishesh.cronjob.exception.ErrorCode;
import org.vishesh.cronjob.exception.InvalidExpressionException;

import java.time.temporal.ValueRange;

public class DayOfWeekExpressionValidator extends BaseCronExpressionValidator {

    private final int position;

    public DayOfWeekExpressionValidator(ValueRange valueRange, int position) {
        super(valueRange);
        this.position = position;
    }

    @Override
    public void validate(CronExpressionDto cronExpression) {
        String dayExpression = cronExpression.getCronExpressions()[position];
        if (!validateExpression(dayExpression)) {
            throw new InvalidExpressionException("Day of week expression is invalid", ErrorCode.INVALID_WEEK_EXPRESSION);
        }
    }

}
