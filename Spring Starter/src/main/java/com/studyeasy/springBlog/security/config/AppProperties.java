package com.studyeasy.springBlog.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    // getters and setters
    private String photoPrefix;

    private String uploadPhotoPrefix;

}
