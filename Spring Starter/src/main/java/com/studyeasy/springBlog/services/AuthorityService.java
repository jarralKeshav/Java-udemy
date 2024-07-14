package com.studyeasy.springBlog.services;

import com.studyeasy.springBlog.models.Authority;
import com.studyeasy.springBlog.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    @Autowired
    private  AuthorityRepository authorityRepository;

//    public AuthorityService(AuthorityRepository authorityRepository) {
//        this.authorityRepository = authorityRepository;
//    }

    public Authority save(Authority authority) {
        return  authorityRepository.save(authority);

    }
}
