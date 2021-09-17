package org.tryout.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class EmployeeCellPhoneUsageHistory {
    private final EmployeeCellPhone employeeCellPhone;
    private final List<UsageHistoryEntry> usageHistory;
}
