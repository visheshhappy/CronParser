package org.vishesh.cronjob.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CommandDto {

    private String cron;
    private String command;

}
