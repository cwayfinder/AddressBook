package com.way.service;

import com.way.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public User save(User user);

    Page<User> findAll(Pageable pageable);

    void delete(Long id);

    User findOne(Long id);

    User findOneByEmail(String email);

    User findOneByEmailAndPassword(String email, String password);
}
