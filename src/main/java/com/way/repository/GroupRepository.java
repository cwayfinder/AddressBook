package com.way.repository;

import com.way.model.Group;
import com.way.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("groupRepository")
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findAllByUser(User user);
}
