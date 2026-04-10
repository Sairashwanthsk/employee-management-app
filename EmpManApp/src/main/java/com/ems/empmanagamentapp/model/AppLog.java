package com.ems.empmanagamentapp.model;

import java.time.LocalDateTime;

import javax.annotation.processing.Generated;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    private String logMessage;
    private String logLevel;

    private LocalDateTime timestamp;
}