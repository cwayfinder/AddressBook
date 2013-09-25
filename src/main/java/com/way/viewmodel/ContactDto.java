package com.way.viewmodel;


import com.way.model.Contact;

public class ContactDto {

    private Long id;

    private String name;
    private String email;
    private String phone;

    private Long projectId;

    public ContactDto() {
    }

    public ContactDto(Contact contact) {
        id = contact.getId();
        name = contact.getName();
        email = contact.getEmail();
        phone = contact.getPhone();
        projectId = contact.getGroup().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return projectId;
    }

    public void setGroupId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
