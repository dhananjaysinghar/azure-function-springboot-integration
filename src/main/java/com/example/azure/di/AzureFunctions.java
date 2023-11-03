package com.example.azure.di;

import com.example.azure.di.model.ResponseModel;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FixedDelayRetry;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AzureFunctions {


    @FunctionName("cronjon")
    @FixedDelayRetry(maxRetryCount = 4, delayInterval = "00:00:10")
    public void execute(@TimerTrigger(name = "keepAliveTrigger", schedule = "*/30 * * * * *") String timerInfo,
                        ExecutionContext context) {

        context.getLogger().info("Starting the scheduler");
    }

    @FunctionName("triggerScheduler")
    public ResponseModel get(
            @HttpTrigger(name = "triggerScheduler", methods =
                    {HttpMethod.GET}, authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage request, ExecutionContext context) {
        context.getLogger().info("Starting the scheduler");
        return ResponseModel.builder().data(Instant.now().toString()).build();
    }
}