package com.bank.depositman.service;

import com.bank.depositman.model.User;
import com.bank.depositman.utils.dto.UserDTO;

public interface UserService {
    User create (UserDTO req);
    User getById (Integer id);
    User updateById (Integer id, UserDTO req);
    void deleteById (Integer id);

}
