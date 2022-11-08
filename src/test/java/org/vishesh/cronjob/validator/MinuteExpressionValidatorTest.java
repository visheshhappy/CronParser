package org.vishesh.cronjob.validator;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.vishesh.cronjob.config.CronConfig;
import org.vishesh.cronjob.dto.CronExpressionDto;
import org.vishesh.cronjob.exception.InvalidExpressionException;
import org.vishesh.cronjob.validator.impl.MinuteExpressionValidator;


public class MinuteExpressionValidatorTest {

    private CronExpressionValidator minuteExpressionValidator = new MinuteExpressionValidator(CronConfig.MINUTE_RANGE, CronConfig.MINUTE_POSITION);

    private static String[] getExpression(String exp) {
        String[] expressions = new String[CronConfig.VALID_CRON_LENGTH];
        expressions[CronConfig.MINUTE_POSITION] = exp;
        return expressions;
    }

    @Test
    public void validate_whenExpressionIsWildCard() {
        String exp = "*";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertDoesNotThrow(() -> minuteExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsNumber() {
        String exp = "1";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertDoesNotThrow(() -> minuteExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsNumberAndOutOfUpperRange() {
        String exp = "60";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> minuteExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsNumberAndOutOfLowerRange() {
        String exp = "-1";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> minuteExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsValidSequence() {
        String exp = "1,3,5";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertDoesNotThrow(() -> minuteExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsInvalidSequence() {
        String exp = "1,67,-1,4";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> minuteExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsValidRange() {
        String exp = "1-25";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertDoesNotThrow(() -> minuteExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsInvalidRange() {
        String exp = "1-100";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> minuteExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsValidInterval() {
        String exp = "1/25";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertDoesNotThrow(() -> minuteExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionIsInvalidInterval() {
        String exp = "1/100";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> minuteExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionContainInvalidCharacters() {
        String exp = "1*25";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> minuteExpressionValidator.validate(cronExpression));
    }

    @Test
    public void validate_whenExpressionContainDuplicateValidCharacters() {
        String exp = "1/100/20";
        String[] expressions = getExpression(exp);
        CronExpressionDto cronExpression = new CronExpressionDto(expressions);
        Assertions.assertThrows(InvalidExpressionException.class, () -> minuteExpressionValidator.validate(cronExpression));
    }

}
