package com.bank.depositman.service.implementation;

import com.bank.depositman.model.Transaction;
import com.bank.depositman.model.User;
import com.bank.depositman.repository.TransactionRepository;
import com.bank.depositman.repository.UserRepository;
import com.bank.depositman.service.UserService;
import com.bank.depositman.utils.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
    @Override
    public User create(UserDTO req) {
        User user = User.builder()
                .username(req.getUsername())
                .password(req.getPassword())
                .accountBalanced(0)
                .build();
        userRepository.save(user);
        return user;
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException()
        );
    }

    //No use on controller because not suitable with business requirement
    @Override
    public User updateById(Integer id, UserDTO req) {
        User user= getById(id);

        if (req.getUsername() != null){
            user.setUsername(req.getUsername());
        }
        if (req.getPassword() != null){
            user.setPassword(req.getPassword());
        }

        userRepository.save(user);

        return user;
    }

    //No use on controller because not suitable with business requirement
    @Override
    public void deleteById(Integer id) {
        getById(id);
        userRepository.deleteById(id);
    }


}
