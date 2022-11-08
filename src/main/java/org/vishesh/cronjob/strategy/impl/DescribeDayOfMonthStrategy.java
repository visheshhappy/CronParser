package org.vishesh.cronjob.strategy.impl;

import org.vishesh.cronjob.model.CronFieldValue;

import java.util.List;

public class DescribeDayOfMonthStrategy extends AbstractDescribeStrategy {

    @Override
    public List<Integer> describe(CronFieldValue cronFieldValue) {
        return describeExpression(cronFieldValue);
    }

}
