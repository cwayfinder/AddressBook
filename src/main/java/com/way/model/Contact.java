package com.way.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String phone;

    @ManyToOne
    private Group group;

    public Contact() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
