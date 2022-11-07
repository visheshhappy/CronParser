package org.vishesh.cronjob.model;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class CronExpression {

    private final String cronExpression;

    private final Map<CronFieldName, CronFieldValue> cronFieldMap;


    public String getCronExpression() {
        return cronExpression;
    }

    public Map<CronFieldName, CronFieldValue> getCronFieldMap() {
        return cronFieldMap;
    }
}
