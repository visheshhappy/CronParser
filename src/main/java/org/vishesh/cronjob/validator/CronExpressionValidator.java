package org.vishesh.cronjob.validator;

import org.vishesh.cronjob.dto.CronExpressionDto;

public interface CronExpressionValidator {

    void validate(CronExpressionDto cronExpression);

}
