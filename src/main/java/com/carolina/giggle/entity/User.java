package com.carolina.giggle.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private boolean active;

    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @ManyToMany
    private Set<Role> role;

    @OneToMany
    private Set<Post> posts;

    public User() {
    }

    public User(String name, String password, boolean active, Set<Role> role){
        this.name = name;
        this.password = password;
        this.active = active;
        this.role = role;
    }

    public User(Set<Post> posts) {
        this.posts = posts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
