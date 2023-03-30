package com.example.demo.service;

import com.example.demo.base.BaseServiceTest;
import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StringUtils;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceTest extends BaseServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void create_emailExists() {
        String email = "email@user.com";
        UserDto userOne = this.createUserDto(email);

        userOne = userService.create(userOne);

        assertNotNull(userOne);
        assertNotNull(userOne.getId());

        UserDto userTwo = this.createUserDto(email);

        assertEquals(userOne.getEmail(), userTwo.getEmail(), "Emails must be same to check if the email exists.");

        EntityExistsException thrown = assertThrows(EntityExistsException.class,
                () -> userService.create(userTwo), "Expected javax.persistence.EntityExistsException while registering the 2nd user.");

        assertTrue(StringUtils.hasLength(thrown.getMessage()));
    }

    @Test
    void create_success() {
        UserDto userOne = this.createUserDto();
        userOne = userService.create(userOne);

        assertNotNull(userOne);
        assertNotNull(userOne.getId());
    }

    @Test
    void getById_notFound() {
        UUID randomId = UUID.randomUUID();

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
                () -> userService.getById(randomId), "Expected javax.persistence.EntityNotFound exception");

        assertTrue(StringUtils.hasLength(thrown.getMessage()));
    }

    @Test
    void getById_success() {
        UserDto userDto = this.createUserDto();
        userDto = userService.create(userDto);

        assertNotNull(userDto);

        UUID id = userDto.getId();

        assertNotNull(id);

        UserDto fetchedUserDto = userService.getById(id);

        assertNotNull(fetchedUserDto);
        assertEquals(userDto, fetchedUserDto);
    }

    @Test
    void update_notFound() {
        UUID randomId = UUID.randomUUID();
        UserDto userDto = this.createUserDto();

        EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class,
                () -> userService.update(randomId, userDto), "Expected javax.persistence.EntityNotFound exception");

        assertTrue(StringUtils.hasLength(thrown.getMessage()));
    }

    @Test
    void update_success() {
        UserDto userOne = this.createUserDto();
        userOne = userService.create(userOne);

        UUID userId = userOne.getId();

        assertNotNull(userOne);
        assertNotNull(userId);

        UserDto updatedUserDto = new UserDto.Builder()
                .id(userId)
                .name(userOne.getName())
                .surname("Updated surname")
                .email("new@email.com")
                .build();
        updatedUserDto = userService.update(userId, updatedUserDto);

        assertNotNull(updatedUserDto);
        assertNotEquals(userOne.getSurname(), updatedUserDto.getSurname(), "Surname must not be the old one.");
        assertNotEquals(userOne.getEmail(), updatedUserDto.getEmail(), "Email must not be the old one.");
    }

    @Test
    void getUserRevisionsById_success() {
        TransactionTemplate tx = new TransactionTemplate(platformTransactionManager);
        tx.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);

        UserDto userDto = this.createUserDto();
        UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDto);

        // Stores the new entity.
        tx.execute(status -> userRepository.save(userEntity));

        // Gets the user revision after creation.
        List<UserDto> userRevisions = userService.getUserRevisionsById(userEntity.getId());

        assertNotNull(userRevisions);
        assertEquals(1, userRevisions.size(), "There must be one revision right after record creation.");

        // Updates the user's name.
        userEntity.setName("New name");

        // Stores the updated entity.
        tx.execute(status -> userRepository.save(userEntity));

        // Gets the user revision after updating the entity.
        userRevisions = userService.getUserRevisionsById(userEntity.getId());

        assertNotNull(userRevisions);
        assertEquals(2, userRevisions.size(), "There must be two revisions for both creating and updating the user.");
    }
}
