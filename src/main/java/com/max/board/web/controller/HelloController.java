package com.max.board.web.controller;

import com.max.board.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON 반환하는 Controller, @ResponseBody 선언 했던 걸 한번에 사용할 수 있게 설정
public class HelloController {

    // get 요청을 받을 수 있는 API 생성 (이전에 @RequestMapping(Method= RequestMethod.GET) 으로 선언해서 사용)
    // /hello 요청이 오면 "hello" 반환함
    @GetMapping("/hello") 
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
                                     // 외부에서 api로 넘긴 파라미터를 가져오는 Annotation
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

}
