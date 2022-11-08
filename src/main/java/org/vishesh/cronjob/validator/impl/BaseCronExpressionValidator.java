package org.vishesh.cronjob.validator.impl;

import org.vishesh.cronjob.config.CronConfig;
import org.vishesh.cronjob.dto.CronExpressionDto;
import org.vishesh.cronjob.utils.CommonValidationService;
import org.vishesh.cronjob.validator.CronExpressionValidator;

import java.time.temporal.ValueRange;

public abstract class BaseCronExpressionValidator implements CronExpressionValidator {

    private final ValueRange valueRange;

    private final CommonValidationService commonValidationService;

    public BaseCronExpressionValidator(ValueRange valueRange) {
        this.valueRange = valueRange;
        this.commonValidationService = new CommonValidationService();
    }

    public abstract void validate(CronExpressionDto cronExpression);

    protected boolean validateExpression(final String expression) {
        if (CronConfig.CRON_WILDCARD.equals(expression)) {
            return true;
        }

        if (commonValidationService.isInvalidValue(expression, valueRange)) {
            return false;
        }

        if (expression.contains(CronConfig.CRON_COMMA_CHARACTER)) {
            String[] values = expression.split(CronConfig.CRON_COMMA_CHARACTER);
            if (values.length == 0) {
                return false;
            }
            for (String val : values) {
                if (commonValidationService.isInvalidValue(val, valueRange)) {
                    return false;
                }
            }

            return true;
        }

        if (expression.contains(CronConfig.CRON_INTERVAL_CHARACTER)) {
            if (commonValidationService.containsDuplicate(expression, CronConfig.CRON_INTERVAL_CHARACTER)) {
                return false;
            }

            String[] values = expression.split(CronConfig.CRON_INTERVAL_CHARACTER);
            if (values.length == 0) {
                return false;
            }
            int start = 0;
            for (String val : values) {
                if (start == 0 && CronConfig.CRON_WILDCARD.equals(val)) {
                    start++;
                    continue;
                }
                if (commonValidationService.isInvalidValue(val, valueRange)) {
                    return false;
                }
            }

            return true;

        }

        if (expression.contains(CronConfig.CRON_RANGE_CHARACTER)) {
            if (commonValidationService.containsDuplicate(expression, CronConfig.CRON_RANGE_CHARACTER)) {
                return false;
            }

            String[] values = expression.split(CronConfig.CRON_RANGE_CHARACTER);
            if (values.length == 0) {
                return false;
            }
            for (String val : values) {
                if (commonValidationService.isInvalidValue(val, valueRange)) {
                    return false;
                }
            }

            return true;
        }

        if (!commonValidationService.isNumeric(expression)) {
            return false;
        }

        return true;
    }


}
