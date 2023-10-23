package com.example.firstproject.service;

import com.example.firstproject.entity.NewOne;
import com.example.firstproject.repository.NewOnerepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Slf4j
public class NewOneService {
    @Autowired
    NewOnerepository newOnerepository;
    // 파일 경로 지정


    public List<NewOne> readCsvAndStoreInDb(String csvFilePath) throws IOException, CsvException {

//      FileReader fileReader = new FileReader(csvFilePath);
//      CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build(); // 첫 번째 줄은 헤더라고 가정
        // 파일 읽고 변환
        try{
            CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(csvFilePath),"UTF-8"));
            csvReader.readNext(); // 첫 줄 생략
            List<String[]> records = csvReader.readAll(); // CSVReader로 모든 행을 다 읽어온다. 공백은 알아서 제거함.
//        ArrayList<NewOne> newOneList = new ArrayList<>();

            // setter를 사용해서 각 컬럼에 값을 전달함
            for (String[] record : records) { // foreach 사용
                NewOne newOne = new NewOne();
                newOne.setCode(Long.parseLong(record[0]));
                newOne.setProduct_name(record[1]);
                newOne.setUnit(record[2]);
                newOne.setOutput_price(record[3]);
                newOne.setInput_price(record[4]);
                newOne.setUnit_count(record[5]);
                newOne.setSearch(record[6]);
                newOne.setUseable(record[7]);
                newOne.setReal_price(record[8]);
                newOne.setEtc(record[9]);

//            newOneList.add(newOne);
                newOnerepository.save(newOne); // jparepository의 save 기능 사용
            }

        } catch (IOException e) {
            log.info("파일오류발생 {} ",e);

        }


        return null; // 단순 저장테스트로 return 값 x
    }
}
