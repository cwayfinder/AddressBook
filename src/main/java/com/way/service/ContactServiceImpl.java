package com.way.service;

import com.way.model.Contact;
import com.way.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repository;

    @Override
    public Contact save(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public Page<Contact> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Contact findOne(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Page<Contact> findAllByGroupId(Long projectId, Pageable pageable) {
        return repository.findAllByGroupId(projectId, pageable);
    }

	@Override
	public List<Contact> findAllByGroupId(Long projectId) {
		return repository.findAllByGroupId(projectId);
	}
}
