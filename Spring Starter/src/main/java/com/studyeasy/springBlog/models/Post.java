package com.studyeasy.springBlog.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Missing Post title")
    private String title;

    @Column(columnDefinition = "TEXT")
    @NotBlank(message = "Missing Post body")
    private String body;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = true)
    private Account account;
}
