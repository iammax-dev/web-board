package com.max.board.web.dto;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class HelloResponseDtoTest {

    @Test
    public void lombokTest() {

        // given
        String name = "Test";
        int amount  = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        assertThat(dto.getName()).isEqualTo(name); // assertj의 검증 메소드
        assertThat(dto.getClass()).isEqualTo(amount);
    }
}
