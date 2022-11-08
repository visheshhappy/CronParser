package org.vishesh.cronjob.strategy.impl;

import org.vishesh.cronjob.config.CronConfig;
import org.vishesh.cronjob.model.CronFieldValue;
import org.vishesh.cronjob.strategy.CronDescribeStrategy;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.List;

import static org.vishesh.cronjob.model.CronFieldValue.ExpressionType.INTERVAL;
import static org.vishesh.cronjob.model.CronFieldValue.ExpressionType.RANGE;
import static org.vishesh.cronjob.model.CronFieldValue.ExpressionType.SPECIFIC;

public abstract class AbstractDescribeStrategy implements CronDescribeStrategy {

    public abstract List<Integer> describe(CronFieldValue cronFieldValue);

    public List<Integer> describeExpression(final CronFieldValue cronFieldValue) {
        List<Integer> builder = new ArrayList();
        ValueRange valueRange = cronFieldValue.getValueRange();
        switch (cronFieldValue.getExpressionType()) {
            case ALWAYS:
                for (long i = valueRange.getMinimum(); i <= valueRange.getMaximum(); i++) {
                    builder.add((int) i);
                }
                break;
            case RANGE:
                String[] range = cronFieldValue.getExpression().split(RANGE.getValue());
                int from = Integer.valueOf(range[0]);
                int to = Integer.valueOf(range[1]);
                for (int i = from; i <= to; i++) {
                    builder.add(i);
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
                for (int i = startValue; i <= valueRange.getMaximum(); i += intervalsDuration) {
                    builder.add(i);
                }
                break;
            case SPECIFIC:
                String[] specifics = cronFieldValue.getExpression().split(SPECIFIC.getValue());
                for (int i = 0; i < specifics.length; i++) {
                    builder.add(Integer.valueOf(specifics[i]));
                }
                break;
        }

        return builder;
    }
}
