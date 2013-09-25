package com.way.service;

import com.way.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContactService {

    public Contact save(Contact contact);

    Page<Contact> findAll(Pageable pageable);

    void delete(Long id);

    Contact findOne(Long id);

    Page<Contact> findAllByGroupId(Long groupId, Pageable pageable);

    List<Contact> findAllByGroupId(Long groupId);

    Page<Contact> findAllByGroupIdAndNameLike(Long groupId, String name, Pageable pageable);
}
