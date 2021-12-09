package com.getir.readingisgood.adapters.postgre.repository;

import com.getir.readingisgood.adapters.postgre.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends BaseRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
