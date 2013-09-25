package com.way.service;

import com.way.model.Group;
import com.way.model.User;

import java.util.List;

public interface GroupService {

    public Group save(Group group);

    List<Group> findAll();

    void delete(Long id);

    Group findOne(Long id);

    List<Group> findAllByUser(User user);
}
