package org.tryout.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class CellPhoneUsagePerMonth {
    private final EmployeeCellPhone employeeCellPhone;
    private final Integer year;
    private final Integer month;
    private final Long totalMinutes;
    private final BigDecimal totalData;
    private final Long count;
}
