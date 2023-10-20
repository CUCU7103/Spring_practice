package com.example.firstproject.api;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;



public class TestCsv {
 /*   public static void main(String[] args) throws IOException, CsvException {

        String charset = "UTF-8"; // 인코딩 설정하기 이 설정이 아니면 한글이 깨진다.
//        String csvFile = "C:/Users/argos/Desktop/소스코드_080354_UP_230907/장별 완성 코드/장별 완성 코드(프로젝트파일)/firstproject2/src/main/resources/test.csv"; // 경로 지정함.
        String csvFile =  "src/main/resources/품목1.csv";
//        String csvFile = "C:/git_clone/src/main/resources/test.csv"; // 경로 지정함.
            // CSVReader 사용함
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(csvFile),charset)); // 인코딩 설정에 맞추어 파일변환.
        reader.readNext(); // 먼저 한 줄을 읽어서 컬럼명이 읽히지 않도록 함.
        //
        String[] line; // csv 파일의 행 정보를 담을 빈 문자열 배열을 생성합니다.
            while ((line = reader.readNext()) != null) { // 파일을 한 줄씩 읽고 빈 줄은 넘어감, CSVReader.readNext()를 이용해서 파일을 한 줄씩 읽어올 수있다.
                System.out.println(String.join(",", line)); // line 배열의 각 원소들을 , 로 연결하여 하나의 문자열로 반환합니다.
            }
//        List<String[]> lines = reader.readAll(); // List안에 있는 값들을 전부 읽어온다.
//        lines.forEach(lineforM -> System.out.println(String.join(",", lineforM))); // 람다식 사용

    }


}*/
// 일반 클래스를 생성했으므로
    public static void main(String[] args) {
        String charset = "UTF-8"; // 인코딩 설정
        String csvFile = "src/main/resources/품목12.csv"; // 파일경로
        // 전역변수로 사용하기 위함.
        CSVReader reader = null;
        // 예외처리 진행함.
        try {
            // CSVReader로 csv파일 읽는다
            reader = new CSVReader(new InputStreamReader(new FileInputStream(csvFile), charset));
            reader.readNext(); // 먼저 한줄을 읽어 컬럼명이 읽히지 않도록함.

            String[] line; // 행 정보를 담을 배열 생성
            // 행이 없을 때까지 반복을 진행함.
            while ((line = reader.readNext()) != null) {
                // 값을 출력하기 위해서 String.join을 통해 line[] 요소들을 하나의 문자열로 반환함.
                System.out.println(String.join(",", line));

            }
        } catch (IOException e) {
            System.err.println("파일을 읽는 동안 오류가 발생함.");
        } catch (CsvException e) {
            System.err.println("CSV 처리 중에 오류가 발생.");
        } finally {
            if (reader != null) {
                try {
                    reader.close(); // 파일을 반드시 닫아준다.
                } catch(IOException e) {
                    System.err.println("파일 닫기 중에 오류가 발생하였습니다.");
                }
            }
        }
    }

    }

