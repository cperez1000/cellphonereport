package org.tryout;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.tryout.service.jasper.SimpleReportExporter;
import org.tryout.service.jasper.SimpleReportFiller;
import org.tryout.service.CellPhoneService;
import org.tryout.service.CellPhoneUsageService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(
            CellPhoneService cellPhoneService,
            CellPhoneUsageService cellPhoneUsageService,
            SimpleReportFiller simpleReportFiller,
            SimpleReportExporter simpleReportExporter
    ) {
        return (args) -> {

            simpleReportFiller.setReportFileName("CellPhoneReport.jrxml");
            simpleReportFiller.compileReport();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("runDate", LocalDate.now());
            parameters.put("numberPhones", cellPhoneService.getCount());
            parameters.put("totalMinutes", cellPhoneUsageService.getTotalMinutes());
            parameters.put("totalData", cellPhoneUsageService.getTotalData());
            parameters.put("averageMinutes", cellPhoneUsageService.getAverageMinutes());
            parameters.put("averageData", cellPhoneUsageService.getAverageData());

            simpleReportFiller.setParameters(parameters);
            simpleReportFiller.setBeansCollection(cellPhoneUsageService.listCellPhoneUsageHistory());
            simpleReportFiller.fillReport();

            simpleReportExporter.setJasperPrint(simpleReportFiller.getJasperPrint());

            simpleReportExporter.exportToPdf("CellPhoneReport.pdf", "Carlos Perez");
            simpleReportExporter.sendToPrinter();

        };
    }


}
