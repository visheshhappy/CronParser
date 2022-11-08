package org.vishesh.cronjob.config;

import java.time.temporal.ValueRange;
import java.util.Set;

public class CronConfig {

    public final static int VALID_CRON_LENGTH = 5;
    public final static String CRON_SEPARATOR = " ";

    public final static String CRON_WILDCARD = "*";

    public final static String CRON_INTERVAL_CHARACTER = "/";

    public final static String CRON_RANGE_CHARACTER = "-";

    public final static String CRON_COMMA_CHARACTER = ",";
    public final static Set<String> validMinutesCharacter = Set.of("/", "-", ",");

    public static final int MINUTE_POSITION = 0;
    public static final int HOUR_POSITION = 1;
    public static final int DAY_OF_MONTH_POSITION = 2;
    public static final int MONTH_POSITION = 3;
    public static final int DAY_OF_WEEK_POSITION = 4;

    public static final ValueRange MINUTE_RANGE = ValueRange.of(0, 59);
    public static final ValueRange HOUR_RANGE = ValueRange.of(0, 23);
    public static final ValueRange MONTH_RANGE = ValueRange.of(1, 12);
    public static final ValueRange DAY_OF_MONTH_RANGE = ValueRange.of(1, 31);
    public static final ValueRange DAY_OF_WEEK_RANGE = ValueRange.of(1, 7);

    public static final int TOTAL_TITLE_SPACE = 14;
}
