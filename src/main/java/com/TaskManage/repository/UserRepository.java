package com.TaskManage.repository;

import com.TaskManage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>{

    Optional<User> findByEmail(String email);

}