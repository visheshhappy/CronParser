package org.vishesh.cronjob.service;

import org.vishesh.cronjob.model.CronExpression;

public interface CronValidationService {

    // todo think of better name of method.
    void validateCronExpression(String cron);


}
