package com.max.board.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) // SpringRunner 라는 스프링 실행자를 사용 (SpringBootTest와 JUnit 사이에 연결자 역할)
@WebMvcTest(controllers = HelloController.class) //
public class HelloControllerTest {

    @Autowired // spring bean 주입
    private MockMvc mockMvc; // web api 테스트시 사용, spring mvc 테스트의 시작점

    @Test
    public void hello_return() throws Exception {
        String  hello = "hello";

        mockMvc.perform(get("/hello")) // Mock mvc를 통해 /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // mvc.perform의 결과와 status 검증, 여기서는 OK(200) 인지 검증 
                .andExpect(content().string("hello")); // 응답 본문의 내용 검증
    }

    @Test
    public void helloDto_return() throws Exception {
        String name = "Test";
        int amount  = 1000;

        mockMvc.perform(get("/hello/dto").param("name", name).param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
