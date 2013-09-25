package com.way.service;

import com.way.model.Group;
import com.way.model.User;
import com.way.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupRepository repository;

    @Override
    public Group save(Group group) {
        return repository.save(group);
    }

    @Override
    public List<Group> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Group findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Group> findAllByUser(User user) {
        return repository.findAllByUser(user);
    }
}
