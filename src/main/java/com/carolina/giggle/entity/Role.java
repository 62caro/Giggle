package com.carolina.giggle.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roles;

    @Transient
    @ManyToMany
    private
    Set<User> users;

    public Role() {
    }

    public Role(Long id, String name) {
        this.id = id;
        this.roles = name;
    }

    public String getName() {
        return roles;
    }

    public void setName(String name) {
        this.roles = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
