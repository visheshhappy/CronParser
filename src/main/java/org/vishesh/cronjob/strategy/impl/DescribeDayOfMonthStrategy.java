package org.vishesh.cronjob.strategy.impl;

import org.vishesh.cronjob.config.CronConfig;
import org.vishesh.cronjob.model.CronFieldValue;
import org.vishesh.cronjob.strategy.CronDescribeStrategy;

import static org.vishesh.cronjob.model.CronFieldValue.ExpressionType.*;

public class DescribeDayOfMonthStrategy implements CronDescribeStrategy {


    @Override
    public String describe(CronFieldValue cronFieldValue) {
        StringBuilder builder = new StringBuilder();
        switch (cronFieldValue.getExpressionType()) {
            case ALWAYS:
                for (int i = 1; i <= 31; i++) {
                    builder.append(i).append(" ");
                }
                break;
            case RANGE:
                String[] range = cronFieldValue.getExpression().split(RANGE.getValue());
                int from = Integer.valueOf(range[0]);
                int to = Integer.valueOf(range[1]);
                for (int i = from; i <= to; i++) {
                    builder.append(i).append(" ");
                }
                break;
            case INTERVAL:
                String[] intervals = cronFieldValue.getExpression().split(INTERVAL.getValue());
                String start = intervals[0];
                int startValue = 0;
                if (!CronConfig.CRON_WILDCARD.equals(start)) {
                    startValue = Integer.valueOf(start);
                }
                int intervalsDuration = Integer.valueOf(intervals[1]);
                for (int i = startValue; i <= 31; i += intervalsDuration) {
                    builder.append(i).append(" ");
                }
                break;
            case SPECIFIC:
                String[] specifics = cronFieldValue.getExpression().split(SPECIFIC.getValue());
                for (int i = 0; i < specifics.length; i++) {
                    builder.append(Integer.valueOf(specifics[i])).append(" ");
                }
                break;
        }

        return builder.toString();
    }

}
