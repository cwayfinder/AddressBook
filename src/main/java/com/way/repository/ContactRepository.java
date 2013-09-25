package com.way.repository;

import com.way.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findAllByGroupId(Long groupId, Pageable pageable);

	List<Contact> findAllByGroupId(Long groupId);
}
