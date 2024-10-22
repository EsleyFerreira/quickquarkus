package br.com.esley.quickquarkus.service;

import br.com.esley.quickquarkus.entity.UserEntity;
import br.com.esley.quickquarkus.exception.UserNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class UserService {

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.persist();
        return userEntity;
    }

    public List<UserEntity> findAll(Integer page, Integer pageSize) {
        return UserEntity.findAll()
                .page(page, pageSize)
                .list();
    }

    public UserEntity findById(UUID userId) {
        return (UserEntity) UserEntity.findByIdOptional(userId)
                .orElseThrow(UserNotFoundException::new);
    }

    public UserEntity updateUser(UUID userId, UserEntity userEntity) {
        var user = findById(userId);

        user.setUsername(userEntity.getUsername());
        user.persist();

        return user;
    }
}