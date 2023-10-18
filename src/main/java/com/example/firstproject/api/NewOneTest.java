package com.example.firstproject.api;

import com.example.firstproject.entity.NewOne;
import com.example.firstproject.service.NewOneService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/newone") // 공통적인 url을 사용할 주로 설정해준다.
public class NewOneTest {
    @Autowired
    NewOneService newOneService;

    @GetMapping("/import") // test를 목적으로하여 파일 경로를 직접적으로 url에 입력함.
                            // 거의 사용하지 않는 방법이다.
    public ResponseEntity<?> importFromCsv(@RequestParam("path") String filePath) throws IOException, CsvException {
        // ResponseEntity<?>에서의 "?"는 와일드 카드로 어떠한 형태의 값이 들어와도 받겠다는 의미이다.
        // 테스트 목적외 에는 잘 사용되어지지 않는다.
        newOneService.readCsvAndStoreInDb(filePath); // 파일경로를 argument로 넘긴다.
        return ResponseEntity.status(HttpStatus.OK).body("Data imported from CSV and saved in DB"); // 성공하면 body의 메시지를 출력한다.

//        return new ResponseEntity<>("Data imported from CSV and saved in DB", HttpStatus.OK);
    }

}
