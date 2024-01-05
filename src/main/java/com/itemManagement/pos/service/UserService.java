package com.itemManagement.pos.service;

import com.itemManagement.pos.dto.UserPwdDTO;
import com.itemManagement.pos.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();
    User createUser(User user);
    User getUserById(Long id);
    User changeUserPassword(Long id, UserPwdDTO userPwdDTO);
}
