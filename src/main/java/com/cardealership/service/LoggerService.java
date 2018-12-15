package com.cardealership.service;

import com.cardealership.domain.model.service.logs.LogServiceModel;

import java.util.Set;

public interface LoggerService {

    Set<LogServiceModel> findAllLogs();

    void create(LogServiceModel logServiceModel);

    void deleteAll();
}
