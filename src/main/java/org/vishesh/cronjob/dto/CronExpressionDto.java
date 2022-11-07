package org.vishesh.cronjob.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CronExpressionDto {
    private String[] cronExpressions;
}
