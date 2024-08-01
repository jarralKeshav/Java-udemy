package com.studyeasy.springBlog.services;

import com.studyeasy.springBlog.models.Post;
import com.studyeasy.springBlog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Optional<Post> getById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Page<Post> findAll(int offset, int pageSize, String field) {
        return postRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.Direction.ASC, field));
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    public void save(Post post) { //did return Post
        if ((post.getId()) == null) {
            post.setCreatedAt(LocalDateTime.now());
        }
        post.setUpdatedAt(LocalDateTime.now());
        postRepository.save(post);
    }

}
