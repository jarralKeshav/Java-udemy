package com.studyeasy.springBlog.utils.constants;

public enum Roles {
    USER("ROLE_USER"),ADMIN("ROLE_ADMIN"),EDITOR("ROLE_EDITOR");
    private final String role;
    private Roles(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
