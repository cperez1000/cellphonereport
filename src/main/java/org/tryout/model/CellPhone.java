package org.tryout.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
@Table(name = "cell_phones")
public class CellPhone {
    @Id
    private Long employeeId;

    private String employeeName;
    private LocalDate purchaseDate;
    private String model;
}
