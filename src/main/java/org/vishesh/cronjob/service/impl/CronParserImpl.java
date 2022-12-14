package org.vishesh.cronjob.service.impl;

import org.vishesh.cronjob.config.CronConfig;
import org.vishesh.cronjob.exception.ErrorCode;
import org.vishesh.cronjob.exception.InvalidExpressionException;
import org.vishesh.cronjob.model.CronExpression;
import org.vishesh.cronjob.model.CronFieldName;
import org.vishesh.cronjob.model.CronFieldValue;
import org.vishesh.cronjob.service.CronParser;
import org.vishesh.cronjob.service.CronValidationService;

import java.util.LinkedHashMap;
import java.util.Map;

public class CronParserImpl implements CronParser {

    private final CronValidationService cronValidationService;

    public CronParserImpl(CronValidationService cronValidationService) {
        this.cronValidationService = cronValidationService;
    }

    @Override
    public CronExpression parse(final String cron) {

        cronValidationService.validateCronExpression(cron);
        String parsedCron[] = cron.split(CronConfig.CRON_SEPARATOR);

        Map<CronFieldName, CronFieldValue> cronMap = new LinkedHashMap();
        String minuteExpression = parsedCron[CronConfig.MINUTE_POSITION];
        CronFieldValue.ExpressionType type = getType(minuteExpression);
        cronMap.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, minuteExpression, type, CronConfig.MINUTE_RANGE));

        String hourExpression = parsedCron[CronConfig.HOUR_POSITION];
        CronFieldValue.ExpressionType hourType = getType(hourExpression);
        cronMap.put(CronFieldName.HOUR, new CronFieldValue(CronFieldName.HOUR, hourExpression, hourType, CronConfig.HOUR_RANGE));

        String dayOfMonth = parsedCron[CronConfig.DAY_OF_MONTH_POSITION];
        CronFieldValue.ExpressionType dayOfMonthExp = getType(dayOfMonth);
        cronMap.put(CronFieldName.DAY_OF_MONTH, new CronFieldValue(CronFieldName.DAY_OF_MONTH, dayOfMonth, dayOfMonthExp, CronConfig.DAY_OF_MONTH_RANGE));

        String monthExpression = parsedCron[CronConfig.MONTH_POSITION];
        CronFieldValue.ExpressionType monthTypeExp = getType(monthExpression);
        cronMap.put(CronFieldName.MONTH, new CronFieldValue(CronFieldName.MONTH, monthExpression, monthTypeExp, CronConfig.MONTH_RANGE));

        String dayOfWeekExpression = parsedCron[CronConfig.DAY_OF_WEEK_POSITION];
        CronFieldValue.ExpressionType dayOfWeekExp = getType(dayOfWeekExpression);
        cronMap.put(CronFieldName.DAY_OF_WEEK, new CronFieldValue(CronFieldName.DAY_OF_WEEK, dayOfWeekExpression, dayOfWeekExp, CronConfig.DAY_OF_WEEK_RANGE));

        CronExpression cronExpression = new CronExpression(cron, cronMap);

        return cronExpression;
    }

    private CronFieldValue.ExpressionType getType(String expression) {

        if (CronConfig.CRON_WILDCARD.equals(expression)) {
            return CronFieldValue.ExpressionType.ALWAYS;
        } else if (expression.contains(",") || isNumeric(expression)) {
            return CronFieldValue.ExpressionType.SPECIFIC;
        } else if (expression.contains("-")) {
            return CronFieldValue.ExpressionType.RANGE;
        } else if (expression.contains("/")) {
            return CronFieldValue.ExpressionType.INTERVAL;
        }

        throw new InvalidExpressionException("expression is invalid", ErrorCode.INVALID_EXPRESSION_CHARACTER);

    }

    private boolean isNumeric(String minuteExpression) {
        try {
            Integer.parseInt(minuteExpression);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
