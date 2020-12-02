package com.max.board.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // lombok 으로 선언된 모든 필드의 get 메소드 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성 (final 없으면 생성X)
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
