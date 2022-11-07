package org.vishesh.cronjob.service.impl;

import lombok.AllArgsConstructor;
import org.vishesh.cronjob.dto.CronViewDto;
import org.vishesh.cronjob.factory.DescribeStrategyFactory;
import org.vishesh.cronjob.model.CronExpression;
import org.vishesh.cronjob.model.CronFieldName;
import org.vishesh.cronjob.model.CronFieldValue;
import org.vishesh.cronjob.service.CronViewService;

import java.util.Map;

@AllArgsConstructor
public class CronViewServiceImpl implements CronViewService {

    private final int titleSpaces = 14; // get from config.

    private final DescribeStrategyFactory describeStrategyFactory;

    @Override
    public CronViewDto createView(CronExpression cronExpression, String command) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<CronFieldName, CronFieldValue> entry : cronExpression.getCronFieldMap().entrySet()) {
            CronFieldValue value = entry.getValue();
            String description = describeStrategyFactory.getCronDescribeStrategy(value.getCronFieldName()).describe(value);
            builder.append(getTitle(entry.getKey())).append(description).append("\n");
        }
        builder.append(getCommandTitle()).append(command);
        return new CronViewDto(builder.toString());
    }

    private String getCommandTitle() {
        String command = "command";
        StringBuilder builder = new StringBuilder(command);
        for (int i = 0; i < titleSpaces - command.length(); i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private String getTitle(CronFieldName key) {
        StringBuilder builder = new StringBuilder();
        int extraSpace = 0;
        switch (key) {
            case MINUTE:
                String min = "minute";
                builder.append(min);
                extraSpace = titleSpaces - min.length();
                break;
            case HOUR:
                String hour = "hour";
                builder.append(hour);
                extraSpace = titleSpaces - hour.length();
                break;
            case DAY_OF_MONTH:
                String dayOfMonth = "day of month";
                builder.append(dayOfMonth);
                extraSpace = titleSpaces - dayOfMonth.length();
                break;
            case MONTH:
                String month = "month";
                builder.append(month);
                extraSpace = titleSpaces - month.length();
                break;
            case DAY_OF_WEEK:
                String dayOfWeek = "day of week";
                builder.append(dayOfWeek);
                extraSpace = titleSpaces - dayOfWeek.length();
                break;
        }
        for (int i = 0; i < extraSpace; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }
}
