package org.vishesh.cronjob.factory;

import lombok.AllArgsConstructor;
import org.vishesh.cronjob.model.CronFieldName;
import org.vishesh.cronjob.strategy.CronDescribeStrategy;

@AllArgsConstructor
public class DescribeStrategyFactory {

    private final CronDescribeStrategy minuteStrategy;
    private final CronDescribeStrategy hourStrategy;
    private final CronDescribeStrategy monthStrategy;
    private final CronDescribeStrategy dayOfMonthStrategy;
    private final CronDescribeStrategy dayOfWeekStrategy;

    public CronDescribeStrategy getCronDescribeStrategy(CronFieldName cronFieldName) {
        switch (cronFieldName) {
            case MINUTE:
                return minuteStrategy;
            case HOUR:
                return hourStrategy;
            case DAY_OF_MONTH:
                return dayOfMonthStrategy;
            case MONTH:
                return monthStrategy;
            case DAY_OF_WEEK:
                return dayOfWeekStrategy;
            default:
                throw new RuntimeException("Invalid cron value");
        }
    }

}
