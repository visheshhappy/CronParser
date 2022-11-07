package org.vishesh.cronjob.service;

import org.checkerframework.checker.units.qual.C;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.vishesh.cronjob.dto.CommandDto;
import org.vishesh.cronjob.dto.CronViewDto;
import org.vishesh.cronjob.exception.InvalidExpressionException;
import org.vishesh.cronjob.model.CronExpression;
import org.vishesh.cronjob.model.CronFieldName;
import org.vishesh.cronjob.model.CronFieldValue;
import org.vishesh.cronjob.service.impl.CronJobServiceImpl;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class CronJobServiceTest {

    @Mock
    private  CronParser cronParser;

    @Mock
    private  CronViewService cronViewService;

    @InjectMocks
    private CronJobServiceImpl cronJobService ;

    @Test
    public void testCreateView_WhenParserThrowsError(){
        doThrow(InvalidExpressionException.class).when(cronParser).parse(anyString());
        Assertions.assertThrows(InvalidExpressionException.class, ()->cronJobService.createCronView(new CommandDto("","")));
    }
    @Test
    public void testCreateView_WhenParserReturnValidCronExpression(){
        CommandDto commandDto = new CommandDto("*/15 0 1,15 * 1-5","/ur/bin/find");
        CronExpression cronExpression = new CronExpression(commandDto.getCron(), Map.of(CronFieldName.MINUTE,
                new CronFieldValue(CronFieldName.MINUTE, "*/15", CronFieldValue.ExpressionType.INTERVAL),
                CronFieldName.HOUR, new CronFieldValue(CronFieldName.HOUR, "0", CronFieldValue.ExpressionType.SPECIFIC),
                CronFieldName.DAY_OF_MONTH, new CronFieldValue(CronFieldName.DAY_OF_MONTH,"1,15", CronFieldValue.ExpressionType.SPECIFIC),
                CronFieldName.MONTH, new CronFieldValue(CronFieldName.MONTH, "*", CronFieldValue.ExpressionType.ALWAYS),
                CronFieldName.DAY_OF_WEEK, new CronFieldValue(CronFieldName.DAY_OF_WEEK, "1-5", CronFieldValue.ExpressionType.RANGE)));
        doReturn(cronExpression).when(cronParser).parse(commandDto.getCron());
        CronViewDto cronViewDto = Assertions.assertDoesNotThrow( ()->cronJobService.createCronView(commandDto));
        Assertions.assertNotNull(cronViewDto.getView());
    }





}
