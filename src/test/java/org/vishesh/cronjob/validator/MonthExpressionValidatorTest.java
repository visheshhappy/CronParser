package org.vishesh.cronjob.validator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.vishesh.cronjob.config.CronConfig;
import org.vishesh.cronjob.dto.CronExpressionDto;
import org.vishesh.cronjob.exception.InvalidExpressionException;
import org.vishesh.cronjob.model.CronFieldName;
import org.vishesh.cronjob.model.CronFieldValue;
import org.vishesh.cronjob.validator.impl.MonthExpressionValidator;

import java.util.HashMap;
import java.util.Map;

public class MonthExpressionValidatorTest {

    private CronExpressionValidator monthExpressionValidator = new MonthExpressionValidator(CronConfig.MONTH_RANGE, CronConfig.MONTH_POSITION);

    private static String[] getExpression(String exp) {
        String[] expressions = new String[CronConfig.VALID_CRON_LENGTH];
        expressions[CronConfig.MONTH_POSITION] = exp;
        return expressions;
    }

    @Test
    public void validate_whenExpressionIsWildCard() {
        String exp = "*";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertDoesNotThrow(() -> monthExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsNumber() {
        String exp = "1";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertDoesNotThrow(() -> monthExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsNumberAndOutOfUpperRange() {
        String exp = "60";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> monthExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsNumberAndOutOfLowerRange() {
        String exp = "-1";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> monthExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsValidSequence() {
        String exp = "1,3,5";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertDoesNotThrow(() -> monthExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsInvalidSequence() {
        String exp = "1,67,-1,4";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> monthExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsValidRange() {
        String exp = "1-11";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertDoesNotThrow(() -> monthExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsInvalidRange() {
        String exp = "1-100";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> monthExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsValidInterval() {
        String exp = "1/10";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertDoesNotThrow(() -> monthExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsInvalidInterval() {
        String exp = "1/100";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> monthExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionContainInvalidCharacters() {
        String exp = "1*25";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> monthExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionContainDuplicateValidCharacters() {
        String exp = "1/100/20";
        String[] expressions = getExpression(exp);
        Map<CronFieldName, CronFieldValue> map = new HashMap<>();
        map.put(CronFieldName.MINUTE, new CronFieldValue(CronFieldName.MINUTE, exp, CronFieldValue.ExpressionType.ALWAYS));
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> monthExpressionValidator.validate(cronExpression));
    }

}
