package com.example.myspringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestDBController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/db")
    public String testConnection() {
        try {
            // 测试执行一个简单的查询
            jdbcTemplate.queryForObject("SELECT 1", String.class);
            return "数据库连接成功！";
        } catch (Exception e) {
            return "数据库连接失败：" + e.getMessage();
        }
    }
}
