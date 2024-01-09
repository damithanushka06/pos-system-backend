package com.itemManagement.pos.service_impl.user;
import com.itemManagement.pos.dto.auth.UserPwdDTO;
import com.itemManagement.pos.entity.user.User;
import com.itemManagement.pos.repository.user.UserRepository;
import com.itemManagement.pos.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User changeUserPassword(Long id, UserPwdDTO userPwdDTO) {
        User user = userRepository.findById(id).orElse(null);

        System.out.println(userPwdDTO.getPassword()); //debugging

        if(user != null) {
            user.setPassword(userPwdDTO.getPassword());
            return userRepository.save(user);
        } else {
            return null;
        }
    }

}
