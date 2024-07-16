package com.studyeasy.springBlog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private long id;


    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String role;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    @ManyToMany
    @JoinTable
            (name = "account_authority", joinColumns = {
                    @JoinColumn(name = "account_id", referencedColumnName = "id")},
                    inverseJoinColumns = {
                    @JoinColumn(name = "privilege_id", referencedColumnName = "authorityId")})
    private Set<Authority> authoritySet= new HashSet<>();
}
