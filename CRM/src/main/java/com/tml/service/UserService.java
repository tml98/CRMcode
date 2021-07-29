package com.tml.service;

import com.tml.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUser(String username,String password,String ip);

    User getUserAutoLogin(String username,String password,String ip);

    List<String> getAll();
}
