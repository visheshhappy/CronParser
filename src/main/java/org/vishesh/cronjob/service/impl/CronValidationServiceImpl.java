package org.vishesh.cronjob.service.impl;

import org.vishesh.cronjob.config.CronConfig;
import org.vishesh.cronjob.dto.CronExpressionDto;
import org.vishesh.cronjob.exception.ErrorCode;
import org.vishesh.cronjob.exception.InvalidExpressionException;
import org.vishesh.cronjob.service.CronValidationService;
import org.vishesh.cronjob.validator.CronExpressionValidator;

import java.util.List;

public class CronValidationServiceImpl implements CronValidationService {

    final List<CronExpressionValidator> cronExpressionValidatorList;

    public CronValidationServiceImpl(final List<CronExpressionValidator> cronExpressionValidatorList) {
        this.cronExpressionValidatorList = cronExpressionValidatorList;
    }

    @Override
    public void validateCronExpression(String cron) {
        String parsedCron[] = cron.split(CronConfig.CRON_SEPARATOR);
        CronExpressionDto cronExpressionDto = new CronExpressionDto(parsedCron);
        if (cronExpressionDto.getCronExpressions().length != CronConfig.VALID_CRON_LENGTH) {
            throw new InvalidExpressionException("Invalid Cron length ", ErrorCode.INVALID_CRON_LENGTH);
        }
        validateCronExpression(cronExpressionDto);
    }

    private void validateCronExpression(CronExpressionDto cronExpressionDto) {
        for (CronExpressionValidator validator : cronExpressionValidatorList) {
            validator.validate(cronExpressionDto);
        }
    }
}
