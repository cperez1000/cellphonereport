package org.tryout.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.tryout.model.CellPhone;
import org.tryout.model.CellPhoneUsagePerDay;
import org.tryout.model.CellPhoneUsagePerMonth;
import org.tryout.repository.CellPhoneUsageRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CellPhoneUsageService implements BaseService<CellPhoneUsagePerDay> {

    private CellPhoneUsageRepository repository;

    @Autowired
    public CellPhoneUsageService(CellPhoneUsageRepository repository) {
        this.repository = repository;
    }

    @Override
    public CellPhoneUsageRepository getRepository() {
        return repository;
    }

    public long getTotalMinutes() {
        return repository.getTotalMinutes();
    }

    public BigDecimal getTotalData() {
        return repository.getTotalData();
    }

    public BigDecimal getAverageMinutes() {
        return repository.getAverageMinutes();
    }

    public BigDecimal getAverageData() {
        return repository.getAverageData();
    }

    public List<CellPhoneUsagePerMonth> listCellPhoneUsagePerMonth() {
        return Streamable.of(repository.listCellPhoneUsagePerMonth()).stream()
                .collect(Collectors.toList());
    }
}
