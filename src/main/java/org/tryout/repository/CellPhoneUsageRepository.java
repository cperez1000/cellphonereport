package org.tryout.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.tryout.model.CellPhoneUsagePerDay;
import org.tryout.model.CellPhoneUsagePerMonth;

import java.math.BigDecimal;

@Repository
public interface CellPhoneUsageRepository extends CrudRepository<CellPhoneUsagePerDay, Long> {
    @Query("SELECT SUM(totalMinutes) FROM CellPhoneUsagePerDay")
    long getTotalMinutes();

    @Query("SELECT SUM(totalData) FROM CellPhoneUsagePerDay")
    BigDecimal getTotalData();

    @Query("SELECT AVG(totalMinutes) FROM CellPhoneUsagePerDay")
    BigDecimal getAverageMinutes();

    @Query("SELECT AVG(totalData) FROM CellPhoneUsagePerDay")
    BigDecimal getAverageData();

    @Query("select new org.tryout.model.CellPhoneUsagePerMonth(" +
            "c.employeeCellPhone, " +
            "extract(YEAR from c.date) AS year, " +
            "extract(MONTH from c.date) AS month, " +
            "SUM(c.totalMinutes), " +
            "SUM(c.totalData), " +
            "count(id)) " +
            "FROM CellPhoneUsagePerDay c " +
            "group by c.employeeCellPhone, year, month"
    )
    Iterable<CellPhoneUsagePerMonth> listCellPhoneUsagePerMonth();


}