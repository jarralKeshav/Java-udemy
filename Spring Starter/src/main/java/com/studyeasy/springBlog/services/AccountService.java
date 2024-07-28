package com.studyeasy.springBlog.services;
import com.studyeasy.springBlog.models.Account;
import com.studyeasy.springBlog.models.Authority;
import com.studyeasy.springBlog.repositories.AccountRepository;
import com.studyeasy.springBlog.security.config.AppProperties;
import com.studyeasy.springBlog.utils.constants.Roles;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {


//        private static final String PHOTO_PATH = "/resources/static/images/imageProfile.png";

    private  final AppProperties appProperties;
    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    public AccountService(AppProperties appProperties, AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.appProperties = appProperties;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public void save(Account account) {             //Account instead of void w.r.t. course
        account.setPassword(passwordEncoder.encode(account.getPassword()));
       if(account.getRole()==null){
           account.setRole(Roles.USER.getRole());
           }
       if(account.getPhoto()==null){
        String path = appProperties.getPhotoPrefix() + "imageProfile1.png";

        account.setPhoto(path);
           }

        accountRepository.save(account);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByEmail(email);
        if (accountOptional.isEmpty()) {
            throw new UsernameNotFoundException("Account not found");
        }
        Account account = accountOptional.get();
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority(account.getRole()));

        for (Authority _auth: account.getAuthoritySet()){
            grantedAuthorityList.add(new SimpleGrantedAuthority(_auth.getAuthorityName()));

        }



        return new User(account.getEmail(), account.getPassword(), grantedAuthorityList);

    }

    public Optional<Account> findOneByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Optional<Account> findOneById(Long id) {
        return accountRepository.findById(id);
    }
}
