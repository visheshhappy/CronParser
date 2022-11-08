package org.vishesh.cronjob.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.vishesh.cronjob.config.CronConfig;
import org.vishesh.cronjob.config.CronJobConfiguration;
import org.vishesh.cronjob.dto.CommandDto;
import org.vishesh.cronjob.dto.CronViewDto;
import org.vishesh.cronjob.service.CronJobService;

public class CronJobTester {
    public static void main(String[] args) {

        CommandDto dto = getCommandDto(args);
        //CommandDto dto = new CommandDto("*/15 0 1,30 2 1-5", "/usr/bin/find");

        Injector injector = Guice.createInjector(new CronJobConfiguration());
        CronJobService cronJobService = injector.getInstance(CronJobService.class);
        CronViewDto cronViewDto = cronJobService.createCronView(dto);
        System.out.println(cronViewDto.getView());
    }

    private static CommandDto getCommandDto(String[] args) {
        for (int i = 0; i < args.length; i++) {
            System.out.println(args[i]);
        }

        if (args.length != 6) {
            throw new IllegalArgumentException("Given Arguments are not correct");
        }

        String command = args[args.length - 1];
        StringBuilder cronExp = new StringBuilder();
        for (int i = 0; i < args.length - 1; i++) {
            cronExp.append(args[i] + CronConfig.CRON_SEPARATOR);
        }
        String cronExpValue = cronExp.toString().trim();
        System.out.println(cronExpValue);
        CommandDto dto = new CommandDto(cronExp.toString().trim(), command);
        return dto;
    }
}
