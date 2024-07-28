package com.studyeasy.springBlog.Controller;

import com.studyeasy.springBlog.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import com.studyeasy.springBlog.models.Account;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.security.Principal;
import java.util.Optional;

@Controller
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "account_views/register";
    }

    @PostMapping("/register")
    public String register_user(@Valid @ModelAttribute Account account, BindingResult result) {
        if(result.hasErrors()) {
            return "account_views/register";
        }
        accountService.save(account);
        return "account_views/login";
    }

    @GetMapping("/login")
    public String login(Model model ) {
    return "account_views/login";
    }


    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model, Principal principal) {
        String authUser = "email";
        if (principal != null) {
            authUser = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findOneByEmail(authUser);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            model.addAttribute("account", account);
            model.addAttribute("photo", account.getPhoto());
            return "account_views/profile";
        }else {
            return "redirect:/?error";
        }
    }

    @GetMapping("/test")
    public String test(Model model ) {
    return "account_views/test";
    }

    //20:27


}
