package com.studyeasy.springBlog.config;

import com.studyeasy.springBlog.models.Account;
import com.studyeasy.springBlog.models.Authority;
import com.studyeasy.springBlog.models.Post;
import com.studyeasy.springBlog.services.AccountService;
import com.studyeasy.springBlog.services.AuthorityService;
import com.studyeasy.springBlog.services.PostService;
import com.studyeasy.springBlog.utils.constants.Privileges;
import com.studyeasy.springBlog.utils.constants.Roles;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class SeedData implements CommandLineRunner {

    private final PostService postService;

    private final AccountService accountService;

    private final AuthorityService authorityService;

    public SeedData(PostService postService, AccountService accountService, AuthorityService authorityService) {
        this.postService = postService;
        this.accountService = accountService;
        this.authorityService = authorityService;
    }

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

        account01.setEmail("jaralkeshav@gmail.com");
        account01.setPassword("password@01");
        account01.setFirstName("Account01");
        account01.setLastName("lastname01");
        account01.setAge(22);
        account01.setDate_of_birth(LocalDate.parse("2002-01-01"));
        account01.setGender("Female");


        account02.setEmail("super_editor@editor.com");
        account02.setPassword("superEditor123");
        account02.setFirstName("Super");
        account02.setLastName("Editor");
        account02.setAge(24);
        account02.setDate_of_birth(LocalDate.parse("2000-01-01"));
        account02.setGender("Male");
        account02.setRole(Roles.SUPER_EDITOR.getRole());
        Set<Authority> authoritySet = new HashSet<>();
        authorityService.findById(Privileges.RESET_ANY_USER_PASSWORD.getPrivilegeId()).ifPresent(authoritySet::add);
        authorityService.findById(Privileges.ACCESS_ADMIN_PANEL.getPrivilegeId()).ifPresent(authoritySet::add);
        account02.setAuthoritySet(authoritySet);

        account03.setEmail("admin@admin.com");
        account03.setPassword("admin@123");
        account03.setFirstName("Admin");
        account03.setLastName("AdminLastName");
        account03.setAge(23);
        account03.setDate_of_birth(LocalDate.parse("2001-01-01"));
        account03.setGender("Male");
        account03.setRole(Roles.ADMIN.getRole());

        account04.setEmail("editor@editor.com");
        account04.setPassword("editor@123");
        account04.setFirstName("Editor");
        account04.setLastName("EditorLastName");
        account04.setAge(20);
        account04.setDate_of_birth(LocalDate.parse("2004-01-01"));
        account04.setGender("Female");
        account04.setRole(Roles.EDITOR.getRole());

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);

        List<Post> posts = postService.findAll();
        if (posts.isEmpty()) {
            Post post1 = new Post();
            post1.setTitle("Post 1");
            post1.setBody("This is a post body............");
            post1.setAccount(account01);
            postService.save(post1);

            Post post2 = new Post();
            post2.setTitle("A Comprehensive Guide to Spring Boot");
            post2.setBody("Spring Boot is a popular open-source framework for creating microservices. " + "It simplifies the development process by eliminating the need for XML configuration files. " + "Spring Boot provides a wide range of features, including dependency injection, autoconfiguration, " + "and embedded servers. This makes it a great choice for building modern web applications. " + "Here are some of the benefits of using Spring Boot: " + "- Reduced development time: Spring Boot's autoconfiguration features eliminate the need for " + "boilerplate code, allowing developers to focus on business logic. " + "- Improved productivity: Spring Boot's intuitive APIs and annotations make it easy to " + "build applications quickly. " + "- Easier testing: Spring Boot's embedded servers simplify testing by eliminating the need for " + "external servers. " + "- Easier deployment: Spring Boot applications are packaged as self-contained JAR files, " + "making them easy to deploy to any environment.");
            post2.setAccount(account01);
            postService.save(post2);


            Post post3 = new Post();
            post3.setTitle("Top 10 Tips for Effective Java Programming");
            post3.setBody("Java is a versatile and widely used programming language. With its strong object-oriented " + "features, it's a great choice for building complex applications. Here are 10 tips to help " + "you become a more effective Java programmer: " + "1. Master the basics: Make sure you have a solid understanding of Java's syntax, data structures, " + "and algorithms. " + "2. Embrace Object-Oriented Programming: Java is an object-oriented language. " + "Leverage classes, objects, inheritance, and encapsulation to organize your code effectively. " + "3. Use Effective Collections: Java offers a variety of collection classes. Choose the right " + "collection type for your needs (e.g., ArrayLists for random access, LinkedLists for frequent " + "insertions/removals). " + "(Remaining tips can be added here)" + "Java is a high-level, class-based, object-oriented programming language that is designed to have as few implementation dependencies as possible. It is a general-purpose programming language intended to let programmers write once, run anywhere (WORA),[16] meaning that compiled Java code can run on all platforms that support Java without the need to recompile.[17] Java applications are typically compiled to bytecode that can run on any Java virtual machine (JVM) regardless of the underlying computer architecture. The syntax of Java is similar to C and C++, but has fewer low-level facilities than either of them. The Java runtime provides dynamic capabilities (such as reflection and runtime code modification) that are typically not available in traditional compiled languages.\n" + "\n" + "Java gained popularity shortly after its release, and has been a very popular programming language since then.[18] Java was the third most popular programming language in 2022 according to GitHub.[19] Although still widely popular, there has been a gradual decline in use of Java in recent years with other languages using JVM gaining popularity.[20]\n" + "\n" + "Java was originally developed by James Gosling at Sun Microsystems. It was released in May 1995 as a core component of Sun's Java platform. The original and reference implementation Java compilers, virtual machines, and class libraries were originally released by Sun under proprietary licenses. As of May 2007, in compliance with the specifications of the Java Community Process, Sun had relicensed most of its Java technologies under the GPL-2.0-only license. Oracle offers its own HotSpot Java Virtual Machine, however the official reference implementation is the OpenJDK JVM which is free open-source software and used by most developers and is the default JVM for almost all Linux distributions.\n" + "\n" + "As of March 2024, Java 22 is the latest version. Java 8, 11, 17, and 21 are previous LTS versions still officially supported.\n" + "\n");
            post3.setAccount(account03);
            postService.save(post3);


            Post post4 = new Post();
            post4.setTitle("The Struggles of a Developer: A Day in the Life");
            post4.setBody("**Morning:** Wake up with a vague sense of dread, knowing there's an impending bug to fix. " + "Spend 30 minutes searching for my missing coffee mug. Finally find it under a pile of " + "laundry. " + "**Mid-Morning:** Open my laptop and see a mountain of code. Spend the next hour deciphering " + "the logic of the previous developer. Why did they use a nested ternary operator?! " + "**Lunchtime:** Stumble out of my chair, back aching from sitting for hours. Realize I forgot " + "to eat breakfast again. Grab a questionable sandwich from the vending machine. " + "(Remaining struggles can be added here)" + "James Gosling, Mike Sheridan, and Patrick Naughton initiated the Java language project in June 1991.[21] Java was originally designed for interactive television, but it was too advanced for the digital cable television industry at the time.[22] The language was initially called Oak after an oak tree that stood outside Gosling's office. Later the project went by the name Green and was finally renamed Java, from Java coffee, a type of coffee from Indonesia.[23] Gosling designed Java with a C/C++-style syntax that system and application programmers would find familiar.[24]\n" + "\n" + "Sun Microsystems released the first public implementation as Java 1.0 in 1996.[25] It promised write once, run anywhere (WORA) functionality, providing no-cost run-times on popular platforms. Fairly secure and featuring configurable security, it allowed network- and file-access restrictions. Major web browsers soon incorporated the ability to run Java applets within web pages, and Java quickly became popular. The Java 1.0 compiler was re-written in Java by Arthur van Hoff to comply strictly with the Java 1.0 language specification.[26] With the advent of Java 2 (released initially as J2SE 1.2 in December 1998 – 1999), new versions had multiple configurations built for different types of platforms. J2EE included technologies and APIs for enterprise applications typically run in server environments, while J2ME featured APIs optimized for mobile applications. The desktop version was renamed J2SE. In 2006, for marketing purposes, Sun renamed new J2 versions as Java EE, Java ME, and Java SE, respectively.\n" + "\n" + "In 1997, Sun Microsystems approached the ISO/IEC JTC 1 standards body and later the Ecma International to formalize Java, but it soon withdrew from the process.[27][28][29] Java remains a de facto standard, controlled through the Java Community Process.[30] At one time, Sun made most of its Java implementations available without charge, despite their proprietary software status. Sun generated revenue from Java through the selling of licenses for specialized products such as the Java Enterprise System.\n" + "\n" + "On November 13, 2006, Sun released much of its Java virtual machine (JVM) as free and open-source software (FOSS), under the terms of the GPL-2.0-only license. On May 8, 2007, Sun finished the process, making all of its JVM's core code available under free software/open-source distribution terms, aside from a small portion of code to which Sun did not hold the copyright.[31]\n" + "\n" + "Sun's vice-president Rich Green said that Sun's ideal role with regard to Java was as an evangelist.[32] Following Oracle Corporation's acquisition of Sun Microsystems in 2009–10, Oracle has described itself as the steward of Java technology with a relentless commitment to fostering a community of participation and transparency.[33] This did not prevent Oracle from filing a lawsuit against Google shortly after that for using Java inside the Android SDK (see the Android section).\n" + "\n" + "On April 2, 2010, James Gosling resigned from Oracle.[34]\n" + "\n" + "In January 2016, Oracle announced that Java run-time environments based on JDK 9 will discontinue the browser plugin.[35]\n" + "\n" + "Java software runs on everything from laptops to data centers, game consoles to scientific supercomputers.[36]\n" + "\n" + "Oracle (and others) highly recommend uninstalling outdated and unsupported versions of Java, due to unresolved security issues in older versions.[37]");
            post4.setAccount(account04);
            postService.save(post4);


            Post post5 = new Post();
            post5.setTitle("Spring Security: An In-Depth Review");
            post5.setBody("Spring Security is a powerful framework for securing Spring applications. It provides features " + "for authentication, authorization, and more. Here's a breakdown of Spring Security's key " + "components: " + "- SecurityContextHolder: Stores the current user's security context. " + "- AuthenticationManager: Responsible for user authentication. " + "- Authentication: Represents the authenticated user and their authorities. " + "- Authorization: Determines whether a user has permission to perform a specific operation. " + "(Detailed explanations of each component can be added here)" + "Principles\n" + "There were five primary goals in the creation of the Java language:[17]\n" + "\n" + "It must be simple, object-oriented, and familiar.\n" + "It must be robust and secure.\n" + "It must be architecture-neutral and portable.\n" + "It must execute with high performance.\n" + "It must be interpreted, threaded, and dynamic.\n" + "Versions\n" + "Main article: Java version history\n" + "As of September 2023, Java 8, 11, 17 and 21 are supported as Long-Term Support (LTS) versions.[38]\n" + "\n" + "Oracle released the last zero-cost public update for the legacy version Java 8 LTS in January 2019 for commercial use, although it will otherwise still support Java 8 with public updates for personal use indefinitely. Other vendors such as Adoptium continue to offer free builds of OpenJDK's Long-Term Support (LTS) versions. These builds may include additional security patches and bug fixes.[39]");
            post5.setAccount(account02);
            postService.save(post5);


        }
    }
}
