package org.tryout.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class UsageHistoryEntry {
    private final LocalDate yearMonth;
    private final Long totalMinutes;
    private final BigDecimal totalData;
    private final Long count;

    @Override
    public String toString() {
        return yearMonth.getMonth() +
                " " + yearMonth.getYear() +
                ", totalMinutes=" + totalMinutes +
                ", totalData=" + totalData;
    }
}
