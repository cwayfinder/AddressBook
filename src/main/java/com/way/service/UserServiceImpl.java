package com.way.service;

import com.way.model.User;
import com.way.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User task) {
        return repository.save(task);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public User findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public User findOneByEmail(String email) {
        return repository.findByEmail(email);
//        return new User();
    }

    @Override
    public User findOneByEmailAndPassword(String email, String password) {
        return repository.findOneByEmailAndPassword(email, password);
    }
}
