package com.studyeasy.springBlog.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Email(message = "Invalid Email")
    @NotBlank(message = "email missing")
    private String email;

    @NotEmpty(message = "Password missing")
    private String password;

    @NotEmpty(message = "Firstname missing")
    private String firstName;

    @NotEmpty(message = "LastName missing")
    private String lastName;

    private String gender;

    @Min(value = 18)
    @Max(value = 120)
    private int age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date_of_birth;

    private String photo;

    private String role;

    @OneToMany(mappedBy = "account")
    private List<Post> posts;

    private String password_reset_token;

    private LocalDateTime password_reset_token_expiry;

    @ManyToMany
    @JoinTable
            (name = "account_authority", joinColumns = {
                    @JoinColumn(name = "account_id", referencedColumnName = "id")},
                    inverseJoinColumns = {
                    @JoinColumn(name = "privilege_id", referencedColumnName = "authorityId")})
    private Set<Authority> authoritySet= new HashSet<>();
}
