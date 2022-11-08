package org.vishesh.cronjob.validator.impl;

import org.vishesh.cronjob.config.CronConfig;
import org.vishesh.cronjob.dto.CronExpressionDto;
import org.vishesh.cronjob.exception.ErrorCode;
import org.vishesh.cronjob.exception.InvalidExpressionException;
import org.vishesh.cronjob.model.CronFieldValue;

import java.time.temporal.ValueRange;
import java.util.List;

import static org.vishesh.cronjob.model.CronFieldValue.ExpressionType.INTERVAL;
import static org.vishesh.cronjob.model.CronFieldValue.ExpressionType.RANGE;
import static org.vishesh.cronjob.model.CronFieldValue.ExpressionType.SPECIFIC;

public class DayOfMonthExpressionValidator extends BaseCronExpressionValidator {

    private final int position;
    private final int monthPosition;

    public DayOfMonthExpressionValidator(ValueRange valueRange, int position, int monthPosition) {
        super(valueRange);
        this.position = position;
        this.monthPosition = monthPosition;
    }

    @Override
    public void validate(CronExpressionDto cronExpression) {
        String dayExpression = cronExpression.getCronExpressions()[position];
        if (!validateExpression(dayExpression)) {
            throw new InvalidExpressionException("Day of month expression is invalid", ErrorCode.INVALID_DAY_EXPRESSION);
        }

    }
}
