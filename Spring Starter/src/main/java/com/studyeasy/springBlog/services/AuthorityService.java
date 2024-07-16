package com.studyeasy.springBlog.services;

import com.studyeasy.springBlog.models.Authority;
import com.studyeasy.springBlog.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

//    public AuthorityService(AuthorityRepository authorityRepository) {
//        this.authorityRepository = authorityRepository;
//    }

    public Authority save(Authority authority) {
        return authorityRepository.save(authority);

    }

    public  Optional<Authority> findById(Long authorityId) {
        return authorityRepository.findById(authorityId);
    }
}
