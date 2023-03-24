package com.example.demo.repository;

import com.example.demo.base.BaseRepositoryTest;
import com.example.demo.entity.UserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@DisplayName("User repository tests.")
public class UserRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    void createUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("My name");
        userEntity.setSurname("My surname");
        userEntity.setEmail("name@surname.com");

        userRepository.saveAndFlush(userEntity);

        List<UserEntity> userEntities = userRepository.findAll();

        assertEquals(1, userEntities.size());
    }
}
