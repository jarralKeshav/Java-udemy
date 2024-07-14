package com.studyeasy.springBlog.Controller;

import com.studyeasy.springBlog.services.AccountService;
import org.springframework.ui.Model;
import com.studyeasy.springBlog.models.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
        return "register";
    }

    @PostMapping("/register")
    public String register_user(@ModelAttribute Account account) {
    accountService.save(account);
    return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model ) {
    return "login";
    }

    //20:27


}
