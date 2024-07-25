package com.studyeasy.springBlog.Controller;

//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;
import com.studyeasy.springBlog.models.Post;
import com.studyeasy.springBlog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String home(Model model) {
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "home_views/home";

    }
}
            