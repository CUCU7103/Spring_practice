package com.example.firstproject.api;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class TestCsv {
    public static void main(String[] args) throws IOException, CsvException {
        String charset = "UTF-8"; // 인코딩 설정하기 이 설정이 아니면 한글이 깨진다.
        String csvFile = "C:/Users/argos/Desktop/소스코드_080354_UP_230907/장별 완성 코드/장별 완성 코드(프로젝트파일)/firstproject2/src/main/resources/test.csv"; // 경로 지정함.
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(csvFile),charset)); // 인코딩 설정에 맞추어 파일변환.
//        String[] line; // csv 파일의 행 정보를 담을 빈 문자열 배열을 생성합니다.
//            while ((line = reader.readNext()) != null) { // 파일을 한 줄씩 읽고 빈 줄은 넘어감, CSVReader.readNext()를 이용해서 파일을 한 줄씩 읽어올 수있다.
//                System.out.println(String.join(",", line)); // line 배열의 각 원소들을 , 로 연결하여 하나의 문자열로 반환합니다.
//            }
        List<String[]> lines = reader.readAll();
        lines.forEach(lineforM -> System.out.println(String.join(",", lineforM)));

    }


}
