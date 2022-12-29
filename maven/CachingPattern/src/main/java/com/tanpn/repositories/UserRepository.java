package com.tanpn.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanpn.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  List<UserEntity> findByUsername(String username);
}