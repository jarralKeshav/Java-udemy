package com.studyeasy.springBlog.Controller;
import com.studyeasy.springBlog.models.Account;
import com.studyeasy.springBlog.models.Post;
import com.studyeasy.springBlog.services.AccountService;
import com.studyeasy.springBlog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class PostController {

    private final PostService postService;
    private final AccountService accountService;

    public PostController(PostService postService, AccountService accountService) {
        this.postService = postService;
        this.accountService = accountService;
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable Long id, Model model, Principal principal){
        Optional<Post> optionalPost = postService.getById(id);
        String authUser= "email";
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            if(principal!=null){
                authUser = principal.getName();
            }
            if(authUser.equals(post.getAccount().getEmail())){
                model.addAttribute("isOwner", true);
            }else{
                model.addAttribute("isOwner", false);
            }

            return "post_views/post";
        }else {

            return "404";
        }
    }

    @GetMapping("/posts/add")
    public String addPost(Model model, Principal principal){
        String authUser= "email";
        if (principal!=null){
            authUser = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findOneByEmail(authUser);
        if(optionalAccount.isPresent()){
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "post_views/post_add";
        }else {
            return "redirect:/";
        }



    }

    @PostMapping("/posts/add")
    @PreAuthorize("isAuthenticated()")
    public String addPostHandler(@Valid @ModelAttribute Post post, BindingResult bindingResult, Principal principal){

        if(bindingResult.hasErrors()) {
            return "post_views/post_add";
        }
        String authUser= "email";
        if (principal!=null){
            authUser = principal.getName();
        }
        if(post.getAccount().getEmail().compareToIgnoreCase(authUser) <0){
        return "redirect:/?error";
        }
            postService.save(post);
        return "redirect:/";
    }

    @GetMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String getPostForEdit(@PathVariable Long id, Model model){
        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post_views/post_edit";
        }else {
            return "404";
        }
    }

    @PostMapping("/posts/{id}/edit")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@Valid @ModelAttribute Post post,BindingResult bindingResult, @PathVariable Long id ){

        if(bindingResult.hasErrors()) {
            return "post_views/post_edit";
        }

        Optional<Post> optionalPost = postService.getById(id);
        if(optionalPost.isPresent()){
            Post existingPost = optionalPost.get();
            existingPost.setTitle(post.getTitle());
            existingPost.setBody(post.getBody());
            postService.save(existingPost);
        }
        return "redirect:/";
     }

    @GetMapping("/posts/{id}/delete")
    @PreAuthorize("isAuthenticated()")
    public String deletePost(@PathVariable Long id){
        Optional<Post> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            postService.delete(post);
            return "redirect:/";
        }else {
            return "404";
        }
    }


}
