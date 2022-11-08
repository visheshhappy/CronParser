package org.vishesh.cronjob.validator.impl;

import org.vishesh.cronjob.dto.CronExpressionDto;
import org.vishesh.cronjob.exception.ErrorCode;
import org.vishesh.cronjob.exception.InvalidExpressionException;

import java.time.temporal.ValueRange;

public class MonthExpressionValidator extends BaseCronExpressionValidator {

    private final int position;


    public MonthExpressionValidator(ValueRange valueRange, int position) {
        super(valueRange);
        this.position = position;
    }

    @Override
    public void validate(CronExpressionDto cronExpression) {
        String monthExpression = cronExpression.getCronExpressions()[position];
        if (!validateExpression(monthExpression)) {
            throw new InvalidExpressionException("Month expression is invalid", ErrorCode.INVALID_MONTH_EXPRESSION);
        }

    }


}
