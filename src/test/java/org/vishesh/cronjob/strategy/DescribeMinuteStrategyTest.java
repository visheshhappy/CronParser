package org.vishesh.cronjob.strategy;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.vishesh.cronjob.config.CronConfig;
import org.vishesh.cronjob.model.CronFieldName;
import org.vishesh.cronjob.model.CronFieldValue;
import org.vishesh.cronjob.strategy.impl.DescribeMinuteStrategy;

import java.util.ArrayList;
import java.util.List;

public class DescribeMinuteStrategyTest {

    private DescribeMinuteStrategy describeMinuteStrategy = new DescribeMinuteStrategy();

    @Test
    public void test_whenTypeIsAlways() {
        CronFieldValue cronFieldValue = new CronFieldValue(CronFieldName.MINUTE, "*", CronFieldValue.ExpressionType.ALWAYS, CronConfig.MINUTE_RANGE);
        List<Integer> values = Assertions.assertDoesNotThrow(() -> describeMinuteStrategy.describe(cronFieldValue));
        Assertions.assertNotNull(values);

        List<Integer> expected = new ArrayList<>();
        for (long i = cronFieldValue.getValueRange().getMinimum(); i <= cronFieldValue.getValueRange().getMaximum(); i++) {
            expected.add((int)i);
        }
        Assertions.assertEquals(expected, values);
    }

}
