package org.vishesh.cronjob.strategy;

import org.vishesh.cronjob.model.CronFieldValue;

public interface CronDescribeStrategy {

    String describe(CronFieldValue cronFieldValue);

}
