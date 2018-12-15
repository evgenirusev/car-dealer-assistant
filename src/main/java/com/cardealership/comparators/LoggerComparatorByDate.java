package com.cardealership.comparators;

import com.cardealership.domain.model.service.logs.LogServiceModel;

import java.util.Comparator;

public class LoggerComparatorByDate implements Comparator<LogServiceModel> {
    @Override
    public int compare(LogServiceModel log1, LogServiceModel log2) {
        return log1.getModifyingDate().compareTo(log2.getModifyingDate());
    }
}
