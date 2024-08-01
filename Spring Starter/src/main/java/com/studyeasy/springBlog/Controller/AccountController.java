package com.studyeasy.springBlog.Controller;

import com.studyeasy.springBlog.models.Account;
import com.studyeasy.springBlog.security.config.AppProperties;
import com.studyeasy.springBlog.services.AccountService;
import com.studyeasy.springBlog.services.EmailService;
import com.studyeasy.springBlog.utils.constants.AppUtil;
import com.studyeasy.springBlog.utils.constants.email.EmailDetails;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Value;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class AccountController {




    private final AppProperties appProperties;
    private final AccountService accountService;
    private final EmailService emailService;

    public AccountController(AccountService accountService, HttpSession httpSession, AppProperties appProperties, EmailService emailService) {
        this.accountService = accountService;
        this.appProperties = appProperties;
        this.emailService = emailService;
    }



    @GetMapping("/register")
    public String register(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "account_views/register";
    }

    @PostMapping("/register")
    public String register_user(@Valid @ModelAttribute Account account, BindingResult result) {
        if (result.hasErrors()) {
            return "account_views/register";
        }
        accountService.save(account);
        return "account_views/login";
    }

    @GetMapping("/login")
    public String login(Model model) {
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
        } else {
            return "redirect:/?error";
        }
    }

    @GetMapping("/test")
    public String test(Model model) {
        return "account_views/test";
    }

    //20:27

    @PostMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String post_profile(@Valid @ModelAttribute Account account, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "account_views/profile";
        }
        String authUser = "email";
        if (principal != null) {
            authUser = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findOneByEmail(authUser);
        if (optionalAccount.isPresent()) {
            Account account_by_id = accountService.findOneById(account.getId()).get();

            account_by_id.setAge(account.getAge());
            account_by_id.setGender(account.getGender());
            account_by_id.setDate_of_birth(account.getDate_of_birth());
            account_by_id.setFirstName(account.getFirstName());
            account_by_id.setLastName(account.getLastName());
            account_by_id.setPassword(account.getPassword());

            accountService.save(account_by_id);
            SecurityContextHolder.clearContext();

            return "redirect:/logout";
        } else {
            return "redirect:/?error";

        }


    }

    @PostMapping("/update_photo")
    @PreAuthorize("isAuthenticated()")
    public String update_photo(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Principal principal) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Please select a file");
            return "redirect:/profile";
        } else {
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            try {
                int length = 10;
                boolean useLetters = true;
                boolean useNumbers = true;
                String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
                String final_photo_name = generatedString + fileName;
                String absolute_fileLocation = AppUtil.get_upload_path(final_photo_name);

                Path path = Paths.get(absolute_fileLocation);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + final_photo_name);

                String authUser = "email";
                if (principal != null) {
                    authUser = principal.getName();
                }
                Optional<Account> optionalAccount = accountService.findOneByEmail(authUser);
                if (optionalAccount.isPresent()) {
                    Account account = optionalAccount.get();
                    Account account_by_id = accountService.findOneById(account.getId()).get();
                    String relative_fileLocation = "uploads/" + final_photo_name;
                    account_by_id.setPhoto(relative_fileLocation);
                    accountService.save(account_by_id);

                }
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                return "redirect:/profile";

            } catch (Exception e) {

            }


        }
        return "redirect:/?error";

    }

    @GetMapping("/forgot-password")
    public String forgot_password(Model model) {
        return "account_views/forgot_password";
    }

    @PostMapping("/reset-password")
    public String reset_password(@RequestParam("email") String _email, RedirectAttributes redirectAttributes, Model model) {
        Optional<Account> optionalAccount = accountService.findOneByEmail(_email);
        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();   //doubt
            String reset_token = UUID.randomUUID().toString();
            account.setPassword_reset_token(reset_token);
            account.setPassword_reset_token_expiry(LocalDateTime.now().plusMinutes(appProperties.getPassword_reset_token_timeout_minutes()));
            accountService.save(account);

        //.....................................................................


            String reset_message =
                    "This is the reset password link: http://localhost/reset_password?token=" + reset_token;
            EmailDetails emailDetails = new EmailDetails(account.getEmail(), "Reset password for Blog demo",reset_message);
            try {
                emailService.sendSimpleEmail(emailDetails);
                redirectAttributes.addFlashAttribute("message", "Password reset email sent");
            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error", "Error sending email, contact admin");
                return "redirect:/forgot-password";
                // Log the exception details for further debugging
            }
            redirectAttributes.addFlashAttribute("message", "Password reset email sent");


            //.....................................................................



            return "redirect:/login";

        } else {
            redirectAttributes.addFlashAttribute("error", "No user found with the email supplied");
            return "redirect:/forgot-password";
        }

    }
}





















