package com.example.myspringboot.repository;

import com.example.myspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 基础的CRUD方法由JpaRepository提供
    // 可以添加自定义的查询方法
}