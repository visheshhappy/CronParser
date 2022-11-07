package org.vishesh.cronjob.service;

import org.vishesh.cronjob.model.CronExpression;

public interface CronParser {

    CronExpression parse(final String cron);

}
