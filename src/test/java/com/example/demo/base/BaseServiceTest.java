package com.example.demo.base;

import com.example.demo.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;

@DataJpaTest
@Import({ObjectMapper.class})
@ComponentScan(basePackages = {"com.example.demo.service"})
public abstract class BaseServiceTest extends BaseRepositoryTest {

    protected UserDto createUserDto(String email) {
        if (StringUtils.hasLength(email)) {

            return new UserDto.Builder()
                    .name("Lorem")
                    .surname("Ipsum")
                    .email(email)
                    .build();
        }

        return this.createUserDto();
    }

    protected UserDto createUserDto() {
        return new UserDto.Builder()
                .name("Lorem")
                .surname("Ipsum")
                .email("lorem@ipsum.com")
                .build();
    }
}
