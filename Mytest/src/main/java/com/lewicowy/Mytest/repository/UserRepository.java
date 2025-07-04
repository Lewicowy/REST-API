package com.lewicowy.Mytest.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lewicowy.Mytest.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}