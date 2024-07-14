package com.studyeasy.springBlog.config;

import com.studyeasy.springBlog.models.Account;
import com.studyeasy.springBlog.models.Authority;
import com.studyeasy.springBlog.models.Post;
import com.studyeasy.springBlog.services.AccountService;
import com.studyeasy.springBlog.services.AuthorityService;
import com.studyeasy.springBlog.services.PostService;
import com.studyeasy.springBlog.utils.constants.Priviledges;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private  PostService postService;

    @Autowired
    private  AccountService accountService;

    @Autowired
    private  AuthorityService authorityService;

//    public SeedData(PostService postService, AccountService accountService, AuthorityService authorityService) {
//        this.postService = postService;
//        this.accountService = accountService;
//        this.authorityService = authorityService;
//    }

    @Override
    public void run(String... args) throws Exception {

        for(Priviledges priviledge : Priviledges.values()) {
            Authority authority = new Authority();
            authority.setAuthorityId(priviledge.getPriviledgeId());
            authority.setAuthorityName(priviledge.getPriviledgeName());
            authorityService.save(authority);
        }

        Account account01 = new Account();
        Account account02 = new Account();

        account01.setEmail("account01@outlook.com");
        account01.setPassword("password");
        account01.setFirstName("Account01");
        account01.setLastName("lastname01");


        account02.setEmail("account02@outlook.com");
        account02.setPassword("password");
        account02.setFirstName("Account02");
        account02.setLastName("lastname02");

        accountService.save(account01);
        accountService.save(account02);


        List<Post> posts = postService.getAll();
        if (posts.isEmpty()) {
            Post post1 = new Post();
            post1.setTitle("Post 1");
            post1.setBody("This is a post body............");
            post1.setAccount(account01);
            postService.save(post1);

            Post post2 = new Post();
            post2.setTitle("Post 2");
            post2.setBody("This is a post body............");
            post2.setAccount(account02);
            postService.save(post2);


        }
    }
}
