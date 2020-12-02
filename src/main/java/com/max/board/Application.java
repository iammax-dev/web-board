package com.max.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// Main Class - @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 항상 프로젝트 최상단에 위치
public class Application {
    public static void main(String[] args) {
        // 내장 WAS 실행
        SpringApplication.run(Application.class, args);
    }


}
