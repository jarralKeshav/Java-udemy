package com.studyeasy.springBlog.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
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

    @Email(message = "Invalid Email")
    @NotEmpty(message = "email missing")
    private String email;

    @NotEmpty(message = "Password missing")
    private String password;

    @NotEmpty(message = "Fistname missing")
    private String firstName;

    @NotEmpty(message = "LastName missing")
    private String lastName;

    private String gender;

    @Min(value = 18)
    @Max(value = 120)
    private int age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    @NotEmpty(message = "Date of Birth missing")
    private LocalDate date_of_birth;

    private String photo;

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
