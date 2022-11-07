package org.vishesh.cronjob.service.impl;

import lombok.AllArgsConstructor;
import org.vishesh.cronjob.dto.CommandDto;
import org.vishesh.cronjob.dto.CronViewDto;
import org.vishesh.cronjob.model.CronExpression;
import org.vishesh.cronjob.service.CronJobService;
import org.vishesh.cronjob.service.CronParser;
import org.vishesh.cronjob.service.CronViewService;

@AllArgsConstructor
public class CronJobServiceImpl implements CronJobService {

    private final CronParser cronParser;
    private final CronViewService cronViewService;

    @Override
    public CronViewDto createCronView(CommandDto commandDto) {
        CronExpression cronExpression = cronParser.parse(commandDto.getCron());
        return cronViewService.createView(cronExpression, commandDto.getCommand());
    }

}
