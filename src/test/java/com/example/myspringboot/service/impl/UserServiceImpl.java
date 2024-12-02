package com.example.myspringboot.service.impl;

import com.example.myspringboot.entity.User;
import com.example.myspringboot.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        // 准备测试数据
        testUser = new User();
        testUser.setId(1L);
        // 设置其他必要的用户属性
    }

    @Test
    void createUser_ShouldReturnSavedUser() {
        // 准备
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // 执行
        User result = userService.createUser(testUser);

        // 验证
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void getUserById_WhenUserExists_ShouldReturnUser() {
        // 准备
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        // 执行
        User result = userService.getUserById(1L);

        // 验证
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        verify(userRepository).findById(1L);
    }

    @Test
    void getUserById_WhenUserNotExists_ShouldThrowException() {
        // 准备
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        // 执行和验证
        assertThrows(RuntimeException.class, () -> {
            userService.getUserById(999L);
        });
        verify(userRepository).findById(999L);
    }

    @Test
    void getAllUsers_ShouldReturnList() {
        // 准备
        List<User> userList = Arrays.asList(testUser);
        when(userRepository.findAll()).thenReturn(userList);

        // 执行
        List<User> result = userService.getAllUsers();

        // 验证
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(userRepository).findAll();
    }

    @Test
    void updateUser_ShouldReturnUpdatedUser() {
        // 准备
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // 执行
        User result = userService.updateUser(testUser);

        // 验证
        assertNotNull(result);
        assertEquals(testUser.getId(), result.getId());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void deleteUser_ShouldCallRepository() {
        // 执行
        userService.deleteUser(1L);

        // 验证
        verify(userRepository).deleteById(1L);
    }
}