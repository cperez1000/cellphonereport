package org.tryout.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class CellPhoneUsagePerMonth {
    private CellPhone cellPhone;
    private Integer year;
    private Integer month;
    private Long totalMinutes;
    private BigDecimal totalData;
    private Long count;
}
