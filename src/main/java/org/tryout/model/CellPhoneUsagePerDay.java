package org.tryout.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
@Table(name = "cell_phone_usage_by_month")
public class CellPhoneUsagePerDay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "employeeId",
            referencedColumnName = "employeeId"
    )
    private EmployeeCellPhone employeeCellPhone;

    private LocalDate date;
    private Long totalMinutes;
    private BigDecimal totalData;
}
