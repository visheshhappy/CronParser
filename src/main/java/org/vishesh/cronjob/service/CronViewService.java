package org.vishesh.cronjob.service;

import org.vishesh.cronjob.dto.CronViewDto;
import org.vishesh.cronjob.model.CronExpression;

public interface CronViewService {

    CronViewDto createView(CronExpression cronExpression, String command);

}
