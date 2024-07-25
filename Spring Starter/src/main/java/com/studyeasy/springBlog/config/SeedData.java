package com.studyeasy.springBlog.config;

import com.studyeasy.springBlog.models.Account;
import com.studyeasy.springBlog.models.Authority;
import com.studyeasy.springBlog.models.Post;
import com.studyeasy.springBlog.services.AccountService;
import com.studyeasy.springBlog.services.AuthorityService;
import com.studyeasy.springBlog.services.PostService;
import com.studyeasy.springBlog.utils.constants.Privileges;
import com.studyeasy.springBlog.utils.constants.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {

        for (Privileges _privilege : Privileges.values()) {
            Authority authority = new Authority();
            authority.setAuthorityId(_privilege.getPrivilegeId());
            authority.setAuthorityName(_privilege.getPrivilegeName());
            authorityService.save(authority);
        }

        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();
        Account account04 = new Account();

        account01.setEmail("account01@outlook.com");
        account01.setPassword("password");
        account01.setFirstName("Account01");
        account01.setLastName("lastname01");

        account02.setEmail("super_editor@editor.com");
        account02.setPassword("superEditor123");
        account02.setFirstName("Super");
        account02.setLastName("Editor");
        account02.setRole(Roles.SUPER_EDITOR.getRole());
        Set<Authority> authoritySet = new HashSet<>();
        authorityService.findById(Privileges.RESET_ANY_USER_PASSWORD.getPrivilegeId()).ifPresent(authoritySet::add);
        authorityService.findById(Privileges.ACCESS_ADMIN_PANEL.getPrivilegeId()).ifPresent(authoritySet::add);
        account02.setAuthoritySet(authoritySet);

        account03.setEmail("admin@admin.com");
        account03.setPassword("admin123");
        account03.setFirstName("Admin");
        account03.setLastName("AdminLastName");
        account03.setRole(Roles.ADMIN.getRole());

        account04.setEmail("editor@editor.com");
        account04.setPassword("editor123");
        account04.setFirstName("Editor");
        account04.setLastName("EditorLastName");
        account04.setRole(Roles.EDITOR.getRole());

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);

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
