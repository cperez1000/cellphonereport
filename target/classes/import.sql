INSERT INTO cell_phones(employee_id,employee_name,purchase_date, model) SELECT CONVERT(employeeId, INT),employeename, CONVERT(purchasedate, DATE), model FROM CSVREAD('classpath:/CellPhone.csv', NULL);

create table cell_phone_usage_by_month_back AS SELECT * FROM CSVREAD('classpath:/CellPhoneUsageByMonth.csv', NULL);
alter table cell_phone_usage_by_month_back ADD id INT PRIMARY KEY AUTO_INCREMENT;
INSERT INTO cell_phone_usage_by_month(id, employee_id, date, total_minutes, total_data) SELECT id, employeeid, PARSEDATETIME(date, 'M/d/yyyy'), totalminutes, totaldata from cell_phone_usage_by_month_back;
DROP TABLE cell_phone_usage_by_month_back;



