package com.example.firstproject.api;

import com.example.firstproject.entity.Nmen;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController // Rest api용 컨트롤러
public class FirstApiController {
    @GetMapping("/api/hello")
    public String test() {
        return "hello world";
    }

    // test csv
//    @RestController
//    public class CsvController {
//        @GetMapping("/read-csv")
//        public List<Nmen> readCsv() throws IOException, CsvValidationException {
//            String csvFile = "C:/Users/argos/Desktop/test.csv";
//            CSVReader reader = new CSVReader(new FileReader(csvFile));
//
//            List<Nmen> nmens = new ArrayList<>();
//
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                Nmen nmen = new Nmen(line[0], line[1]);
//                nmens.add(nmen);
//            }
//
//            return nmens;
//        }
//
//    }

}
