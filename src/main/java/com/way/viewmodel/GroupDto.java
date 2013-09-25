package com.way.viewmodel;


import com.way.model.Group;

public class GroupDto {

    private Long id;

    private String name;

    public GroupDto() {
    }

    public GroupDto(Group group) {
        id = group.getId();
        name = group.getName();
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
}
