package com.ems.empmanagamentapp.service;

import com.ems.empmanagamentapp.repository.AppLogRepository;
import org.springframework.stereotype.Service;
import com.ems.empmanagamentapp.model.AppLog;
import java.time.LocalDateTime;


@Service
public class LogService {
    
    private final AppLogRepository appLogRepository;

    public LogService(AppLogRepository appLogRepository) {
        this.appLogRepository = appLogRepository;
    }

    public void log(String level, String message) {
        AppLog log = AppLog.builder()
                .logMessage(message)
                .logLevel(level)
                .timestamp(LocalDateTime.now())
                .build();
        AppLog saveLog = appLogRepository.save(log);
        System.out.println();
    }
}