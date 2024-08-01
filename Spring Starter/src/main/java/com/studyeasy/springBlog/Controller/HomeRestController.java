package com.studyeasy.springBlog.Controller;

//import ch.qos.logback.core.model.Model;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import com.studyeasy.springBlog.models.Post;
import com.studyeasy.springBlog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/v1")
public class HomeRestController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public List<Post> home(){
        return postService.findAll();
    }
}
