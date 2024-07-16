package com.studyeasy.springBlog.utils.constants;

import lombok.Getter;

@Getter
public enum Roles {
    USER("ROLE_USER"),ADMIN("ROLE_ADMIN"),EDITOR("ROLE_EDITOR"),SUPER_EDITOR("ROLE_SUPER_EDITOR");
    private final String role;
    Roles(String role) {
        this.role = role;
    }
}
