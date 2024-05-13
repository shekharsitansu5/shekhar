package com.ss.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ss.lms.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
