package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.AuditRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.UUID;

@Service
public final class UserService {

    private final UserRepository userRepository;
    private final AuditRepository auditRepository;

    public UserService(final UserRepository userRepository, final AuditRepository auditRepository) {
        this.userRepository = userRepository;
        this.auditRepository = auditRepository;
    }

    public List<UserDto> getUserRevisionsById(final UUID userId) {
        final List<UserEntity> entityRevisions = auditRepository.getRevisions(UserEntity.class, "id", userId);

        return UserMapper.INSTANCE.toListDto(entityRevisions);
    }

    public UserDto create(final UserDto userDto) {
        final String email = userDto.getEmail();
        final boolean exists = userRepository.existsByEmail(email);

        if (exists) {
            throw new EntityExistsException("The provided email address already exists.");
        }

        final UserEntity userEntity = UserMapper.INSTANCE.toEntity(userDto);

        userRepository.save(userEntity);

        return UserMapper.INSTANCE.toDto(userEntity);
    }

    public UserDto update(final UUID userId, final UserDto userDto) {
        final UserEntity userEntity = userRepository.getReferenceById(userId);
        UserMapper.INSTANCE.mapEntity(userDto, userEntity);

        userRepository.save(userEntity);

        return UserMapper.INSTANCE.toDto(userEntity);
    }

    public UserDto getById(final UUID userId) {
        final UserEntity userEntity = userRepository.getReferenceById(userId);

        return UserMapper.INSTANCE.toDto(userEntity);
    }

    public void delete(final UUID userId) {
        userRepository.deleteById(userId);
    }
}
