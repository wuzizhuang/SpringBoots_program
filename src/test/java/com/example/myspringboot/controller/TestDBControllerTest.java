package com.example.myspringboot.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TestDBControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    void testConnection_WhenSuccess_ShouldReturnSuccessMessage() throws Exception {
        // 准备
        when(jdbcTemplate.queryForObject(anyString(), String.class)).thenReturn("1");

        // 执行和验证
        mockMvc.perform(get("/test/db"))
                .andExpect(status().isOk())
                .andExpect(content().string("数据库连接成功！"));
    }

    @Test
    void testConnection_WhenFail_ShouldReturnErrorMessage() throws Exception {
        // 准备
        when(jdbcTemplate.queryForObject(anyString(), String.class))
                .thenThrow(new RuntimeException("Connection failed"));

        // 执行和验证
        mockMvc.perform(get("/test/db"))
                .andExpect(status().isOk())
                .andExpect(content().string("数据库连接失败：Connection failed"));
    }
}