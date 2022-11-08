package org.vishesh.cronjob.factory;

import org.junit.Assert;
import org.junit.Test;
import org.vishesh.cronjob.model.CronFieldName;
import org.vishesh.cronjob.strategy.CronDescribeStrategy;
import org.vishesh.cronjob.strategy.impl.DescribeDayOfMonthStrategy;
import org.vishesh.cronjob.strategy.impl.DescribeDayOfWeekStrategy;
import org.vishesh.cronjob.strategy.impl.DescribeHourStrategy;
import org.vishesh.cronjob.strategy.impl.DescribeMinuteStrategy;
import org.vishesh.cronjob.strategy.impl.DescribeMonthStrategy;

public class DescribeStrategyFactoryTest {

    private CronDescribeStrategy minuteStrategy = new DescribeMinuteStrategy();

    private CronDescribeStrategy hourStrategy = new DescribeHourStrategy();

    private CronDescribeStrategy monthStrategy = new DescribeMonthStrategy();

    private CronDescribeStrategy dayOfMonthStrategy = new DescribeDayOfMonthStrategy();

    private CronDescribeStrategy dayOfWeekStrategy = new DescribeDayOfWeekStrategy();

    private DescribeStrategyFactory describeStrategyFactory = new DescribeStrategyFactory(minuteStrategy, hourStrategy, monthStrategy, dayOfMonthStrategy, dayOfWeekStrategy);

    @Test
    public void testGetCronDescribeStrategy_whenFieldIsMinute() {
        CronDescribeStrategy strategy = describeStrategyFactory.getCronDescribeStrategy(CronFieldName.MINUTE);
        Assert.assertTrue(strategy instanceof DescribeMinuteStrategy);
    }

    @Test
    public void testGetCronDescribeStrategy_whenFieldIsHour() {
        CronDescribeStrategy strategy = describeStrategyFactory.getCronDescribeStrategy(CronFieldName.HOUR);
        Assert.assertTrue(strategy instanceof DescribeHourStrategy);
    }

    @Test
    public void testGetCronDescribeStrategy_whenFieldIsMonth() {
        CronDescribeStrategy strategy = describeStrategyFactory.getCronDescribeStrategy(CronFieldName.MONTH);
        Assert.assertTrue(strategy instanceof DescribeMonthStrategy);
    }

    @Test
    public void testGetCronDescribeStrategy_whenFieldIsDayOfMonth() {
        CronDescribeStrategy strategy = describeStrategyFactory.getCronDescribeStrategy(CronFieldName.DAY_OF_MONTH);
        Assert.assertTrue(strategy instanceof DescribeDayOfMonthStrategy);
    }

    @Test
    public void testGetCronDescribeStrategy_whenFieldIsDayOfWeek() {
        CronDescribeStrategy strategy = describeStrategyFactory.getCronDescribeStrategy(CronFieldName.DAY_OF_WEEK);
        Assert.assertTrue(strategy instanceof DescribeDayOfWeekStrategy);
    }

}
