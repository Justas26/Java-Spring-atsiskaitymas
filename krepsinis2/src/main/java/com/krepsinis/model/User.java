package com.krepsinis.model;

import javax.persistence.*;
import java.util.Set;
import javax.persistence.Table;
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;




    @Transient
    @Column(name = "passwordConfirm")
    private String passwordConfirm;

    @ManyToMany
    @JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns =@JoinColumn(name="role_id"))
    private Set<Role> roles;

    public User(int id, String username, String password, String passwordConfirm, Set<Role> roles,String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
        this.email=email;
    }
    public User( String username, String password, String passwordConfirm, Set<Role> roles,String email) {
        this.username = username;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.roles = roles;
        this.email=email;
    }
    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }
    public Set<Role> getRoles() {
        return roles;
    }
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
