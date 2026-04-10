package com.ems.empmanagamentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ems.empmanagamentapp.model.AppLog;

public interface AppLogRepository extends JpaRepository<AppLog, Integer> {
    // Custom query methods can be defined here if needed
}