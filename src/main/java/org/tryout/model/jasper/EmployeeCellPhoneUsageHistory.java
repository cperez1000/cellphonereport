package org.tryout.model.jasper;

import lombok.*;
import org.tryout.model.EmployeeCellPhone;

import java.util.List;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class EmployeeCellPhoneUsageHistory {
    private final EmployeeCellPhone employeeCellPhone;
    private final List<UsageHistoryEntry> usageHistory;
}
