package org.tryout.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.tryout.model.*;
import org.tryout.repository.CellPhoneUsageRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
        return repository.getTotalData().setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAverageMinutes() {
        return repository.getAverageMinutes().setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getAverageData() {
        return repository.getAverageData().setScale(2, RoundingMode.HALF_UP);
    }

    public List<CellPhoneUsagePerMonth> listCellPhoneUsagePerMonth() {
        return Streamable.of(repository.listCellPhoneUsagePerMonth()).stream()
                .collect(Collectors.toList());
    }

    public Map<EmployeeCellPhone, Map<LocalDate, List<CellPhoneUsagePerMonth>>> listCellPhoneUsageByEmployee() {
        return listCellPhoneUsagePerMonth().stream()
                .collect(Collectors.groupingBy(CellPhoneUsagePerMonth::getEmployeeCellPhone,
                        Collectors.groupingBy(this::yearMonth)));
    }

    public List<EmployeeCellPhoneUsageHistory> listCellPhoneUsageHistory() {
        return listCellPhoneUsageByEmployee().entrySet().stream()
                .map(entry -> new EmployeeCellPhoneUsageHistory(entry.getKey(), toUsageHistory(entry.getValue())))
                .collect(Collectors.toList());

    }

    private List<UsageHistoryEntry> toUsageHistory(Map<LocalDate, List<CellPhoneUsagePerMonth>> usageMap) {
        return usageMap.values().stream()
                .flatMap(Collection::stream)
                .map(this::toUsageHistoryEntry)
                .sorted(Comparator.comparing(UsageHistoryEntry::getYearMonth))
                .collect(Collectors.toList());

    }


    private UsageHistoryEntry toUsageHistoryEntry(CellPhoneUsagePerMonth cellPhoneUsagePerMonth) {
        return new UsageHistoryEntry(yearMonth(cellPhoneUsagePerMonth), cellPhoneUsagePerMonth.getTotalMinutes(), cellPhoneUsagePerMonth.getTotalData(), cellPhoneUsagePerMonth.getCount());
    }


    private LocalDate yearMonth(CellPhoneUsagePerMonth cellPhoneUsagePerMonth) {
        return LocalDate.of(cellPhoneUsagePerMonth.getYear(), cellPhoneUsagePerMonth.getMonth(), 1);

    }
}
