package com.jojoldu.com.freelecspringboot2webservice.web.dto;
import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Test;

public class HelloResponseDtoTest {

    @Test
    public void 롬북_기능_테스트() {

        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name, amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

    }
}
