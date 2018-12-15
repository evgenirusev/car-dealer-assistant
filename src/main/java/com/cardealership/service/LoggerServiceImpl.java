package com.cardealership.service;

import com.cardealership.comparators.LoggerComparatorByDate;
import com.cardealership.domain.entity.Log;
import com.cardealership.domain.model.service.logs.LogServiceModel;
import com.cardealership.repository.LoggerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LoggerServiceImpl implements LoggerService {

    private final LoggerRepository loggerRepository;

    private final ModelMapper modelMapper;

    public LoggerServiceImpl(LoggerRepository loggerRepository, ModelMapper modelMapper) {
        this.loggerRepository = loggerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<LogServiceModel> findAllLogs() {
        List<Log> logs = this.loggerRepository.findAll();

        Set<LogServiceModel> logDtos = new LinkedHashSet<>();
        for (Log log : logs) {
            logDtos.add(this.modelMapper.map(log, LogServiceModel.class));
        }

        Set<LogServiceModel> sortedLogs = logDtos.stream().sorted(new LoggerComparatorByDate()).collect(Collectors.toCollection(LinkedHashSet::new));

        return sortedLogs;
    }

    @Override
    public void create(LogServiceModel logServiceModel) {
        Log log = this.modelMapper.map(logServiceModel, Log.class);
        this.loggerRepository.save(log);
    }

    @Override
    public void deleteAll() {
        this.loggerRepository.deleteAll();
    }
}
