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

// Annotation for a JPA test that focuses only on JPA components.
@DataJpaTest
// Annotation used to declare a custom display name for the annotated test class or test method.
@DisplayName("User repository tests.")
class UserRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void createUser() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("My name");
        userEntity.setSurname("My surname");
        userEntity.setEmail("name@surname.com");

        // Saves & flushes the entity instantly.
        userRepository.saveAndFlush(userEntity);

        // Returns all entities to check the actual result with the expected.
        List<UserEntity> userEntities = userRepository.findAll();

        // Checks if the table has only one record, which we have just created.
        assertEquals(1, userEntities.size());
    }
}
