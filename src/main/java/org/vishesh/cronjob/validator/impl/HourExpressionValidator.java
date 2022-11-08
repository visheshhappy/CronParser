package org.vishesh.cronjob.validator.impl;

import org.vishesh.cronjob.dto.CronExpressionDto;
import org.vishesh.cronjob.exception.ErrorCode;
import org.vishesh.cronjob.exception.InvalidExpressionException;

import java.time.temporal.ValueRange;

public class HourExpressionValidator extends BaseCronExpressionValidator {

    private final int position;

    public HourExpressionValidator(ValueRange valueRange, int position) {
        super(valueRange);
        this.position = position;
    }

    @Override
    public void validate(CronExpressionDto cronExpression) {
        String hourExpression = cronExpression.getCronExpressions()[position];
        if (!validateExpression(hourExpression)) {
            throw new InvalidExpressionException("Hour expression is invalid", ErrorCode.INVALID_HOUR_EXPRESSION);
        }
    }


}
