package org.vishesh.cronjob.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.checkerframework.checker.units.qual.C;
import org.vishesh.cronjob.factory.DescribeStrategyFactory;
import org.vishesh.cronjob.service.CronJobService;
import org.vishesh.cronjob.service.CronParser;
import org.vishesh.cronjob.service.CronValidationService;
import org.vishesh.cronjob.service.CronViewService;
import org.vishesh.cronjob.service.impl.CronJobServiceImpl;
import org.vishesh.cronjob.service.impl.CronParserImpl;
import org.vishesh.cronjob.service.impl.CronValidationServiceImpl;
import org.vishesh.cronjob.service.impl.CronViewServiceImpl;
import org.vishesh.cronjob.strategy.CronDescribeStrategy;
import org.vishesh.cronjob.strategy.impl.*;
import org.vishesh.cronjob.validator.CronExpressionValidator;
import org.vishesh.cronjob.validator.impl.*;

import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

public class CronJobConfiguration extends AbstractModule {

    @Provides
    @Singleton
    public CronJobService cronJobService(final CronParser cronParser, final CronViewService cronViewService) {
        return new CronJobServiceImpl(cronParser, cronViewService);
    }

    @Provides
    @Singleton
    public CronParser cronParser(final CronValidationService cronValidationService) {
        return new CronParserImpl(cronValidationService);
    }

    @Provides
    @Singleton
    public CronValidationService cronValidationService() {
        List<CronExpressionValidator> cronExpressionValidatorList = Arrays.asList(
                new MinuteExpressionValidator(CronConfig.MINUTE_RANGE, CronConfig.MINUTE_POSITION),
                new HourExpressionValidator(CronConfig.HOUR_RANGE, CronConfig.HOUR_POSITION),
                new MonthExpressionValidator(CronConfig.MONTH_RANGE, CronConfig.MONTH_POSITION),
                new DayExpressionValidator(CronConfig.DAY_OF_MONTH_RANGE, CronConfig.DAY_OF_MONTH_POSITION),
                new DayOfWeekExpressionValidator(CronConfig.DAY_OF_WEEK_RANGE, CronConfig.DAY_OF_WEEK_POSITION));
        return new CronValidationServiceImpl(cronExpressionValidatorList);
    }

    @Provides
    @Singleton
    public CronViewService cronViewService(final DescribeStrategyFactory describeStrategyFactory) {
        return new CronViewServiceImpl(describeStrategyFactory);
    }

    @Provides
    @Singleton
    public DescribeStrategyFactory describeStrategyFactory(@Named("minuteStrategy") final CronDescribeStrategy minuteStrategy,
                                                           @Named("hourStrategy") final CronDescribeStrategy hourStrategy,
                                                           @Named("monthStrategy") final CronDescribeStrategy monthStrategy,
                                                           @Named("dayOfMonthStrategy") final CronDescribeStrategy dayOfMonthStrategy,
                                                           @Named("dayOfWeekStrategy") final CronDescribeStrategy dayOfWeekStrategy) {
        return new DescribeStrategyFactory(minuteStrategy, hourStrategy, monthStrategy,
                dayOfMonthStrategy, dayOfWeekStrategy);
    }

    @Provides
    @Singleton
    @Named("minuteStrategy")
    public CronDescribeStrategy minuteStrategy() {
        return new DescribeMinuteStrategy();
    }

    @Provides
    @Singleton
    @Named("hourStrategy")
    public CronDescribeStrategy hourStrategy() {
        return new DescribeHourStrategy();
    }

    @Provides
    @Singleton
    @Named("monthStrategy")
    public CronDescribeStrategy monthStrategy() {
        return new DescribeMonthStrategy();
    }

    @Provides
    @Singleton
    @Named("dayOfMonthStrategy")
    public CronDescribeStrategy dayOfMonthStrategy() {
        return new DescribeDayOfMonthStrategy();
    }

    @Provides
    @Singleton
    @Named("dayOfWeekStrategy")
    public CronDescribeStrategy dayOfWeekStrategy() {
        return new DescribeDayOfWeekStrategy();
    }

}
