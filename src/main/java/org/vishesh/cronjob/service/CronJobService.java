package org.vishesh.cronjob.service;

import org.vishesh.cronjob.dto.CommandDto;
import org.vishesh.cronjob.dto.CronViewDto;

public interface CronJobService {

    CronViewDto createCronView(CommandDto commandDto);

}
