package org.vishesh.cronjob.strategy;

import org.vishesh.cronjob.model.CronFieldValue;

import java.util.List;

public interface CronDescribeStrategy {

    List<Integer> describe(CronFieldValue cronFieldValue);

}
